package ca.mcgill.ecse321.tutoringapp.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;

@Entity
public class GroupSessionRequest{
   private Date dateCreated;

public void setDateCreated(Date value) {
    this.dateCreated = value;
}
public Date getDateCreated() {
    return this.dateCreated;
}
private Student requestor;

@ManyToOne(optional=false)
public Student getRequestor() {
   return this.requestor;
}

public void setRequestor(Student requestor) {
   this.requestor = requestor;
}

private Course course;

@ManyToOne(optional=false)
public Course getCourse() {
   return this.course;
}

public void setCourse(Course course) {
   this.course = course;
}

private Set<Offering> session;

@ManyToMany(mappedBy="groupSessionRequest" )
public Set<Offering> getSession() {
   return this.session;
}

public void setSession(Set<Offering> sessions) {
   this.session = sessions;
}

private GroupSession groupSession;

@ManyToOne
public GroupSession getGroupSession() {
   return this.groupSession;
}

public void setGroupSession(GroupSession groupSession) {
   this.groupSession = groupSession;
}

private boolean isScheduled = false;

public void setIsScheduled(boolean value) {
    this.isScheduled = value;
}
public boolean isIsScheduled() {
    return this.isScheduled;
}
private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
}
