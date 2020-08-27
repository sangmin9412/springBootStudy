package springBootTest2.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springBootTest2.domain.MemberDTO;
import springBootTest2.mapper.MemberMapper;

@Component
@Service
@Transactional
public class MemberDeleteService {
	@Autowired
	MemberMapper memberMapper;
	public void memberDelete(String userId) throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUserId(userId);
		memberMapper.memberDelete(memberDTO);
		
	}
	
}
