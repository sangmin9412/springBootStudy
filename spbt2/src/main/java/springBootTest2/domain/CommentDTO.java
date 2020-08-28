package springBootTest2.domain;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
	private Long commentNo;
	private String cuserId;
	private Timestamp regDate;
	private String commentSubject;
	private String commentContent;
	
	StartEndPageDTO startEndPageDTO;
	
	MemberDTO memberDTO;
	
	private List<ReplyDTO> replies;

	public CommentDTO(Long commentNo, String cuserId, Timestamp regDate, String commentSubject, String commentContent) {
		this.commentNo = commentNo;
		this.cuserId = cuserId;
		this.regDate = regDate;
		this.commentSubject = commentSubject;
		this.commentContent = commentContent;
	}
}
