package telran.java52.forum.dto.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ForumNotFoundExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
