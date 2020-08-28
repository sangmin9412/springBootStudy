package springBootTest2.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRepliesDTO {
	private CommentDTO commentDTO;
	private List<ReplyDTO> replies;
}
