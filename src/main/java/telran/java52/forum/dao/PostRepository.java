package telran.java52.forum.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.java52.forum.dto.DateRangeDto;
import telran.java52.forum.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	Stream<Post> findByAuthorIgnoreCase(String author);
	
	Stream<Post> findByTagsIn(List<String> tags);
	
	@Query("{ 'date' : { $gte: ?0.from, $lte: ?0.to }}")
	Stream<Post> findByDateRangeIn(DateRangeDto dateRangeDto);
}
