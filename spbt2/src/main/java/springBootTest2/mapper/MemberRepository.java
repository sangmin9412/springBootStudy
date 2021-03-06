package springBootTest2.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.MemberDTO;

@Component
@Repository
public class MemberRepository {
	private final String namespace = "mappers.MemberJoinOkMapper";
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	public Integer joinOkUpdate(MemberDTO memberDTO) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		String statement = namespace + ".joinOkUpdate";
		return sqlSession.update(statement, memberDTO);
	}
	
}
