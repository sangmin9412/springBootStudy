package springBootTest2.command;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
