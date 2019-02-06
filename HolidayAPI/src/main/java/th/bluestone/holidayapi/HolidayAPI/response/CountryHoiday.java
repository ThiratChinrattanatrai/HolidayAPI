package th.bluestone.holidayapi.HolidayAPI.response;

import java.util.List;

public class CountryHoiday {
	String status;
	List<String> holidays;
	
	public CountryHoiday() {
		super();
	}

	public CountryHoiday(String status) {
		super();
		this.status = status;
	}

	public CountryHoiday(String status, List<String> holidays) {
		super();
		this.status = status;
		this.holidays = holidays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getHolidays() {
		return holidays;
	}

	public void setHolidays(List<String> holidays) {
		this.holidays = holidays;
	}

	@Override
	public String toString() {
		return "CountryHoiday [status=" + status + ", holidays=" + holidays + "]";
	}


	

	
}
