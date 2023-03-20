package com.web.viewModel;

import com.core.framework.utils.DateUtility;
import com.core.framework.web.viewModel.BaseEntityViewModel;
import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SecretaryWorkTimeViewModel extends BaseEntityViewModel<String> {

	private String  secretaryId;
	private String  secretaryFirstName;
	private String  secretaryLastName;
	private Date    start;
	private Date    end;
	private String  jalaliDate;
	private String  startTime;
	private String  endTime;
	private Integer duration;
	private Float salary;

	public String getJalaliDate() {
		return DateUtility.miladiToJalali(this.start);
	}

	public String getStartTime() {
		if (this.start == null) {
			return null;
		}
		return DateUtility.getTimeFromDate(this.start);
	}

	public String getEndTime() {
		if (this.end == null) {
			return null;
		}
		return DateUtility.getTimeFromDate(this.end);
	}

	public Long getDuration() {
		long diff = 0;
		if (end == null) {
			Date current = new Date();
			diff = current.getTime() - start.getTime();
		}
		else {
			diff = end.getTime() - start.getTime();
		}
		long diffMinutes = (diff / 1000) / 60;
		return diffMinutes;
	}
}
