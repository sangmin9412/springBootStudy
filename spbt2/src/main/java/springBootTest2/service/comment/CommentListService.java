package springBootTest2.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import springBootTest2.controller.PageAction;
import springBootTest2.domain.CommentDTO;
import springBootTest2.domain.CommentRepliesDTO;
import springBootTest2.domain.CommentUserDTO;
import springBootTest2.domain.StartEndPageDTO;
import springBootTest2.mapper.CommentMapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Component
@Service
public class CommentListService {
	@Autowired
	CommentMapper commentMapper;

	public void execute(Model model, Integer page) throws Exception {
		int limit = 10;
		int limitPage = 10;
		
		Long startRow = ((long) page - 1) * limit + 1;
		Long endRow = startRow + limit - 1;
		
		CommentDTO dto = new CommentDTO();
		dto.setStartEndPageDTO(new StartEndPageDTO(startRow, endRow));
		List<CommentDTO> lists = commentMapper.getCommentList(dto);
		int count = commentMapper.getCommentCount();
		
		model.addAttribute("comments", lists);
		model.addAttribute("count", count);
		
		PageAction pageAction = new PageAction();
		pageAction.page(model, count, limit, limitPage, page, "commentList");
		
	}

	public String commentDetail(Model model, Long commentNo) throws Exception {
		String path = "";
		/*
		CommentDTO dto = new CommentDTO();
		dto.setCommentNo(commentNo);
		dto = commentMapper.getCommentReplies(dto);
		model.addAttribute("dto", dto);
		path = "thymeleaf/comment/comment_collection";
		*/
		
		CommentRepliesDTO replies = commentMapper.commentRepliesCollection(commentNo);
		model.addAttribute("replies", replies);
		path = "thymeleaf/comment/comment_collection1";
		return path;
	}

	public String writerInfo(Long commentNo, Model model) throws Exception {
		String path = "";
		/*
		CommentDTO dto = commentMapper.getCommentUser(commentNo);
		model.addAttribute("writer", dto);
		path = "thymeleaf/comment/writerInfo";
		*/
		CommentUserDTO dto = commentMapper.getCommentUserList(commentNo);
		model.addAttribute("writer", dto);
		path = "thymeleaf/comment/writerInfo1";
		return path;
	}
	
}
