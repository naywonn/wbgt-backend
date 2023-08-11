package sg.nus.iss.team9ad.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

@Service
public class MLService {

	private final RestTemplate restTemplate;

	public MLService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getAllCurrentDataFromML() throws IOException {
		String mlApiUrl = "https://wbgtgroup9.azurewebsites.net/all_current";
		return restTemplate.getForObject(mlApiUrl, String.class);
	}

	public Map<String, Double> getAllMSEValues() throws IOException {
	    Map<String, Double> mseValuesMap = new HashMap<>();

	    String[] stationIds = {"S106","S104","S24","S44","S116","S43",
	    		"S50","S60","S107","S109","S111","S115","S117","S121"};

	    for (String stationId : stationIds) {
	        try {
	            Double mseValue = getMSEForStation(stationId);
	            mseValuesMap.put(stationId, mseValue);
	        } catch (Exception e) {
	            mseValuesMap.put(stationId, 0.0);
	        }
	    }



	    return mseValuesMap;
	}


	public Double getMSEForStation(String stationId) throws IOException {
		String mseApiUrl = "https://wbgtgroup9.azurewebsites.net/mse?station_id=" + stationId;
		return restTemplate.getForObject(mseApiUrl, Double.class);
	}

	public Double getMSEForSingleStation(String stationId) throws IOException {
		return getMSEForStation(stationId);
	}

}
