package pl.mmprogr.services.Question;

import org.springframework.stereotype.Service;
import pl.mmprogr.models.Question.Question;

import java.util.List;
import java.util.Optional;

/**
 * Created by ggere on 11.12.2017.
 */
@Service
public interface QuestionService {
	Optional<Question> findById(Integer id);
	List<Question> findAll();
	void add(Question review);
	void deleteById(Integer id);
}
