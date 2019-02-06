package th.bluestone.holidayapi.HolidayAPI.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.bluestone.holidayapi.HolidayAPI.exception.BusinessException;
import th.bluestone.holidayapi.HolidayAPI.response.CountryHoiday;
import th.bluestone.holidayapi.HolidayAPI.response.Holiday;
import th.bluestone.holidayapi.HolidayAPI.response.HolidayResponse;
import th.bluestone.holidayapi.HolidayAPI.util.HolidayUtil;

import static th.bluestone.holidayapi.HolidayAPI.constants.ErrorConstants.*;

@RestController
@RequestMapping("/holiday")
public class HolidayController {
	
	@Value("${country.max:2}")
	String maxCountry;
	
	@Value("${country.avail}")
	String availCountry;
	
	@Value("${api.holiday.endpoint}")
	String apiEndpoint;
	
	@Value("${api.holiday.key:NOT_PROVIDE_KEY}")
	String apiKey;
	
	@Autowired
	HolidayUtil holidayUtil;
	
	private static Logger logger = LoggerFactory.getLogger(HolidayController.class);
	 
	@GetMapping("/search")
	public HashMap<String, Object> searchHoliday(@RequestParam(name = "countries") String countries,
			@RequestParam(name = "date") String date) {
		logger.info("serch holiday with %s,%s",countries,date);
		HashMap<String, Object> response = new HashMap<String, Object>();
		try {
			if(apiKey.equals("NOT_PROVIDE_KEY")) {
				throw new BusinessException(NOTPROVIDE_API_KEY_CODE,NOTPROVIDE_API_KEY_MSG);
			}
			String[] countryArr = countries.split(",");
			
			if (countryArr.length > Integer.parseInt(maxCountry)) {
				throw new BusinessException(COUNTRY_EXCEED_CODE,String.format(COUNTRY_EXCEED_MSG, maxCountry));
			}

			Calendar calendar = holidayUtil.getCalendar(date, "yyyy-MM-dd");
			response.put("date",date);
			for (String country : countryArr) {
				try {
					if(!availCountry.contains(country)) {
						throw new BusinessException(COUNTRY_NOT_AVBL_CODE, String.format(COUNTRY_NOT_AVBL_MSG, country));
					}
					
					HashMap<String,String> queryMap = new HashMap<String,String>();
					queryMap.put("country",country);
					queryMap.put("year",String.valueOf(calendar.get(Calendar.YEAR)));
					queryMap.put("month",String.valueOf(calendar.get(Calendar.MONTH) + 1));
					queryMap.put("day",String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
					queryMap.put("key",apiKey);
					
					HolidayResponse restResponse = holidayUtil.callPublicAPI(apiEndpoint,queryMap);
					
					List<String> holidaysNameArr = new ArrayList<String>();
							
					for(Holiday holiday : restResponse.getHolidays()) {
						holidaysNameArr.add(holiday.getName());
					}
					
					response.put(country, new CountryHoiday("SUCCESS", holidaysNameArr));				
					logger.error(String.format("country : %s success",country));
				}catch(BusinessException e) {	
					logger.error("Error : " + e.getMessage());
					response.put(country,new CountryHoiday(e.getMessage()));
				}
			}
			
		}catch (BusinessException e) {
			logger.error("Error : " + e.getMessage());
			response.put("error",e.getMessage());
		}catch(Exception e) {
			logger.error("Error : " + e.getMessage());
			response.put("error",e.getMessage());
		}
		logger.info("search holiday response : " + response.toString());
		return response;

	}
}
