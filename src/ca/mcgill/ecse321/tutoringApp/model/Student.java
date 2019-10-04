package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Student extends AppUser{
   private Set<SessionRequest> sessionRequest;
   
   @ManyToMany(mappedBy="student" )
   public Set<SessionRequest> getSessionRequest() {
      return this.sessionRequest;
   }
   
   public void setSessionRequest(Set<SessionRequest> sessionRequests) {
      this.sessionRequest = sessionRequests;
   }
   
   private Boolean isActiveAccount = true;

public void setIsActiveAccount(Boolean value) {
    this.isActiveAccount = value;
}
public Boolean getIsActiveAccount() {
    return this.isActiveAccount;
}
}
