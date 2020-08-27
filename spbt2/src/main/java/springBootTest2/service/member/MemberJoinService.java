package springBootTest2.service.member;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import springBootTest2.command.MemberCommand;
import springBootTest2.controller.MailAction;
import springBootTest2.controller.SmsSend;
import springBootTest2.domain.MemberDTO;
import springBootTest2.mapper.MemberMapper;
import springBootTest2.mapper.MemberRepository;

@Component
@Service
@Transactional
public class MemberJoinService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	MailAction mailAction;
	@Autowired
	MemberMapper memberRepository;
	public Integer insertMember(MemberCommand memberCommand, Model model) {
		if (!memberCommand.isUserPwEqualToUserPwCon()) {
			model.addAttribute("valid_userPwCon", "비밀번호가 일치하지 않습니다.");
			return null;
		}
		
		MemberDTO memberDTO = new MemberDTO();
		Timestamp userBirth = Timestamp.valueOf(memberCommand.getUserBirth());
		memberDTO.setUserId(memberCommand.getUserId());
		memberDTO.setUserPw(passwordEncoder.encode(memberCommand.getUserPw()));
		memberDTO.setUserName(memberCommand.getUserName());
		memberDTO.setUserGender(memberCommand.getUserGender());
		memberDTO.setUserEmail(memberCommand.getUserEmail());
		memberDTO.setUserAddr(memberCommand.getUserAddr());
		memberDTO.setUserPh1(memberCommand.getUserPh1());
		memberDTO.setUserPh2(memberCommand.getUserPh2());
		memberDTO.setUserBirth(userBirth);
		
		String iterest = "";
		for (String s: memberCommand.getChk1()) {
			iterest += s + "`";
		}
		memberDTO.setInterest(iterest);
		
		Integer result = null;
		try {
			result = memberMapper.insertMember(memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (result != null) {
			SmsSend ss = new SmsSend();
			try {
				mailAction.sendMail(memberDTO.getUserEmail(), memberDTO.getUserId());
				ss.smsSend(memberDTO.getUserPh1(), memberDTO.getUserName()+"님 회원가입을 축하합니다.");
			} catch (Exception e) {
				ss.smsSend(memberDTO.getUserPh1(), memberDTO.getUserName()+"님 회원가입을 축하합니다. " + "그러나 1254-1254로 문의 바랍니다.");
				e.printStackTrace();
			}
		} else {
			model.addAttribute("duplicate_userId", "사용중인 아이디입니다.");
		}
		return result;
	}
	public Integer joinOkUpdate(String joinOk, String reciver, String userId) {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setJoinOk(joinOk);
		memberDTO.setUserEmail(reciver);
		memberDTO.setUserId(userId);
		// return memberRepository.joinOkUpdate(memberDTO);
		Integer result = null;
		try {
			result = memberRepository.joinOkUpdate(memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
