package springBootTest2.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {
	private Long commentNo;
	private String cuserId;
	private Long replyNo;
	private String ruserId;
	private String replyContent;
	private Timestamp regDate;
	
}
