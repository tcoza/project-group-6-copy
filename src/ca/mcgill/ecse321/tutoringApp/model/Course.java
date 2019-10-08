package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Course{
   private String courseCode;

public void setCourseCode(String value) {
    this.courseCode = value;
}
public String getCourseCode() {
    return this.courseCode;
}
private Subject subject;

@ManyToOne(optional=false)
public Subject getSubject() {
   return this.subject;
}

public void setSubject(Subject subject) {
   this.subject = subject;
}

private University university;

@ManyToOne(optional=false)
public University getUniversity() {
   return this.university;
}

public void setUniversity(University university) {
   this.university = university;
}

private Set<Tutor> tutor;

@ManyToMany
public Set<Tutor> getTutor() {
   return this.tutor;
}

public void setTutor(Set<Tutor> tutors) {
   this.tutor = tutors;
}

private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
   private SessionRequest sessionRequest;
   
   @OneToOne(mappedBy="course" )
   public SessionRequest getSessionRequest() {
      return this.sessionRequest;
   }
   
   public void setSessionRequest(SessionRequest sessionRequest) {
      this.sessionRequest = sessionRequest;
   }
   
   }
