package sg.nus.iss.team9ad.service;

import java.util.List;
import java.util.Optional;

import sg.nus.iss.team9ad.model.Staff;

public interface StaffService {
	
	List<Staff>findAllStaffs();

	Optional<Staff>findbyUsername(String name);
	
	Optional<Staff> findStaff(int id);
	  
	  Staff createStaff(Staff staff);
	  
	  Staff updateStaff(Staff staff);
	  
	  void deleteStaff(int id);

	

}
