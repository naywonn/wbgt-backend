package sg.nus.iss.team9ad.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.nus.iss.team9ad.model.Staff;
import sg.nus.iss.team9ad.repo.StaffRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StaffServiceImpl implements StaffService {

	private StaffRepo staffRepo;

	
	public StaffServiceImpl(StaffRepo staffRepo) {
		this.staffRepo = staffRepo;
	}

	@Override
	public List<Staff> findAllStaffs() {
		return staffRepo.findAll();
	}

	@Override
	public Optional<Staff> findStaff(int id) {
		return staffRepo.findById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public Staff createStaff(Staff staff) {
		return staffRepo.save(staff);
	}

	@Transactional(readOnly = false)
	@Override
	public Staff updateStaff(Staff staff) {
		return staffRepo.save(staff);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteStaff(int id) {
		staffRepo.deleteById(id);
	}

	@Override
	public Optional<Staff> findStaffByName(String name) {
		return staffRepo.findByName(name);
	}

}
