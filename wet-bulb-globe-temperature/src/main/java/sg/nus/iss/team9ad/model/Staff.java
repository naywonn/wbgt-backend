package sg.nus.iss.team9ad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Password is required")
	@Size(min = 4, message = "Password must be at least 6 characters")
	private String password;

	@NotBlank(message = "Title is required")
	private String title;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;


	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Staff() {
	}

	public Staff(String name, String password, String title, String email) {
		this.name = name;
		this.password = password;
		this.title = title;
		this.email = email;
	}

	// Getters and setters...

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", password=" + password + ", title=" + title + ", email=" + email + "]";
	}
}
