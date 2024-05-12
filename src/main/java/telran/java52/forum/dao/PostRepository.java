package telran.java52.forum.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.java52.forum.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	Stream<Post> findByAuthorIgnoreCase(String author);
	
	Stream<Post> findByTagsIn(List<String> tags);
	
}
