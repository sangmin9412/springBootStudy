package springBootTest2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsDTO {
	String userId;
	Integer questionNum;
	Integer optionNum;
	String optionName;
}
