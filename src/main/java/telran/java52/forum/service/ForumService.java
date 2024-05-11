package telran.java52.forum.service;

import java.util.List;

import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.DateRangeDto;
import telran.java52.forum.dto.ForumAddDto;
import telran.java52.forum.dto.ForumDto;


public interface ForumService {
	
	ForumDto addPost (String author, ForumAddDto forumAddDto);
	
	ForumDto findPostById (String id);
	
	void addLikeToPost (String id);
	
	List <ForumDto> findPostByAuthor (String author);
	
	ForumDto addComment (String id, String author, CommentAddDto commentAddDto);
	
	ForumDto removePost (String id);
	
	List<ForumDto> findPostsByTags (List<String>tags);
	
	List<ForumDto> findPostsByPeriod (DateRangeDto dateRangeDto);
	
	ForumDto updatePost (String id, ForumAddDto forumAddDto);
	
}
