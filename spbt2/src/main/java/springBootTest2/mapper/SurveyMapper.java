package springBootTest2.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.OptionsDTO;
import springBootTest2.domain.QuestionDTO;
import springBootTest2.domain.QuestionOptionDTO;

@Component
@Repository
public interface SurveyMapper {
	public int questionInsert(QuestionDTO qdto) throws Exception;
	public int questionNum(String userId) throws Exception;
	public void optionInsert(OptionsDTO odto) throws Exception;
	public int questionCount() throws Exception;
	public QuestionOptionDTO surveySelectAll(int i) throws Exception; 
	
}
