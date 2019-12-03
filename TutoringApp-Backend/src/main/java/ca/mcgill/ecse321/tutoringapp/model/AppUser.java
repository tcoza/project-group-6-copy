package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * This auto-generated is our model class for AppUser, the super-class of
 * Manager, Student, and Tutor. The primary key is the username. Table per class
 * inheritance strategy is used for our JPA mapping to the persistence layer.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class AppUser {
	public AppUser() {

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
