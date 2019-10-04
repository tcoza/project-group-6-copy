package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Evaluation{
   private AppUser appUser;

public void setAppUser(AppUser value) {
    this.appUser = value;
}
public AppUser getAppUser() {
    return this.appUser;
}
   private Date date;
   
   @OneToOne(optional=false)
   public Date getDate() {
      return this.date;
   }
   
   public void setDate(Date date) {
      this.date = date;
   }
   
   private EvaluationComment evaluationComment;
   
   @OneToOne(mappedBy="evaluation", cascade={CascadeType.ALL})
   public EvaluationComment getEvaluationComment() {
      return this.evaluationComment;
   }
   
   public void setEvaluationComment(EvaluationComment evaluationComment) {
      this.evaluationComment = evaluationComment;
   }
   
   private EvaluationRating evaluationRating;
   
   @OneToOne(cascade={CascadeType.ALL}, optional=false)
   public EvaluationRating getEvaluationRating() {
      return this.evaluationRating;
   }
   
   public void setEvaluationRating(EvaluationRating evaluationRating) {
      this.evaluationRating = evaluationRating;
   }
   
   private ScheduledSession scheduledSession;
   
   @ManyToOne(optional=false)
   public ScheduledSession getScheduledSession() {
      return this.scheduledSession;
   }
   
   public void setScheduledSession(ScheduledSession scheduledSession) {
      this.scheduledSession = scheduledSession;
   }
   
   }
