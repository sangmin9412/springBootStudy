package springBootTest2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPwChangeDTO {
	String userId;
	String newUserPw;
}
