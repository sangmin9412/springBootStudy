package springBootTest2.controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.AuthInfo;
import springBootTest2.command.ChangePwCommand;
import springBootTest2.command.MemberCommand;
import springBootTest2.domain.MemberDTO;
import springBootTest2.service.member.MemberDeleteService;
import springBootTest2.service.member.MemberDetailService;
import springBootTest2.service.member.MemberModifyService;
import springBootTest2.service.member.PwModifyService;

@Controller
@RequestMapping("mypage")
public class MemberMyPageController {
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberModifyService memberModifyService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	PwModifyService pwModifyService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@ModelAttribute("changePwCommand")
	ChangePwCommand setChangePwCommand() {
		return new ChangePwCommand();
	}
	
	
	@RequestMapping("myInfo")
	public String myInfo(
				Model model,
				HttpSession session 
			) throws Exception {
		String userId = ((AuthInfo) session.getAttribute("authInfo")).getId();
		memberDetailService.memberDetail(userId, model);
		return "thymeleaf/mypage/myInfo";
	}
	@RequestMapping("myPw")
	public String myPw() {
		return "thymeleaf/mypage/myInfoPw";
	}
	@RequestMapping(value = "myInfoCng", method = RequestMethod.POST)
	public String myInfoCng(
				@RequestParam(value = "userPw") String userPw,
				HttpSession session,
				Model model
			) throws Exception {
		String userId = ((AuthInfo) session.getAttribute("authInfo")).getId();
		MemberDTO memberDTO = memberDetailService.memberDetail(userId, model);
		if (passwordEncoder.matches(userPw, memberDTO.getUserPw())) {
			return "thymeleaf/mypage/myInfoCng";
		} else {
			model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/mypage/myInfoPw";
		}
	}
	@RequestMapping(value = "myInfoModifyPro", method = RequestMethod.POST)
	public String myInfoModifyPro(
				MemberCommand memberCommand,
				Model model
			) throws Exception {
		Integer i = memberModifyService.memberModify(memberCommand, model);
		if (i > 0) {
			return "redirect:/mypage/myInfo";
		} else {
			model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
			return "redirect:/mypage/myInfoCng";
		}
	}
	@RequestMapping(value = "myPwForm", method = RequestMethod.GET)
	public String memberPwForm() {
		return "thymeleaf/mypage/pwModify";
	}
	@RequestMapping(value = "myPwForm", method = RequestMethod.POST)
	public String memberPwForm1(
				@RequestParam(value = "userPw") String userPw,
				Model model,
				HttpSession session
			) throws Exception {
		return pwModifyService.execute(userPw, model, session);
	}
	@RequestMapping(value = "pwModifyPro", method = RequestMethod.POST)
	public String pwModifyPro(
				@Validated ChangePwCommand changePwCommand,
				BindingResult result,
				HttpSession session,
				Model model
			) throws Exception {
		if (result.hasErrors()) {
			return "thymeleaf/mypage/pwModify_1";
		}
		return pwModifyService.changePw(changePwCommand, session, model);
	}
	@RequestMapping(value = "myDelete", method = RequestMethod.GET)
	public String myDelete() {
		return "thymeleaf/mypage/userDeletePw";
	}
	@RequestMapping(value = "myDeletePro", method = RequestMethod.POST)
	public String myDeletePro(
				@RequestParam("userPw") String userPw,
				HttpSession session,
				Model model
			) throws Exception {
		return memberDeleteService.myDelete(userPw, session, model);
	}
}
