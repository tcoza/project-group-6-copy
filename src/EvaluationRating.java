
public class EvaluationRating {
   private Integer starRating;
   
   public void setStarRating(Integer value) {
      this.starRating = value;
   }
   
   public Integer getStarRating() {
      return this.starRating;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * EvaluationRating ------------------------- Evaluation
    *           evaluationRating        &gt;       evaluation
    * </pre>
    */
   private Evaluation evaluation;
   
   public void setEvaluation(Evaluation value) {
      this.evaluation = value;
   }
   
   public Evaluation getEvaluation() {
      return this.evaluation;
   }
   
   }
