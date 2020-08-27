package springBootTest2.service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import springBootTest2.command.AuthInfo;
import springBootTest2.domain.MemberDTO;
import springBootTest2.domain.UserPwChangeDTO;
import springBootTest2.mapper.MemberMapper;

@Component
@Service
@Transactional
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public void memberDelete(String userId) throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(userId);
		memberMapper.memberDelete(memberDTO);
		
	}
	public String myDelete(String userPw, HttpSession session, Model model) throws Exception {
		String userId = ((AuthInfo) session.getAttribute("authInfo")).getId();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(userId);
		memberDTO = memberMapper.selectByMember(memberDTO).get(0);
		
		if (passwordEncoder.matches(userPw, memberDTO.getUserPw())) {
			memberMapper.memberDelete(memberDTO);
			return "redirect:/main/logout";
		} else {
			model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/mypage/userDeletePw";
		}
	}
	
}
