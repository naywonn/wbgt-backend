package sg.nus.iss.team9ad.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.team9ad.model.LoginRequest;
import sg.nus.iss.team9ad.model.Staff;
import sg.nus.iss.team9ad.service.StaffService;



@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@GetMapping("/list")
	public List<Staff> getAllStaffs() {
		return staffService.findAllStaffs();
	}
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
		// 在实际应用中，根据员工ID查找员工并验证密码
		Optional<Staff> authenticatedStaffOpt= staffService.findbyUsername(loginRequest.getUsername());
		if (authenticatedStaffOpt.isPresent()) {
			Staff authenticatedStaff = authenticatedStaffOpt.get();
			if (authenticatedStaff.getPassword().equals(loginRequest.getPassword())) {
				// 将认证后的员工信息保存到会话中
				session.setAttribute("authenticatedStaff", authenticatedStaff);
				
				return ResponseEntity.ok("Login successful");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		// 从会话中移除认证的员工信息
		session.invalidate();
		return ResponseEntity.ok("Logout successful");
	}
	@GetMapping("/list/{id}")
	public ResponseEntity<?> getStaffById(@PathVariable("id") Integer id) {
	    if (id == null) {
	        // Handle the case when id is null, e.g., return a ResponseEntity with an error message
	        return ResponseEntity.badRequest().body("Staff ID is missing.");
	    }

	    Optional<Staff> optStaff = staffService.findStaff(id);

	    if (optStaff.isPresent()) {
	        Staff staff = optStaff.get();
	        return new ResponseEntity<Staff>(staff, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Staff not found.", HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/list")
	public ResponseEntity<Staff> createStaff(@RequestBody Staff inStaff) {
	    try {
	        Staff retStaff = staffService.createStaff(inStaff);
	        return new ResponseEntity<Staff>(retStaff, HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	    }
	}
	@PutMapping("/list/{id}")
	public ResponseEntity<?> editStaff(@PathVariable("id") Integer id, @Validated @RequestBody Staff inStaff, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(error.getDefaultMessage()).append("; ");
			}
			return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
		}

		Optional<Staff> optStaff = staffService.findStaff(id);

		if (optStaff.isPresent()) {
			Staff staff = optStaff.get();

			staff.setName(inStaff.getName());
			staff.setTitle(inStaff.getTitle());
			staff.setEmail(inStaff.getEmail());

			Staff updatedStaff = null;
			try {
				updatedStaff = staffService.updateStaff(staff);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return new ResponseEntity<Staff>(updatedStaff, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/list/{id}")
	public ResponseEntity<HttpStatus> deleteStaff(@PathVariable("id") Integer id) {
		try {
			staffService.deleteStaff(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
