package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Tutor extends AppUser{
   private TutorStatus status = TutorStatus.Pending;

public void setStatus(TutorStatus value) {
    this.status = value;
}
public TutorStatus getStatus() {
    return this.status;
}
   private Set<Evaluation> evaluation;
   
   @OneToMany(mappedBy="tutor" )
   public Set<Evaluation> getEvaluation() {
      return this.evaluation;
   }
   
   public void setEvaluation(Set<Evaluation> evaluations) {
      this.evaluation = evaluations;
   }
   
   private Set<Course> course;
   
   @ManyToMany
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   }
