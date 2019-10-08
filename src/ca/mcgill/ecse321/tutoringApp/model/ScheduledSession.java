package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;

@Entity
public class ScheduledSession{
   @OneToMany(mappedBy="scheduledSession")
   public Set<Evaluation> getEvaluation() {
      return this.evaluation;
   }
   
   private Date start;

public void setStart(Date value) {
    this.start = value;
}
public Date getStart() {
    return this.start;
}
private Date end;

public void setEnd(Date value) {
    this.end = value;
}
public Date getEnd() {
    return this.end;
}
private Integer id;

public void setId(Integer value) {
    this.id = value;
}
public Integer getId() {
    return this.id;
}
private Boolean isFull;

public void setIsFull(Boolean value) {
    this.isFull = value;
}
public Boolean getIsFull() {
    return this.isFull;
}
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
   private Set<Evaluation> evaluation;
   
   public void setEvaluation(Set<Evaluation> evaluations) {
      this.evaluation = evaluations;
   }
   
   }
