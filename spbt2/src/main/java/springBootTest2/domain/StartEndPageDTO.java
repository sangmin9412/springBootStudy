package springBootTest2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartEndPageDTO {
	Long startPage;
	Long endPage;
	String userId;
	String num;
	
	public StartEndPageDTO(Long startPage, Long endPage) {
		super();
		this.startPage = startPage;
		this.endPage = endPage;
	}
	
}	
