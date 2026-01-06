package com.theralink.domain.basic;

import com.theralink.domain.user.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticatedUserInfo {
	private UserPrincipal userPrincipal;
	private String        token;

}
