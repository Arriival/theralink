package com.theralink.web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConsultantSessionSumDto {
	private Float consultantFee;
	private Float customerFee;

	Long time;

	public ConsultantSessionSumDto(Float consultantFee, Float customerFee, Long time) {
		this.consultantFee = consultantFee;
		this.customerFee = customerFee;
		this.time = time;
	}

	/*	private Date  start;
	private Date  end;

	public long getDuration() {
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
	}*/
}
