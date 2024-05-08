package telran.java52.forum.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class CommentDto {
	String id;
	String user;
	String message;
	Date dateCreated;
	Long likes;
}
