package telran.java52.accounting.dto.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserAccountNotFoundExeption extends RuntimeException {

	private static final long serialVersionUID = -2489096744241104716L;
	

}
