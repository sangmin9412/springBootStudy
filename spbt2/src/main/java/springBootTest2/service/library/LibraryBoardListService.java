package springBootTest2.service.library;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.controller.PageAction;
import springBootTest2.domain.LibraryDTO;
import springBootTest2.domain.StartEndPageDTO;
import springBootTest2.mapper.LibraryBoardMapper;

@Component
@Service
public class LibraryBoardListService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	
	public void libraryBoardList(Model model, Integer page) throws Exception {
		int limit = 10;
		int limitPage = 10;
		
		Long startRow = ((long) page - 1) * limit + 1;
		Long endRow = startRow + limit - 1;
		
		StartEndPageDTO startEndPageDTO = new StartEndPageDTO(startRow, endRow);
		
		int count = libraryBoardMapper.libraryCount();
		List<LibraryDTO> list = libraryBoardMapper.selectByLibrary(startEndPageDTO);
		
		model.addAttribute("count", count);
		model.addAttribute("libraryList", list);
		
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "libraryList");
		
	}
	
	
}
