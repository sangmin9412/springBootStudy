package springBootTest2.service.library;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.FileName;
import springBootTest2.domain.LibraryDTO;
import springBootTest2.domain.StartEndPageDTO;
import springBootTest2.mapper.LibraryBoardMapper;

@Component
@Service
public class LibraryBoardDetailService {
	@Autowired
	LibraryBoardMapper libraryBoardMapper;
	
	public void libraryDetail(String boardNum, HttpSession session, Model model) throws Exception {
		List<FileName> fileList = null;
		libraryBoardMapper.updateReadCount(boardNum);
		
		StartEndPageDTO startEndPageDTO = new StartEndPageDTO(1L, 1L, null, boardNum);
		LibraryDTO dto = libraryBoardMapper.selectByLibrary(startEndPageDTO).get(0);
		
		if (dto.getOriginalFileName() != null) {
			String [] oriFile = dto.getOriginalFileName().split("`");
			String [] strFile = dto.getStoreFileName().split("`");
			String [] fileSize = dto.getFileSize().split("`");
			fileList = new ArrayList<FileName>();
			int i = 0;
			for (String file : oriFile) {
				FileName fileName = new FileName(file, strFile[i], fileSize[i]);
				fileList.add(fileName);
				i++;
			}
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("fileList", fileList);
	}
	
}
