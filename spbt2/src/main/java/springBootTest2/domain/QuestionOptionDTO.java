package springBootTest2.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionOptionDTO {
	QuestionDTO questionDTO;
	List<OptionsDTO> options;
	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
}
