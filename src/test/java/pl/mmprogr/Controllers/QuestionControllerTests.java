package pl.mmprogr.Controllers;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import pl.mmprogr.controllers.QuestionController;
import pl.mmprogr.models.Question.Question;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(QuestionController.class)
public class QuestionControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private QuestionController questionController;

	@Autowired
	private Gson gson;

	@Test
	public void getAllQuestions() throws Exception {
		List<Question> allQuestions = new ArrayList<>();

		allQuestions.add(Question.builder().id(0).build());
		allQuestions.add(Question.builder().id(1).build());

		given(questionController.findAll()).willReturn(allQuestions);

		mvc.perform(get("/questions/")
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(allQuestions.get(0).getId())))
				.andExpect(jsonPath("$[1].id", is(allQuestions.get(1).getId())));
	}

	@Test
	public void getQuestionsByIdExist() throws Exception {
		Question question = Question.builder().id(0).content("test").build();

		given(questionController.findById(question.getId())).willReturn(ResponseEntity.ok().body(question));

		mvc.perform(get("/questions/{id}", question.getId())
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id", is(question.getId())))
				.andExpect(jsonPath("content", is(question.getContent())));

		verify(questionController, times(1)).findById(question.getId());
		verifyNoMoreInteractions(questionController);
	}

	@Test
	public void getQuestionsByIdNotExist() throws Exception {
		given(questionController.findById(0)).willReturn(ResponseEntity.notFound().build());

		mvc.perform(get("/questions/{id}", 0)
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());

		verify(questionController, times(1)).findById(0);
		verifyNoMoreInteractions(questionController);
	}

	@Test
	public void addQuestionCreated() throws Exception {
		Question question = Question.builder().content("test").build();

		BindingResult bindingResult = mock(BindingResult.class);
		Errors errors = mock(Errors.class);
		when(questionController.add(question, bindingResult, errors)).thenReturn(ResponseEntity.status(HttpStatus.CREATED).build());
		mvc.perform(
				post("/questions/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(question)))
				.andExpect(status().isOk())
				.andDo(print());

		verify(questionController, times(1)).add(any(Question.class), any(BindingResult.class), any(Errors.class));
		verifyNoMoreInteractions(questionController);
	}
}