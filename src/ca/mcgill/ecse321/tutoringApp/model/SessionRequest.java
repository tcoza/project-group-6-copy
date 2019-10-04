package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class SessionRequest{
   private Course course;
   
   @OneToOne(optional=false)
   public Course getCourse() {
      return this.course;
   }
   
   public void setCourse(Course course) {
      this.course = course;
   }
   
   private Integer numStudents;

public void setNumStudents(Integer value) {
    this.numStudents = value;
}
public Integer getNumStudents() {
    return this.numStudents;
}
   private Set<Student> student;
   
   @ManyToMany
   public Set<Student> getStudent() {
      return this.student;
   }
   
   public void setStudent(Set<Student> students) {
      this.student = students;
   }
   
   }
