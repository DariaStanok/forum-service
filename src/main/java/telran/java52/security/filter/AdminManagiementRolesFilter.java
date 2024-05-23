package telran.java52.security.filter;

import java.io.IOException;
import java.util.Set;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import telran.java52.accounting.dao.UserAccountRepository;
import telran.java52.accounting.model.Role;
import telran.java52.accounting.model.UserAccount;

@Component
@RequiredArgsConstructor
@Order(20)
public class AdminManagiementRolesFilter implements Filter {
//principal available
	final UserAccountRepository userAccountRepository;
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if (checkEndpoint(request.getMethod(), request.getServletPath())) {
	        try {
	            String login = request.getUserPrincipal().getName();
	            UserAccount userAccount = userAccountRepository.findById(login)
	            		.orElseThrow(RuntimeException::new);
	            if (!hasAdminRole(userAccount.getRoles())) {
	            	throw new RuntimeException();
	            }
	        } catch (Exception e) {
	        	response.sendError(401);
				return;
	        }
	    }
	    chain.doFilter(request, response);
	}
	private boolean checkEndpoint(String method, String path) {
		return !(HttpMethod.POST.matches(method) && path.matches("/user/{login}/role/{role}"));
	}

	private boolean hasAdminRole(Set<Role> roles) {
	    for (Role role : roles) {
	        if ("ADMIN".equals(role.name())) {
	            return true;
	        }
	    }
	    return false;
	}
}
