package com.theralink.service.user;

import com.theralink.domain.user.User;
import com.theralink.service.IGenericService;
import com.theralink.web.viewModel.user.ChangePasswordDto;
import com.theralink.web.viewModel.user.UserOrgRoleDto;
import com.theralink.web.viewModel.user.UserViewModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService extends IGenericService<User, String> {
    UserDetails loadUserByUsername(String username);

    UserDetails loadUserByUsernameForAuthenticate(String username, String jwt) throws UsernameNotFoundException;

    List<UserOrgRoleDto> authenticatedUserAuthoritiesList();

	List<String> authenticatedUserRoles();

	String save(User user);
    String signUp(UserViewModel userViewModel);

    boolean unLock(String id);

    boolean checkUserNameExists(String username);

    boolean changePassword(ChangePasswordDto  changePasswordDto);

	boolean changePasswordByAdmin(ChangePasswordDto entity);
}
