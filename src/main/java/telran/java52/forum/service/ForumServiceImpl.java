package telran.java52.forum.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.forum.dao.PostRepository;
import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.DateRangeDto;
import telran.java52.forum.dto.ForumAddDto;
import telran.java52.forum.dto.ForumDto;
import telran.java52.forum.dto.exeptions.ForumNotFoundExeption;
import telran.java52.forum.model.Comment;
import telran.java52.forum.model.Post;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService  {
 
	final PostRepository postRepository;
	final ModelMapper modelMapper;
	
	@Override
	public ForumDto addPost(String author, ForumAddDto forumAddDto) {
		Post post = modelMapper.map(forumAddDto, Post.class);
		post.setAuthor(author);
		post = postRepository.save(post); 
		return modelMapper.map(post, ForumDto.class);
	}

	@Override
	public ForumDto findPostById(String id) {
		Post post = postRepository.findById(id).orElseThrow(ForumNotFoundExeption::new);
		return modelMapper.map(post, ForumDto.class);
	}

	@Override
	public void addLikeToPost(String id) {
		Post post = postRepository.findById(id).orElseThrow(ForumNotFoundExeption::new);
		post.addLike();
		post = postRepository.save(post); 
	}

	@Override
	public ForumDto addComment(String id, String author, CommentAddDto commentAddDto) {
		Post post = postRepository.findById(id).orElseThrow(ForumNotFoundExeption::new);
		Comment comment = new Comment(author, commentAddDto.getMessage());
		post.addComment(comment);
		post = postRepository.save(post);
		return modelMapper.map(post,ForumDto.class);
	}

	@Override
	public ForumDto removePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(ForumNotFoundExeption::new);
		postRepository.delete(post);
		return modelMapper.map(post, ForumDto.class);
	}
		


	@Override
	public List<ForumDto> findPostsByTags(List<String> tags) {
		return postRepository.findByTagsIn(tags)
				.map(post -> modelMapper.map(post, ForumDto.class))
				.toList();
	}


	@Override
	public List<ForumDto> findPostByAuthor(String author) {
		return postRepository.findByAuthorIgnoreCase(author)
				.map(post -> modelMapper.map(post, ForumDto.class))
				.toList();
	}
	
	@Override
	public List<ForumDto> findPostsByPeriod(DateRangeDto dateRangeDto) {
		LocalDateTime dayStarting = dateRangeDto.getDateFrom().atStartOfDay(); 
        LocalDateTime dayEnding = dateRangeDto.getDateTo().atTime(LocalTime.MAX);
		return postRepository.findByDateRangeIn(dateRangeDto)
				.filter(post -> post.getDateCreated().isAfter(dayStarting) && post.getDateCreated().isBefore(dayEnding))
	            .map(post -> modelMapper.map(post, ForumDto.class))
	            .toList();
	}


	@Override
	public ForumDto updatePost(String id, ForumAddDto forumAddDto) {
		Post post = postRepository.findById(id).orElseThrow(ForumNotFoundExeption::new);
		String content = forumAddDto.getContent();
		String title = forumAddDto.getTitle();
		Set <String> tags = forumAddDto.getTags();
		if(content != null) {
			 post.setContent(content);
		}
		if (title != null) {
			post.setTitle(title);
		}
		if (tags != null) {
			tags.forEach(post::addTag);
		}
		post = postRepository.save(post);
		return modelMapper.map(post, ForumDto.class);
	}
}
