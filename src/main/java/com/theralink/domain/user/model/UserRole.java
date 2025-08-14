package com.theralink.domain.user.model;

import lombok.Getter;

@Getter
public enum UserRole {
	ADMIN,          // مدیر اصلی مرکز (مثلاً مدیریت کل مرکز)
	COUNSELOR,      // مشاور
	SECRETARY,      // منشی
	CLIENT,         // مراجع
	OWNER
}
