package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

@Entity
public class Course{
   private String courseCode;

public void setCourseCode(String value) {
    this.courseCode = value;
}
public String getCourseCode() {
    return this.courseCode;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
   private Set<GroupSessionRequest> sessionRequest;
   
   @OneToMany(mappedBy="course" )
   public Set<GroupSessionRequest> getSessionRequest() {
      return this.sessionRequest;
   }
   
   public void setSessionRequest(Set<GroupSessionRequest> sessionRequests) {
      this.sessionRequest = sessionRequests;
   }
   
   private University university;
   
   @ManyToOne(optional=false)
   public University getUniversity() {
      return this.university;
   }
   
   public void setUniversity(University university) {
      this.university = university;
   }
   
   private Subject subject;
   
   @ManyToOne(optional=false)
   public Subject getSubject() {
      return this.subject;
   }
   
   public void setSubject(Subject subject) {
      this.subject = subject;
   }
   
   private Set<Tutor> tutor;
   
   @ManyToMany(mappedBy="course" )
   public Set<Tutor> getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Set<Tutor> tutors) {
      this.tutor = tutors;
   }
   
   }
