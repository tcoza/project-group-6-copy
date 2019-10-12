package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class TeachingInstitution{
   private String name;

public void setName(String value) {
    this.name = value;
}
@Id
public String getName() {
    return this.name;
}
private Set<Course> course;

@OneToMany(mappedBy="school" )
public Set<Course> getCourse() {
   return this.course;
}

public void setCourse(Set<Course> courses) {
   this.course = courses;
}

private String/*No type specified!*/ type;

public void setType(String/*No type specified!*/ value) {
    this.type = value;
}
public String/*No type specified!*/ getType() {
    return this.type;
}
   private Set<Subject> subject;
   
   @OneToMany(mappedBy="school" )
   public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
   }
   
   }
