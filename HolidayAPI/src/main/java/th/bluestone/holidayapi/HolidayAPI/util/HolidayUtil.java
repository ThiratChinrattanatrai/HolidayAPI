package th.bluestone.holidayapi.HolidayAPI.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import th.bluestone.holidayapi.HolidayAPI.exception.BusinessException;
import th.bluestone.holidayapi.HolidayAPI.response.HolidayResponse;
import static th.bluestone.holidayapi.HolidayAPI.constants.ErrorConstants.*;

@Service
public class HolidayUtil {
	
	private static Logger logger = LogManager.getLogger(HolidayUtil.class);
	
	public HolidayResponse callPublicAPI(String endPoint,HashMap<String,String> queryMap) throws IOException {
		
		String query = "?";
		for(String key : queryMap.keySet()) {
			query += key + "=" + queryMap.get(key) + "&";
		}
		query = query.substring(0, query.length() - 1);
		String urlWithQuery = endPoint + query;
		logger.info("Call api : " + endPoint);
		
		URL url =  new URL(urlWithQuery);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
        
		if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : "
                    + conn.getResponseCode() + " " + conn.getResponseMessage());
        }
        
		InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        
        String output = "";
        String temp;
        
        while ((temp = br.readLine()) != null) {
            output+= temp;
        }
        
        logger.info("Response from rest : " + output);
        ObjectMapper mapper = new ObjectMapper();
        HolidayResponse actualObj = mapper.readValue(output,HolidayResponse.class);

        conn.disconnect();
		return actualObj;
	}
	
	public Calendar getCalendar(String inputDate,String pattern) throws BusinessException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar calendar = Calendar.getInstance();
		try {
			Date outputDate = sdf.parse(inputDate);
			calendar.setTime(outputDate);
		} catch (ParseException e) {
			throw new BusinessException(INVALID_DATE_CODE,String.format(INVALID_DATE_CODE, pattern));
		}
		return calendar;
	}
}
