package telran.java52.forum.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java52.forum.dao.PostRepository;
import telran.java52.forum.dto.CommentAddDto;
import telran.java52.forum.dto.DateRangeDto;
import telran.java52.forum.dto.ForumAddDto;
import telran.java52.forum.dto.ForumDto;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLikeToPost(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ForumDto> findPostByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ForumDto addComment(String id, String author, CommentAddDto commentAddDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ForumDto deletePost(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ForumDto> findPostsByTags(List<String> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ForumDto> findPostsByPeriod(DateRangeDto dateRangeDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ForumDto updatePost(String id, ForumAddDto forumAddDto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
