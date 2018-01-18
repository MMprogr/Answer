package pl.mmprogr.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.mmprogr.models.Question.Question;
import pl.mmprogr.services.Question.QuestionService;
import pl.mmprogr.services.Validation.ValidationErrorBuilder;

import javax.validation.Valid;

/**
 * Created by ggere on 21.12.2017.
 */
@RestController
@RequestMapping("/questions")
@Api(value = "questions", description = "Operations pertaining to questions in Answer Site")
public class QuestionController {
	private final QuestionService questionService;

	@Autowired
	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}


	@ApiOperation(value = "View all questions", response = Question.class, responseContainer = "List")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Iterable<Question> findAll() {
		return questionService.findAll();
	}

	@ApiOperation(value = "View a question by id", response = Question.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity findById(@PathVariable("id") Integer id) {
		return questionService.findById(id)
				.map(q -> ResponseEntity.ok().body(q))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "Add a question")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity add(@Valid @RequestBody Question question, BindingResult result, Errors errors) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(ValidationErrorBuilder.fromBindingErrors(errors), HttpStatus.BAD_REQUEST);
		}
		questionService.add(question);
		return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
	}

	@ApiOperation(value = "Delete a question")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		return questionService.findById(id)
				.map(q -> {
					questionService.deleteById(id);
					return ResponseEntity.ok().body(q);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
