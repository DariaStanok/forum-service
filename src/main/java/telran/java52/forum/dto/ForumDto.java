package telran.java52.forum.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class ForumDto {
	 String id;
	 String title;
	 String content;
	 String author; 
	 Date dateCreated;
	 List<String> tags;
	 Long likes;
	 List<CommentDto>comments;
	
}
