
public class SmallRoom extends Room {
   /**
    * <pre>
    *           3..3     1..1
    * SmallRoom ------------------------- TutoringApplication
    *           smallRoom        &lt;       tutoringApplication
    * </pre>
    */
   private TutoringApplication tutoringApplication;
   
   public void setTutoringApplication(TutoringApplication value) {
      this.tutoringApplication = value;
   }
   
   public TutoringApplication getTutoringApplication() {
      return this.tutoringApplication;
   }
   
   }
