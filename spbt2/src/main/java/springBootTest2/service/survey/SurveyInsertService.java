package springBootTest2.service.survey;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import springBootTest2.command.AuthInfo;
import springBootTest2.command.SurveyCommand;
import springBootTest2.domain.OptionsDTO;
import springBootTest2.domain.QuestionDTO;
import springBootTest2.mapper.SurveyMapper;

@Component
@Service
public class SurveyInsertService {
	@Autowired
	SurveyMapper surveyMapper;
	
	public void surveyInsert(SurveyCommand surveyCommand, HttpSession session) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		String userId = authInfo.getId();
		
		QuestionDTO qdto = new QuestionDTO();
		qdto.setQuestionTitle(surveyCommand.getQuestion());
		qdto.setUserId(userId);
		surveyMapper.questionInsert(qdto);
		// question 저장되고 나서 questionNum을 받아오기 위해 쿼리문 실행
		int questionNum = surveyMapper.questionNum(userId);
		String [] options = null;
		if (surveyCommand.getOptions() != null) {
			options = surveyCommand.getOptions().split("`");
		}
		
		int optionsNum = 1;
		
		for (String optionName : options) {
			OptionsDTO odto = new OptionsDTO();
			odto.setQuestionNum(questionNum);
			odto.setOptionNum(optionsNum);
			odto.setOptionName(optionName);
			odto.setUserId(userId);
			surveyMapper.optionInsert(odto);
			optionsNum++;
		}
		
	}
	
}
