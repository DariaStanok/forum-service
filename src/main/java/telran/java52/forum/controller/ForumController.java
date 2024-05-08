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
public class ForumController {

	ForumService forumService;

	@PostMapping("/forum/post/{user}")
	public ForumDto addPost( @PathVariable String user,@RequestBody ForumAddDto forumAddDto) {
		return forumService.addPost(user, forumAddDto);
	}

	@GetMapping ("/forum/post/{postId}")
	public ForumDto findPostById(@PathVariable String postId) {
		return forumService.findPostById(postId);
	}

	@PutMapping("/forum/post/{postId}/like")
	public void addLikeToPost(@PathVariable String postId) {
		forumService.addLikeToPost(postId);
		
	}

	@GetMapping("/forum/posts/author/{user}")
	public List<ForumDto> findPostByAuthor(@PathVariable String user) {
		return forumService.findPostByAuthor(user);
	}

	@PutMapping("/forum/post/{postId}/comment/{user}")
	public ForumDto addComment(@PathVariable String postId, @PathVariable String user,@RequestBody CommentAddDto commentAddDto) {
		return forumService.addComment(postId, user, commentAddDto);
	}

	@DeleteMapping ("/forum/post/{postId}")
	public ForumDto deletePost(@PathVariable String postId) {
		return forumService.deletePost(postId);
	}

	@PostMapping ("/forum/posts/tags")
	public List<ForumDto> findPostsByTags(@RequestBody List<String> tags) {
		return forumService.findPostsByTags(tags);
	}

	@PostMapping("/forum/posts/period")
	public List<ForumDto> findPostsByPeriod(@RequestBody DateRangeDto dateRangeDto) {
		return forumService.findPostsByPeriod(dateRangeDto);
	}

	@PutMapping("/forum/post/{postId}")
	public ForumDto updatePost(@PathVariable String postId,@RequestBody ForumAddDto forumAddDto) {
		return forumService.updatePost(postId, forumAddDto);
	}
	
	
	
}
