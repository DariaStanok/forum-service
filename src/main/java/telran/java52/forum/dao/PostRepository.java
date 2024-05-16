package telran.java52.forum.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import telran.java52.forum.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
	Stream<Post> findByAuthorIgnoreCase(String author);
	
	Stream<Post> findByTagsIn(List<String> tags);
	
	@Query("{ 'dateCreated' : { $gte: ?0, $lte: ?1 }}")
	Stream<Post> findByDateRangeIn(LocalDate dateFrom, LocalDate dateTo);
}
