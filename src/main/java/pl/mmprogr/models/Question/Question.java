package pl.mmprogr.models.Question;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ggere on 29.11.2017.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue
	@ApiModelProperty(notes = "The database generated question id", hidden = true)
	private Integer id;

	@ApiModelProperty(notes = "Question content", required = true)
	@NotNull
	@NotEmpty
	private String content;

	@ApiModelProperty(notes = "Date of creation", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date date = new Date();
}
