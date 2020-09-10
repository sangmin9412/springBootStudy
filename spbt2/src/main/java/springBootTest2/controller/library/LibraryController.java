package springBootTest2.controller.library;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTest2.command.LibraryBoardCommand;
import springBootTest2.controller.FileDownLoad;
import springBootTest2.domain.FileName;
import springBootTest2.service.FileDelService;
import springBootTest2.service.library.LibraryBoardDeleteService;
import springBootTest2.service.library.LibraryBoardDetailService;
import springBootTest2.service.library.LibraryBoardListService;
import springBootTest2.service.library.LibraryBoardModifyService;
import springBootTest2.service.library.LibraryBoardService;

@Controller
@RequestMapping("libraryBoard")
public class LibraryController {
	@Autowired
	LibraryBoardService libraryBoardService;
	@Autowired
	LibraryBoardListService libraryBoardListService;
	@Autowired
	LibraryBoardDetailService libraryBoardDetailService;
	@Autowired
	LibraryBoardModifyService libraryBoardModifyService;
	@Autowired
	LibraryBoardDeleteService libraryBoardDeleteService;
	@Autowired
	FileDownLoad fileDownLoad;
	@Autowired
	FileDelService fileDelService;
	
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
	@RequestMapping("libraryDetail/{id}")
	public String libraryDetail(
				@PathVariable(value = "id") String boardNum,
				Model model,
				HttpSession session
			) throws Exception {
		libraryBoardDetailService.libraryDetail(boardNum, session, model);
		return "thymeleaf/lib_Board/lib_board_view";
	}
	@RequestMapping("fileDown")
	public void fileDownLoad(
				@RequestParam(value = "file") String fileName,
				HttpServletResponse response,
				HttpServletRequest request
			) throws Exception {
		String path = "/upload/lib_Board/";
		fileDownLoad.fileDownLoad(path, fileName, request, response);
	}
	@RequestMapping("libBoardModify")
	public String libBoardModify(
				@RequestParam(value = "boardNum") String boardNum,
				Model model,
				HttpSession session
			) throws Exception {
		if (session.getAttribute("fileList") != null) {
			session.removeAttribute("fileList");
		}
		libraryBoardDetailService.libraryDetail(boardNum, session, model);
		return "thymeleaf/lib_Board/lib_board_modify";
	}
	@RequestMapping("fileDel")
	public String fileDel(
			FileName fileName,
			HttpSession session,
			Model model
			) {
		fileDelService.fileSessionAdd(fileName, session, model);
		return "thymeleaf/lib_Board/delPage";
	}
	@RequestMapping(value = "libBoardModifyPro", method = RequestMethod.POST)
	public String libBoardModifyPro(
				LibraryBoardCommand libraryBoardCommand,
				HttpSession session,
				Model model
			) throws Exception {
		return libraryBoardModifyService.libBoardModify(libraryBoardCommand, session, model);
	}
	@RequestMapping("libBoardDel")
	public String libBoardDel (
				@RequestParam(value = "boardNum") String boardNum,
				Model model
			) {
		model.addAttribute("boardNum", boardNum);
		return "thymeleaf/lib_Board/lib_board_delete";
	}
	@RequestMapping("libBoardDelPro")
	public String libBoardDelPro(
				@RequestParam(value = "boardNum") String boardNum,
				@RequestParam(value = "boardPass") String boardPass,
				HttpSession session,
				Model model
			) throws Exception {
		return libraryBoardDeleteService.execute(boardNum, boardPass, session, model);
	}
}
