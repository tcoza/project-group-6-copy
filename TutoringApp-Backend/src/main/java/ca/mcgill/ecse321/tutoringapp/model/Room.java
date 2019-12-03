package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * This auto-generated is our model class for Room, a super-class of SmallRoom
 * and Classroom. The primary key is the room 'id'. Table per class inheritance
 * strategy is used for our JPA mapping to the persistence layer.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Room {
	public Room() {

	}

	private int id;

	public void setId(int value) {
		this.id = value;
	}

	@Id
	public int getId() {
		return this.id;
	}

}
