package springBootTest2.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.MemberCommand;
import springBootTest2.service.member.MemberDeleteService;
import springBootTest2.service.member.MemberDetailService;
import springBootTest2.service.member.MemberModifyService;

@Controller
@RequestMapping("edit")
public class MemberDetailController {
	@Autowired
	private MemberDetailService memberDetailService;
	@Autowired
	private MemberModifyService memberModifyService;
	@Autowired
	MemberDeleteService memberDeleteService; 
	@RequestMapping(value = "memberInfo/{id}")
	public String memberDetail(
				@PathVariable(value = "id") String userId,
				Model model
			) throws Exception {
		memberDetailService.memberDetail(userId, model);
		return "thymeleaf/member/memberInfo";
	}
	@RequestMapping(value = "memberModify")
	public String memberModify(
				@RequestParam(value = "userId") String userId,
				Model model
			) throws Exception  {
		memberDetailService.memberDetail(userId, model);
		return "thymeleaf/member/memberModify";
	}
	@RequestMapping(value = "memberModifyPro", method = RequestMethod.POST)
	public String memberModifyPro(
				MemberCommand memberCommand,
				Model model
			) throws Exception {
		Integer i = memberModifyService.memberModify(memberCommand, model);
		if (i > 0) {
			return "redirect:/edit/memberInfo/" + memberCommand.getUserId();
		} else {
			model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
			return "thymeleaf/member/memberModify";
		}
	}
	@RequestMapping(value = "memberDelete/{id}", method = RequestMethod.GET)
	public String memberDelete(
				@PathVariable(value = "id") String userId
			) throws Exception {
		memberDeleteService.memberDelete(userId);
		return "redirect:/mem/memberList";
	}
}
