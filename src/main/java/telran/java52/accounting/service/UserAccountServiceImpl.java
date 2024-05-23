package telran.java52.accounting.service;


import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import telran.java52.accounting.dao.UserAccountRepository;
import telran.java52.accounting.dto.RolesDto;
import telran.java52.accounting.dto.UserDto;
import telran.java52.accounting.dto.UserEditDto;
import telran.java52.accounting.dto.UserRegisterDto;
import telran.java52.accounting.dto.exeptions.IncorrectRoleExeption;
import telran.java52.accounting.dto.exeptions.UserAccountNotFoundExeption;
import telran.java52.accounting.dto.exeptions.UserExistExeption;
import telran.java52.accounting.model.Role;
import telran.java52.accounting.model.UserAccount;


@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService, CommandLineRunner{

	final UserAccountRepository userAccountRepository;
	final ModelMapper modelMapper;
	

	// TODO Don't testing
	@Override	
	public UserDto register(UserRegisterDto userRegisterDto) {
		if (userAccountRepository.existsByLogin(userRegisterDto.getLogin())) {
	        throw new UserExistExeption();
	    }
		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
		String password = BCrypt.hashpw(userRegisterDto.getPassword(), BCrypt.gensalt());
		userAccount.setPassword(password);
		userAccount = userAccountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}
	@Override
	public UserDto getUser(String login) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserAccountNotFoundExeption::new);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto removeUser(String login) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserAccountNotFoundExeption::new);
		userAccountRepository.deleteById(login);
		return modelMapper.map(userAccount, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserAccountNotFoundExeption::new);
		userAccount.setFirstName(userEditDto.getFirstName());
		userAccount.setLastName(userEditDto.getLastName());
		userAccount = userAccountRepository.save(userAccount);
		return modelMapper.map(userAccount, UserDto.class);
	}


	public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserAccountNotFoundExeption::new);
		boolean res;
		  try {
			   if (isAddRole) {
			    res = userAccount.addRole(role);
			   } else {
			    res = userAccount.removeRole(role);
			   }
			   if (res) {
			    userAccountRepository.save(userAccount);
			   } 
			  } catch (Exception e) {
			   throw new IncorrectRoleExeption();
			  }

	    userAccount = userAccountRepository.save(userAccount);
	    return modelMapper.map(userAccount, RolesDto.class);
	}

	// TODO Don't testing
	@Override
	public void changePassword(String login, String newPassword) {
		 UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserAccountNotFoundExeption::new);
		 String password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		 userAccount.setPassword(password);
		 userAccountRepository.save(userAccount);
		
	}
	@Override
	public void run(String... args) throws Exception {
		if (!userAccountRepository.existsById("admin")) {
			String password = BCrypt.hashpw("admin", BCrypt.gensalt());
			UserAccount userAccount = new UserAccount("admin", "", "", password);
			userAccount.addRole(Role.MODERATOR.name());
			userAccount.addRole(Role.ADMINISTRATOR.name());
			userAccountRepository.save(userAccount);
		}

	}

}
