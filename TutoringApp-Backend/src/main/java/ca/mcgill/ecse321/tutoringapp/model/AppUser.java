package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppUser{
   private String firstName;

public void setFirstName(String value) {
    this.firstName = value;
}
public String getFirstName() {
    return this.firstName;
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
@Id
public static String getUsername() {
    return AppUser.username;
}
}
