package pl.mmprogr.services.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mmprogr.models.Question.Question;
import pl.mmprogr.repositories.Question.QuestionRepo;

import java.util.List;
import java.util.Optional;

/**
 * Created by ggere on 11.12.2017.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
	private final QuestionRepo questionRepo;

	@Autowired
	public QuestionServiceImpl(QuestionRepo questionRepo) {
		this.questionRepo = questionRepo;
	}

	@Override
	public Optional<Question> findById(Integer id) {
		return questionRepo.findById(id);
	}

	@Override
	public List<Question> findAll() {
		return questionRepo.findAll();
	}

	@Override
	public void add(Question Question) {
		questionRepo.save(Question);
	}

	@Override
	public void deleteById(Integer id) {
		questionRepo.deleteById(id);
	}
}
