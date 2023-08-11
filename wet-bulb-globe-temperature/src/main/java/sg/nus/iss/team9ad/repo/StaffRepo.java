package sg.nus.iss.team9ad.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.team9ad.model.Staff;

public interface StaffRepo extends JpaRepository<Staff, Integer>{


    Optional<Staff> findByName(String name);

}
