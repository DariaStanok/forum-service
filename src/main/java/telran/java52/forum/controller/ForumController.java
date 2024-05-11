package telran.java52.forum.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.DateRangeDto;
import telran.java52.forum.dto.ForumAddDto;
import telran.java52.forum.dto.ForumDto;
import telran.java52.forum.service.ForumService;

@RestController
@RequiredArgsConstructor
// or @RequestMapping ("/forum")=>("/post/{user}")
public class ForumController {

	final ForumService forumService;

	@PostMapping("/forum/post/{author}")
	public ForumDto addPost( @PathVariable String author,@RequestBody ForumAddDto forumAddDto) {
		return forumService.addPost(author, forumAddDto);
	}

	@GetMapping ("/forum/post/{id}")
	public ForumDto findPostById(@PathVariable String id) {
		return forumService.findPostById(id);
	}

	@PutMapping("/forum/post/{id}/like")
	public void addLikeToPost(@PathVariable String id) {
		forumService.addLikeToPost(id);
		
	}

	@GetMapping("/forum/posts/author/{author}")
	public List<ForumDto> findPostByAuthor(@PathVariable String author) {
		return forumService.findPostByAuthor(author);
	}

	@PutMapping("/forum/post/{id}/comment/{author}")
	public ForumDto addComment(@PathVariable String id, @PathVariable String author,@RequestBody CommentAddDto commentAddDto) {
		return forumService.addComment(id, author, commentAddDto);
	}

	@DeleteMapping ("/forum/post/{id}")
	public ForumDto deletePost(@PathVariable String id) {
		return forumService.deletePost(id);
	}

	@PostMapping ("/forum/posts/tags")
	public List<ForumDto> findPostsByTags(@RequestBody List<String> tags) {
		return forumService.findPostsByTags(tags);
	}

	@PostMapping("/forum/posts/period")
	public List<ForumDto> findPostsByPeriod(@RequestBody DateRangeDto dateRangeDto) {
		return forumService.findPostsByPeriod(dateRangeDto);
	}

	@PutMapping("/forum/post/{id}")
	public ForumDto updatePost(@PathVariable String id,@RequestBody ForumAddDto forumAddDto) {
		return forumService.updatePost(id, forumAddDto);
	}
	
	
	
}
