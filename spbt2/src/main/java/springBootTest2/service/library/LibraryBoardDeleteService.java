package springBootTest2.service.library;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.command.AuthInfo;
import springBootTest2.domain.LibraryDTO;
import springBootTest2.domain.StartEndPageDTO;
import springBootTest2.mapper.LibraryBoardMapper;

@Component
@Service
public class LibraryBoardDeleteService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String execute(String boardNum, String boardPass, HttpSession session, Model model) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		LibraryDTO dto = new LibraryDTO();
		dto.setBoardNum(Long.parseLong(boardNum));
		dto.setUserId(authInfo.getId());
		
		StartEndPageDTO startEndPageDTO = new StartEndPageDTO(1L, 1L, null, boardNum);
		LibraryDTO lib = libraryBoardMapper.selectByLibrary(startEndPageDTO).get(0);
		
		if (passwordEncoder.matches(boardPass, lib.getBoardPass())) {
			int result = libraryBoardMapper.libraryDelete(dto);
			System.out.println(result + " 개의 자료실 글이 삭제되었습니다.");
			if (result >= 1) { // DB에서 삭제가 되었다면 파일을 삭제함
				String [] strFile = lib.getStoreFileName().split("`");
				
				String path = "/upload/lib_Board/";
				String filePath = session.getServletContext().getRealPath(path);
				File file = null;
				
				for (String fn : strFile) {
					file = new File(filePath + fn);
					if (file.exists()) {
						file.delete();
					}
				}
			}
			return "redirect:/libraryBoard/libraryList";
		}
		
		model.addAttribute("valid_Pw", "비밀번호가 틀렸습니다.");
		return "thymeleaf/lib_Board/lib_board_delete";
	}
	
	
}
