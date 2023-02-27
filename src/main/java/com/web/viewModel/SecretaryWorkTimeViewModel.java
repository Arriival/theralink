package com.web.viewModel;

import com.core.framework.utils.DateUtility;
import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SecretaryWorkTimeViewModel extends BaseEntityViewModel<String> {

	private String    secretaryId;
	private String    secretaryFirstName;
	private String    secretaryLastName;
	private Timestamp start;
	private Timestamp end;
	private String    jalaliDate;
	private String    startTime;
	private String    endTime;
	private Integer   duration;

	public String getJalaliDate() {
		return DateUtility.miladiToJalali(this.start);
	}

	public String getStartTime() {
		if (this.start == null) {
			return null;
		}
		return DateUtility.getTimeFromTimestamp(this.start);
	}

	public String getEndTime() {
		if (this.end == null) {
			return null;
		}
		return DateUtility.getTimeFromTimestamp(this.end);
	}

	public Integer getDuration() {
		if (this.end == null || this.start == null) {
			return null;
		}
		long milliseconds = this.end.getTime() - this.start.getTime();
		int seconds = (int) milliseconds / 1000;
		return seconds / 60;
	}
}
