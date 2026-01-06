package com.theralink.config.session;

import com.theralink.domain.basic.AuthenticatedUserInfo;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SessionStorage {

	@Value("${app.session.expireTime}")
	private static Long expireTime;

	private static final Map<String, AuthenticatedUserInfo> session = new ConcurrentHashMap<>();

	public static void put(String key, AuthenticatedUserInfo authenticatedUserInfo) {
		System.out.println("put : " + key + "  -  " + authenticatedUserInfo.toString());
		session.putIfAbsent(key, authenticatedUserInfo);
	}

	public static AuthenticatedUserInfo get(String key) {
		System.out.println("get : " + key);
		return session.get(key);
	}

	public static Optional<AuthenticatedUserInfo> getIfExist(String key) {
		if (session.containsKey(key)) {
			System.out.println("get exist : " + key);
			return Optional.ofNullable(session.get(key));
		}
		else {
			return Optional.empty();
		}
	}

	public static Boolean exist(String key) {
		return session.containsKey(key);
	}

	public static void remove(String key) {
		session.remove(key);
	}

}
