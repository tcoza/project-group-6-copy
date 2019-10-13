package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class AppUser {
	private TutoringApplicationModel tutoringApplication;

	@ManyToOne(optional = false)
	public TutoringApplicationModel getTutoringApplication() {
		return this.tutoringApplication;
	}

	public void setTutoringApplication(TutoringApplicationModel tutoringApplication) {
		this.tutoringApplication = tutoringApplication;
	}

	private String firstName;

	public void setFirstName(String value) {
		this.firstName = value;
	}

	public String getFirstName() {
		return this.firstName;
	}

	private String lastName;

	public void setLastName(String value) {
		this.lastName = value;
	}

	public String getLastName() {
		return this.lastName;
	}

	private String username;

	public void setUsername(String value) {
		username = value;
	}

	@Id
	public String getUsername() {
		return username;
	}
}
