package th.bluestone.holidayapi.HolidayAPI.response;

import java.util.List;

public class HolidayResponse {
	String status;
	String error;
	String date;
	List<Holiday> holidays;
	
	public HolidayResponse() {
		super();
	}

	public HolidayResponse(String status, String error, String date, List<Holiday> holidays) {
		super();
		this.status = status;
		this.error = error;
		this.date = date;
		this.holidays = holidays;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public List<Holiday> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<Holiday> holidays) {
		this.holidays = holidays;
	}

	@Override
	public String toString() {
		return "HolidayResponse [status=" + status + ", error=" + error + ", date=" + date + ", holidays=" + holidays
				+ "]";
	}

	
	
	
}
