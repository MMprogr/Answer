package pl.mmprogr.models.Answer;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mmprogr.models.Question.Question;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ggere on 10.01.2018.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answers")
public class Answer {
	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "The database generated answer id", hidden = true)
	private Integer id;

	@ApiModelProperty(notes = "Answer content", required = true)
	@NotNull
	@NotEmpty
	private String content;

	@ApiModelProperty(notes = "Date of answer creation", required = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@NotNull
	private Date date;

	@ApiModelProperty(notes = "Question to which answer belongs", required = true)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_id")
	@NotNull
	private Question question;
}
