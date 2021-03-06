package springBootTest2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.MemberDTO;
import springBootTest2.domain.UserPwChangeDTO;

@Component
@Repository(value = "springBootTest2.mapper")
public interface MemberMapper {
	public Integer insertMember(MemberDTO dto) throws Exception;
	public Integer joinOkUpdate(MemberDTO dto) throws Exception;
	public List<MemberDTO> selectByMember(MemberDTO memberDTO) throws Exception;
	public Integer getMemberCount() throws Exception;
	public Integer memberUpdate(MemberDTO dto) throws Exception;
	public Integer memberDelete(MemberDTO memberDTO) throws Exception;
	public Integer changePw(UserPwChangeDTO dto) throws Exception ;
}
