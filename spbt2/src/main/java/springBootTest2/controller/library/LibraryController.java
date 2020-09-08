package springBootTest2.controller.library;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.LibraryBoardCommand;
import springBootTest2.service.library.LibraryBoardListService;
import springBootTest2.service.library.LibraryBoardService;

@Controller
@RequestMapping("libraryBoard")
public class LibraryController {
	@Autowired
	LibraryBoardService libraryBoardService;
	@Autowired
	LibraryBoardListService libraryBoardListService;
	
	// command 객체가 필요한 페이지에 model로 전달
	@ModelAttribute
	LibraryBoardCommand setLibraryBoardCommand() {
		return new LibraryBoardCommand();
	}
	
	@RequestMapping("libraryList")
	public String libraryList(
				@RequestParam(value = "page", defaultValue = "1") Integer page,
				Model model
			) throws Exception {
		libraryBoardListService.libraryBoardList(model, page);
		return "thymeleaf/lib_Board/lib_board_list";
	}
	@RequestMapping(value = "libBoardForm", method = RequestMethod.GET)
	public String libBoardFormGet() {
		return "thymeleaf/lib_Board/lib_board_write";
	}
	@RequestMapping(value = "libBoardForm", method = RequestMethod.POST)
	public String libBoardFormPost(
				@Validated LibraryBoardCommand libraryBoardCommand,
				BindingResult result,
				HttpServletRequest request
			) throws Exception {
		if (result.hasErrors()) {
			return "thymeleaf/lib_Board/lib_board_write";
		}

		return libraryBoardService.writePro(libraryBoardCommand, request);
	}	
}
