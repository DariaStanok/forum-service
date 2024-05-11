package telran.java52.forum.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForumDto {
	 String id;
	 String title;
	 String content;
	 String author; 
	 LocalDate dateCreated;
	 @Singular
	 Set <String> tags;
	 Long likes;
	 @Singular
	 List<CommentDto>comments;
	
}
