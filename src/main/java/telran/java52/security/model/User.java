package telran.java52.security.model;

import java.security.Principal;
import java.util.Set;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User implements Principal{
	private String name;
	@Singular
	private Set<String> roles;
	
	public static  User getUserFromPrincipal(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal instanceof User) {
            return (User) principal;      
        } else {
            throw new IllegalStateException("Principal is not an instance of User");
        }
    }

}