package telran.java52.forum.service;

import java.util.List;

import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.DateRangeDto;
import telran.java52.forum.dto.ForumAddDto;
import telran.java52.forum.dto.ForumDto;


public interface ForumService {
	
	ForumDto addPost (String user, ForumAddDto forumAddDto);
	
	ForumDto findPostById (String postId);
	
	void addLikeToPost (String postId);
	
	List <ForumDto> findPostByAuthor (String user);
	
	ForumDto addComment (String postId, String user, CommentAddDto commentAddDto);
	
	ForumDto deletePost (String postId);
	
	List<ForumDto> findPostsByTags (List<String>tags);
	
	List<ForumDto> findPostsByPeriod (DateRangeDto dateRangeDto);
	
	ForumDto updatePost (String postId, ForumAddDto forumAddDto);
	
}
