package springBootTest2.command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginCommand {
	@Size(min = 6, max = 12, message = "아이디를 입력해주세요.")
	String userId;
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	String userPw;
	Boolean idStore;
	Boolean autoLogin;
	
}
