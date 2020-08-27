package springBootTest2.command;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePwCommand {
	String userId;
	@NotEmpty
	String userPw;
	@NotEmpty
	String newPw;
	@NotEmpty
	String reNewPw;
	
	public boolean isNewPwToReNewPw() {
		if (newPw.equals(reNewPw)) {
			return true;
		}
		return false;
	}
	
}
