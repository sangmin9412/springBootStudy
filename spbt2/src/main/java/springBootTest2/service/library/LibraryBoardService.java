package springBootTest2.service.library;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.AuthInfo;
import springBootTest2.command.LibraryBoardCommand;
import springBootTest2.domain.LibraryDTO;
import springBootTest2.mapper.LibraryBoardMapper;

@Component
@Service
public class LibraryBoardService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	
	public String writePro(LibraryBoardCommand libraryBoardCommand, HttpServletRequest request) throws Exception {
		LibraryDTO dto = new LibraryDTO();
		dto.setBoardSubject(libraryBoardCommand.getBoardSubject());
		dto.setBoardContent(libraryBoardCommand.getBoardContent());
		dto.setBoardName(libraryBoardCommand.getBoardName());
		dto.setBoardPass(libraryBoardCommand.getBoardPass());
		
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		dto.setUserId(authInfo.getId());
		dto.setIpAddr(request.getRemoteAddr());
		
		// 파일 정보를 입력하기 위한 변수
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal = "";
		String location = "";
		String path = "/upload/lib_Board/";
		String filePath = request.getServletContext().getRealPath(path);
		System.out.println(filePath);
		
		for (MultipartFile mf : libraryBoardCommand.getReport()) {
			String original = mf.getOriginalFilename(); // 전송된 파일명
			String originalFileExtension = original.substring(original.lastIndexOf(".")); // 전송된 파일명으로 부터 확장자만 잘라옴
			String store = UUID.randomUUID().toString().replace("-", "") + originalFileExtension;
			String fileSize = Long.toString(mf.getSize());
			
			originalTotal += original + "`";
			storeTotal += store + "`";
			fileSizeTotal += fileSize + "`";
			
			// 파일을 저장하기 위해서 파일 객체 생성
			File file = new File(filePath + store);
			try {
				mf.transferTo(file);
			} catch (Exception e) {
				location = "thymeleaf/lib_Board/lib_board_write";
				e.printStackTrace();
			}
		}
		
		dto.setOriginalFileName(originalTotal);
		dto.setStoreFileName(storeTotal);
		dto.setFileSize(fileSizeTotal);
		
		libraryBoardMapper.libraryInsert(dto);
		location = "redirect:/libraryBoard/libraryList";
		return location;
		
	}
	
}
