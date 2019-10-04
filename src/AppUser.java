import java.util.Set;

public abstract class AppUser {
   private String firstName;
   
   public void setFirstName(String value) {
      this.firstName = value;
   }
   
   public String getFirstName() {
      return this.firstName;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * AppUser ------------------------- TutoringApp
    *           appUser        &lt;       tutoringApplication
    * </pre>
    */
   private TutoringApp tutoringApplication;
   
   public void setTutoringApplication(TutoringApp value) {
      this.tutoringApplication = value;
   }
   
   public TutoringApp getTutoringApplication() {
      return this.tutoringApplication;
   }
   
   private String lastName;
   
   public void setLastName(String value) {
      this.lastName = value;
   }
   
   public String getLastName() {
      return this.lastName;
   }
   
   private static String username;
   
   public static void setUsername(String value) {
      AppUser.username = value;
   }
   
   public static String getUsername() {
      return AppUser.username;
   }
   
   private Set<Evaluation> evaluation;
   
   public void setEvaluation(Set<Evaluation> value) {
      this.evaluation = value;
   }
   
   public Set<Evaluation> getEvaluation() {
      return this.evaluation;
   }
   
   }
