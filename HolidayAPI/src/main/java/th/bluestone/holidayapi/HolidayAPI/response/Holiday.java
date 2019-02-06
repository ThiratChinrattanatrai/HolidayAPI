package th.bluestone.holidayapi.HolidayAPI.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Holiday {
	String name;
	String date;
	String observed;
	String _public;
	
	public Holiday() {
		super();
	}

	public Holiday(String name, String date, String observed, String _public) {
		super();
		this.name = name;
		this.date = date;
		this.observed = observed;
		this._public = _public;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getObserved() {
		return observed;
	}

	public void setObserved(String observed) {
		this.observed = observed;
	}

	@JsonProperty("public")
	public String get_public() {
		return _public;
	}

	@JsonProperty("public")
	public void set_public(String _public) {
		this._public = _public;
	}

	@Override
	public String toString() {
		return "Holiday [name=" + name + ", date=" + date + ", observed=" + observed + ", _public=" + _public + "]";
	}
	
}
