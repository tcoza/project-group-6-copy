package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Student extends AppUser{
   private Boolean isActiveAccount = true;

public void setIsActiveAccount(Boolean value) {
    this.isActiveAccount = value;
}
public Boolean getIsActiveAccount() {
    return this.isActiveAccount;
}
   private Set<Evaluation> evaluation;
   
   @OneToMany(mappedBy="author" )
   public Set<Evaluation> getEvaluation() {
      return this.evaluation;
   }
   
   public void setEvaluation(Set<Evaluation> evaluations) {
      this.evaluation = evaluations;
   }
   
   private Set<GroupSessionRequest> sessionRequest;
   
   @OneToMany(mappedBy="requestor" )
   public Set<GroupSessionRequest> getSessionRequest() {
      return this.sessionRequest;
   }
   
   public void setSessionRequest(Set<GroupSessionRequest> sessionRequests) {
      this.sessionRequest = sessionRequests;
   }
   
   private Set<PrivateSession> privateSession;
   
   @OneToMany(mappedBy="student" )
   public Set<PrivateSession> getPrivateSession() {
      return this.privateSession;
   }
   
   public void setPrivateSession(Set<PrivateSession> privateSessions) {
      this.privateSession = privateSessions;
   }
   
   }
