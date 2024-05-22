package telran.java52.accounting.dto.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistExeption extends RuntimeException {

	private static final long serialVersionUID = 5716031225618528467L;


}
