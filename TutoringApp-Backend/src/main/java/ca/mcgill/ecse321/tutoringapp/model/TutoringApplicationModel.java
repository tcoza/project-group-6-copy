package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class TutoringApplicationModel{
	
	private int id;

	public void setId(int value) {
	    this.id = value;
	}
	
	@Id
	public int getId() {
	    return this.id;
	}

   private Set<AppUser> appUser;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<AppUser> getAppUser() {
      return this.appUser;
   }
   
   public void setAppUser(Set<AppUser> appUsers) {
      this.appUser = appUsers;
   }
   
   private Set<SessionRequest> sessionRequest;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<SessionRequest> getSessionRequest() {
      return this.sessionRequest;
   }
   
   public void setSessionRequest(Set<SessionRequest> sessionRequests) {
      this.sessionRequest = sessionRequests;
   }
   
   private Set<Course> course;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private Set<Subject> subject;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
   }
   
   private Set<Room> room;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<Room> getRoom() {
      return this.room;
   }
   
   public void setRoom(Set<Room> rooms) {
      this.room = rooms;
   }
   
   private Set<ScheduledSession> scheduledSession;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<ScheduledSession> getScheduledSession() {
      return this.scheduledSession;
   }
   
   public void setScheduledSession(Set<ScheduledSession> scheduledSessions) {
      this.scheduledSession = scheduledSessions;
   }
   
   private Set<Offering> offering;
   
   @OneToMany(mappedBy="tutoringApplication" , cascade={CascadeType.ALL})
   public Set<Offering> getOffering() {
      return this.offering;
   }
   
   public void setOffering(Set<Offering> offerings) {
      this.offering = offerings;
   }
   
   }
