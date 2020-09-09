package springBootTest2.service.library;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import springBootTest2.command.AuthInfo;
import springBootTest2.command.LibraryBoardCommand;
import springBootTest2.domain.FileName;
import springBootTest2.domain.LibraryDTO;
import springBootTest2.domain.StartEndPageDTO;
import springBootTest2.mapper.LibraryBoardMapper;

@Component
@Service
@Transactional
public class LibraryBoardModifyService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String libBoardModify(LibraryBoardCommand libraryBoardCommand, HttpSession session, Model model) throws Exception {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		LibraryDTO dto = new LibraryDTO();
		dto.setBoardNum(Long.parseLong(libraryBoardCommand.getBoardNum()));
		dto.setBoardSubject(libraryBoardCommand.getBoardSubject());
		dto.setBoardContent(libraryBoardCommand.getBoardContent());
		dto.setUserId(authInfo.getId());
		List<FileName> list = (List<FileName>) session.getAttribute("fileList");
		
		StartEndPageDTO startEndPageDTO = new StartEndPageDTO(1L, 1L, dto.getUserId(), dto.getBoardNum().toString());
		LibraryDTO lib = libraryBoardMapper.selectByLibrary(startEndPageDTO).get(0); // 자료실정보 가져오기
		
		if (list != null) {
			for (FileName fn : list) {
				lib.setOriginalFileName(lib.getOriginalFileName().replace(fn.getOriginalFileName()+"`", ""));
				lib.setStoreFileName(lib.getStoreFileName().replace(fn.getStoreFileName()+"`", ""));
				lib.setFileSize(lib.getFileSize().replace(fn.getFileSize()+"`", ""));
			}
		}
		
		String originalTotal = "";
		String storeTotal = "";
		String fileSizeTotal = "";
		File file = null;
		String path = "/upload/lib_Board/";
		String filePath = session.getServletContext().getRealPath(path);
		// resources 폴더에 접근할 때 전체 경로를 적어줌
		// filePath = "C:/javaProgram/springBootStudy/spbt2/src/main/resources\static/upload/lib_Board";
		
		for (MultipartFile mf : libraryBoardCommand.getReport()) {
			if (mf.getOriginalFilename().length() == 0) break;
			String original = mf.getOriginalFilename();
			String originalFileExtension = original.substring(original.lastIndexOf("."));
			String store = UUID.randomUUID().toString().replace("-", "") + originalFileExtension;
			String fileSize = Long.toString(mf.getSize());
			
			originalTotal += original + "`";
			storeTotal += store + "`";
			fileSizeTotal += fileSize + "`";
			file = new File(filePath + store);
			try {
				// 파일 저장
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String f1 = "";
		String f2 = "";
		String f3 = "";
		
		if (lib.getOriginalFileName() != null) { // 파일을 전부 삭제 한 후 수정을 통해 업로드할 때 null이 포함되어 DB에 저장되는 부분 방지
			f1 = originalTotal+lib.getOriginalFileName();
			f2 = storeTotal+lib.getStoreFileName();
			f3 = fileSizeTotal+lib.getFileSize();
		} else {
			f1 = originalTotal;
			f2 = storeTotal;
			f3 = fileSizeTotal;
		}
		
		dto.setOriginalFileName(f1);
		dto.setStoreFileName(f2);
		dto.setFileSize(f3);
		
		
		// 수정을 하기 위해서는 command에 있는 비밀번호와 디비에 저장된 비밀번호가 같아야 한다.
		if (passwordEncoder.matches(libraryBoardCommand.getBoardPass(), lib.getBoardPass())) {
			// 같으면 update
			libraryBoardMapper.libraryUpdate(dto);
			// session에 있는 파일 정보 삭제.
			if (list != null) {
				// 파일 삭제
				for (FileName fi : list) {
					file = new File(filePath + fi.getStoreFileName().replace("`", ""));
					if (file.exists()) {
						file.delete();
					}
				}
				// session 삭제
				session.removeAttribute("fileList");
			}
			// 디테일 페이지로 이동
			return "redirect:/libraryBoard/libraryDetail/" + lib.getBoardNum();
		}
		
		// 비밀번호가 일치하지 않으면 수정 페이지로 이동
		return "thymeleaf/lib_Board/lib_board_modify";
	}
	
}
