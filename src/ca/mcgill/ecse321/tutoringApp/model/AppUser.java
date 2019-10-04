package ca.mcgill.ecse321.tutoringApp.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
public abstract class AppUser{
   private String firstName;

public void setFirstName(String value) {
    this.firstName = value;
}
public String getFirstName() {
    return this.firstName;
}
private TutoringApp tutoringApplication;

@ManyToOne(optional=false)
public TutoringApp getTutoringApplication() {
   return this.tutoringApplication;
}

public void setTutoringApplication(TutoringApp tutoringApplication) {
   this.tutoringApplication = tutoringApplication;
}

private String lastName;

public void setLastName(String value) {
    this.lastName = value;
}
public String getLastName() {
    return this.lastName;
}
private String username;

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
