package telran.java52.accounting.service;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import telran.java52.accounting.dao.UserAccountRepository;
import telran.java52.accounting.dto.RolesDto;
import telran.java52.accounting.dto.UserDto;
import telran.java52.accounting.dto.UserEditDto;
import telran.java52.accounting.dto.UserRegisterDto;
import telran.java52.accounting.dto.exeptions.UserAccountNotFoundExeption;
import telran.java52.accounting.model.UserAccount;


@Service
@RequiredArgsConstructor
public class UserAccountSreviceImpl implements UserAccountService{

	final UserAccountRepository userAccountRepository;
	final ModelMapper modelMapper;
	

	// TODO Don't testing
	@Override	
	public UserDto register(UserRegisterDto userRegisterDto) {
		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
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
	    if (isAddRole) {
	        if (!userAccount.getRoles().contains(role)) {
	            userAccount.addRole(role);
	        } else {
	            isAddRole = false; 
	        }
	    } else {
	        if (userAccount.getRoles().contains(role)) {
	            userAccount.removeRole(role);
	        } else {
	            isAddRole = true; 
	        }
	    }
	    userAccount = userAccountRepository.save(userAccount);
	    return modelMapper.map(userAccount, RolesDto.class);
	}

	// TODO Don't testing
	@Override
	public void changePassword(String login, String newPassword) {
		 UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserAccountNotFoundExeption::new); 
		 userAccount.setPassword(newPassword);
		 userAccountRepository.save(userAccount);
		
	}

}
