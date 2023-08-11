package sg.nus.iss.team9ad.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.team9ad.service.MLService;

@CrossOrigin
@RestController
@RequestMapping("/ML")
public class MLController {

	@Autowired
	private MLService mlService;

	@GetMapping("/all_current")
	public ResponseEntity<String> getAllCurrentData() {
		try {
			String jsonData = mlService.getAllCurrentDataFromML();
			return ResponseEntity.ok(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/mse/{stationId}")
    public ResponseEntity<Double> getMSEForStation(@PathVariable String stationId) {
        try {
            Double mseValue = mlService.getMSEForSingleStation(stationId);
            return ResponseEntity.ok(mseValue);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/all_mse")
    public ResponseEntity<Map<String, Double>> getMSEValues() {
        try {
            Map<String, Double> mseValuesMap = mlService.getAllMSEValues();
            return ResponseEntity.ok(mseValuesMap);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
