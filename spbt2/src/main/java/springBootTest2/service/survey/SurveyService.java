package springBootTest2.service.survey;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.QuestionOptionDTO;
import springBootTest2.mapper.SurveyMapper;

@Component
@Service
public class SurveyService {
	@Autowired
	SurveyMapper surveyMapper;
	public void execute(Model model) throws Exception {
		List<QuestionOptionDTO> lists = new ArrayList<QuestionOptionDTO>();
		// 질문의 갯수가 몇개인지 확인
		int questionCount = surveyMapper.questionCount();
		for (int i = 1; i <= questionCount; i++) {
			QuestionOptionDTO dto = surveyMapper.surveySelectAll(i);
			lists.add(dto);
		}
		
		model.addAttribute("questions", lists);
		
	}
	
}
