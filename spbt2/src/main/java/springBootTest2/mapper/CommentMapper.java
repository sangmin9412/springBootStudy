package springBootTest2.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import springBootTest2.domain.CommentDTO;
import springBootTest2.domain.CommentRepliesDTO;
import springBootTest2.domain.CommentUserDTO;
import springBootTest2.domain.ReplyDTO;

@Component
@Repository(value = "springBootTest2.mapper.CommentMapper")
public interface CommentMapper {
	public Integer insertComment(CommentDTO dto) throws Exception;
	public List<CommentDTO> getCommentList(CommentDTO dto) throws Exception;
	public Integer getCommentCount() throws Exception;
	public Integer replyInsert(ReplyDTO replyDTO) throws Exception;
	public CommentDTO getCommentReplies(CommentDTO dto) throws Exception;
	public CommentRepliesDTO commentRepliesCollection(Long commentNo) throws Exception;
	public CommentDTO getCommentUser(Long commentNo) throws Exception;
	public CommentUserDTO getCommentUserList(Long commentNo) throws Exception;
}
