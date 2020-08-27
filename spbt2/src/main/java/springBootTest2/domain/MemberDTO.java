package springBootTest2.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias(value = "memdto")
public class MemberDTO implements Serializable {
	String userId;
	String userPw;
	String userName;
	Timestamp userBirth;
	String userGender;
	String userAddr;
	String userPh1;
	String userPh2;
	String userEmail;
	Timestamp userRegist;
	String joinOk;
	String interest;
	
	StartEndPageDTO startEndPageDTO;

	public MemberDTO(String userId, String userPw, String userName, Timestamp userBirth, String userGender,
			String userAddr, String userPh1, String userPh2, String userEmail, Timestamp userRegist, String joinOk,
			String interest) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userBirth = userBirth;
		this.userGender = userGender;
		this.userAddr = userAddr;
		this.userPh1 = userPh1;
		this.userPh2 = userPh2;
		this.userEmail = userEmail;
		this.userRegist = userRegist;
		this.joinOk = joinOk;
		this.interest = interest;
	}
	
}
