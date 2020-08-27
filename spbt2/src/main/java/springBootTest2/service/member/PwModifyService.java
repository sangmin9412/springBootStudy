package springBootTest2.service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.command.AuthInfo;
import springBootTest2.domain.MemberDTO;
import springBootTest2.mapper.MemberMapper;

@Component
@Service
public class PwModifyService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(String userPw, Model model, HttpSession session) throws Exception {
		String userId = ((AuthInfo) session.getAttribute("authInfo")).getId();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(userId);
		memberDTO = memberMapper.selectByMember(memberDTO).get(0);
		
		if (passwordEncoder.matches(userPw, memberDTO.getUserPw())) {
			return "thymeleaf/mypage/pwModify_1";
		} else {
			model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/mypage/pwModify";
		}
	}
	
}
