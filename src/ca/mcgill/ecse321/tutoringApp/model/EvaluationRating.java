package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class EvaluationRating{
   private Integer starRating;

public void setStarRating(Integer value) {
    this.starRating = value;
}
public Integer getStarRating() {
    return this.starRating;
}
   private Evaluation evaluation;
   
   @OneToOne(mappedBy="evaluationRating" , optional=false)
   public Evaluation getEvaluation() {
      return this.evaluation;
   }
   
   public void setEvaluation(Evaluation evaluation) {
      this.evaluation = evaluation;
   }
   
   }
