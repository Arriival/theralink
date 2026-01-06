package com.theralink.service.user;

import com.theralink.common.exception.ApplicationException;
import com.theralink.common.exception.UnAuthorizeException;
import com.theralink.common.mapping.ModelMapperUtil;
import com.theralink.config.session.SessionStorage;
import com.theralink.domain.Action;
import com.theralink.domain.Group;
import com.theralink.domain.Person;
import com.theralink.domain.UserOrganizationStructureRole;
import com.theralink.domain.basic.AuthenticatedUserInfo;
import com.theralink.domain.user.User;
import com.theralink.domain.user.UserMapper;
import com.theralink.repository.IGenericRepository;
import com.theralink.repository.organizationStructure.IOrganizationStructureRepository;
import com.theralink.repository.user.IUserRepository;
import com.theralink.service.GenericService;

import com.theralink.service.actionGroup.IActionGroupService;
import com.theralink.service.person.IPersonService;
import com.theralink.service.user.IUserService;
import com.theralink.service.userGroup.IUserGroupService;
import com.theralink.utils.HashUtil;
import com.theralink.utils.SecurityUtil;
import com.theralink.web.viewModel.user.ChangePasswordDto;
import com.theralink.web.viewModel.user.UserOrgRoleDto;
import com.theralink.web.viewModel.user.UserViewModel;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends GenericService<User, String> implements IUserService, UserDetailsService {

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private IUserGroupService iUserGroupService;

	@Autowired
	private IActionGroupService iActionGroupService;

	@Autowired
	private IPersonService iPersonService;

	@Autowired
	private IOrganizationStructureRepository iOrganizationStructureRepository;

	@Override
	protected IGenericRepository<User, String> getGenericRepo() {
		return iUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = iUserRepository.findByUserName(username);
		if (user == null) {
			throw new UnAuthorizeException("Incorrect Credentials", 10);
		}
		return getUserDetails(user);
	}

	private UserDetails getUserDetails(User user) {
		List<Group> groups = iUserGroupService.loadByUser(user.getId());
		List<Action> actionsList = new ArrayList<>();
		groups.forEach(g -> {
			List<Action> actions = iActionGroupService.loadActionsByGroup(g.getId());
			actionsList.addAll(actions);
		});
		Set<Action> set = new HashSet<>(actionsList);
		actionsList.clear();
		actionsList.addAll(set);
		user.setActions(actionsList);
		Person person = iPersonService.load(user.getPerson().getId());
		user.setPerson(person);
		return UserMapper.userToPrincipal(user);
	}

	@Override
	public UserDetails loadUserByUsernameForAuthenticate(String username, String jwt) throws UsernameNotFoundException {
		Optional<AuthenticatedUserInfo> optionalAuthenticatedUserInfo = SessionStorage.getIfExist(username);
		if (optionalAuthenticatedUserInfo.isPresent()) {
			return optionalAuthenticatedUserInfo.get().getUserPrincipal();
		}
		else {
			User user = iUserRepository.findByUserName(username);
			if (user == null) {
				throw new UnAuthorizeException("User Not Found.", 10);
			}
			if (!user.isActivated()) {
				throw new UnAuthorizeException("User " + username + " was not activated", 11);
			}
			if (user.isLock()) {
				throw new UnAuthorizeException("User " + username + " Locked.", 12);
			}
			return getUserDetails(user);
		}
	}

	@Override
	public List<UserOrgRoleDto> authenticatedUserAuthoritiesList() {
		return getUserOrgRoles(SecurityUtil.getAuthenticatedUser().getStructureRoles());
	}

	public List<UserOrgRoleDto> getUserOrgRoles(List<? extends UserOrganizationStructureRole> structureRoleSet) {

		List<UserOrganizationStructureRole> roles = iOrganizationStructureRepository.findByUserWithOrg(SecurityUtil.getAuthenticatedUser().getId());
		if (structureRoleSet == null) {
			return List.of();
		}

		/*return structureRoleSet.stream().filter(uosr -> uosr.getOrganizationStructure() != null).collect(Collectors.groupingBy(uosr -> uosr.getOrganizationStructure().getId(), // کلید = orgId
				Collectors.toList())).entrySet().stream().map(entry -> {
			String orgId = entry.getKey();
			String orgName = entry.getValue().get(0).getOrganizationStructure().getName(); // نام سازمان از اولین عنصر
			List<String> authorities = entry.getValue().stream().map(uosr -> "ROLE_" + uosr.getRole().name()) // تبدیل Enum به String
					.collect(Collectors.toList());

			UserOrgRoleDto dto = new UserOrgRoleDto();
			dto.setOrganizationId(orgId);
			dto.setOrganizationName(orgName);
			dto.setAuthorities(authorities);
			return dto;
		}).collect(Collectors.toList());*/

		return roles.stream()
				.collect(Collectors.groupingBy(uosr -> uosr.getOrganizationStructure().getId()))
				.entrySet().stream()
				.map(entry -> {
					String orgId = entry.getKey();
					String orgName = entry.getValue().get(0).getOrganizationStructure().getName();
					List<String> authorities = entry.getValue().stream()
							.map(uosr -> "ROLE_" + uosr.getRole().name())
							.collect(Collectors.toList());

					UserOrgRoleDto dto = new UserOrgRoleDto();
					dto.setOrganizationId(orgId);
					dto.setOrganizationName(orgName);
					dto.setAuthorities(authorities);
					return dto;
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<String> authenticatedUserRoles() {
		return SecurityUtil.getAuthenticatedUserRoles().stream().map(r -> r.name()).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public String save(User user) {
		if (user.getId() == null) {
			user.setPassword(HashUtil.hashPassword(user.getPassword()));
			user.getStructureRoleSet().forEach(r -> {
				r.setUser(user);
			});
		}
		else {
			User currentUser = iUserRepository.findById(user.getId()).get();
			user.setPassword(currentUser.getPassword());
		}

		/*user.getRoles().forEach(r -> {
			if (!SecurityUtil.hasCurrentUserThisAuthority(r.name())) {
				throw new ApplicationException("دسترسی غیر مجاز به نقش");
			}
		});*/
		return super.save(user);
	}

	@Transactional
	@Override
	public String signUp(UserViewModel entity) {
		User user = ModelMapperUtil.map(entity, User.class);
		if (user.getId() == null) {
			user.setPassword(HashUtil.hashPassword(entity.getPassword()));
			Person person = new Person();
			person.setFirstName(entity.getFirstName());
			person.setLastName(entity.getLastName());
			String entityId = iPersonService.save(person);
			person.setId(entityId);
			user.setPerson(person);
		}
		return super.save(user);
	}

	@Override
	@Transactional
	public boolean unLock(String id) {
		User user = iUserRepository.findById(id).get();
		user.setLock(false);
		super.save(user);
		return true;
	}

	@Override
	public boolean checkUserNameExists(String username) {
		User suggestUserName = iUserRepository.findByUserName(username);
		if (suggestUserName == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	@Transactional
	public boolean changePassword(ChangePasswordDto changePasswordDto) {
		String authenticatedUserId = SecurityUtil.getAuthenticatedUserId();
		User currentUser = load(authenticatedUserId);
		if (currentUser.getPassword().equals(HashUtil.hashPassword(changePasswordDto.getOldPass()))) {
			if (changePasswordDto.getNewPass().equals(changePasswordDto.getRePass())) {
				currentUser.setPassword(HashUtil.hashPassword(changePasswordDto.getNewPass()));
				super.save(currentUser);
				return true;
			}
			else {
				throw new ApplicationException("پسورد جدید با تکرار آن مطابقت ندارد.");
			}
		}
		else {
			throw new ApplicationException("گذرواژه اشتباه است.");
		}
	}

	@Override
	@Transactional
	public boolean changePasswordByAdmin(ChangePasswordDto changePasswordDto) {
		String authenticatedUserId = changePasswordDto.getUserId();
		User currentUser = load(authenticatedUserId);
		if (changePasswordDto.getNewPass().equals(changePasswordDto.getRePass())) {
			currentUser.setPassword(HashUtil.hashPassword(changePasswordDto.getNewPass()));
			super.save(currentUser);
			return true;
		}
		else {
			throw new ApplicationException("پسورد جدید با تکرار آن مطابقت ندارد.");
		}
	}

}
