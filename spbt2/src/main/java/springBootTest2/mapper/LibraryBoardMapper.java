package springBootTest2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.LibraryDTO;
import springBootTest2.domain.StartEndPageDTO;

@Component
@Repository
public interface LibraryBoardMapper {
	public Integer libraryInsert(LibraryDTO dto) throws Exception;
	public int libraryCount() throws Exception;
	public List<LibraryDTO> selectByLibrary(StartEndPageDTO startEndPageDTO) throws Exception;	
	
}
