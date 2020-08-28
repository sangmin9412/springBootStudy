package springBootTest2.service.comment;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springBootTest2.command.AuthInfo;
import springBootTest2.command.CommentCommand;
import springBootTest2.domain.CommentDTO;
import springBootTest2.domain.ReplyDTO;
import springBootTest2.mapper.CommentMapper;

@Component
@Service
@Transactional
public class CommentWriteService {
	@Autowired
	CommentMapper commentMapper;
	public void commentWrite(
				CommentCommand commentCommand,
				HttpSession session
			) throws Exception {
		String userId = ((AuthInfo) session.getAttribute("authInfo")).getId();
		CommentDTO dto = new CommentDTO(
					null, 
					userId, 
					null, 
					commentCommand.getCommentSubject(), 
					commentCommand.getCommentContent()
				);
		commentMapper.insertComment(dto);
	}
	public void replyInsert(Long commentNo, String cuserId, String replyContent, HttpSession session) throws Exception {
		String userId = ((AuthInfo) session.getAttribute("authInfo")).getId();
		ReplyDTO replyDTO = new ReplyDTO();
		replyDTO.setCommentNo(commentNo);
		replyDTO.setCuserId(cuserId);
		replyDTO.setReplyContent(replyContent);
		replyDTO.setRuserId(userId);
		commentMapper.replyInsert(replyDTO);
	}
	
}
