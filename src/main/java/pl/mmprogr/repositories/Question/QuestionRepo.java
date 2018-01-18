package pl.mmprogr.repositories.Question;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mmprogr.models.Question.Question;

/**
 * Created by ggere on 11.12.2017.
 */
@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
}
