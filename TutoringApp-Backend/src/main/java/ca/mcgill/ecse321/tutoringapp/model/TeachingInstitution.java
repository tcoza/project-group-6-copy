package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class TeachingInstitution {
	public TeachingInstitution() {
		
	}
	private String name;

	public void setName(String value) {
		this.name = value;
	}

	@Id
	public String getName() {
		return this.name;
	}

	private Set<Course> course;

	@OneToMany(mappedBy = "school")
	public Set<Course> getCourse() {
		return this.course;
	}

	public void setCourse(Set<Course> courses) {
		this.course = courses;
	}

	private InstitutionType type;

	public void setType(InstitutionType value) {
		this.type = value;
	}

	public InstitutionType getType() {
		return this.type;
	}

	private Set<Subject> subject;

	@OneToMany(mappedBy = "school")
	public Set<Subject> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<Subject> subjects) {
		this.subject = subjects;
	}

}
