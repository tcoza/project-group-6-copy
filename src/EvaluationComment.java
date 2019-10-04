
public class EvaluationComment {
   private String comment;
   
   public void setComment(String value) {
      this.comment = value;
   }
   
   public String getComment() {
      return this.comment;
   }
   
   /**
    * <pre>
    *           0..1     1..1
    * EvaluationComment ------------------------- Evaluation
    *           evaluationComment        &lt;       evaluation
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
