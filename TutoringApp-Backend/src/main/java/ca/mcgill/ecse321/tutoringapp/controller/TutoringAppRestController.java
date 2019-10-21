package ca.mcgill.ecse321.tutoringapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.tutoringapp.model.*;
import ca.mcgill.ecse321.tutoringapp.service.TutoringAppService;

@CrossOrigin(origins = "*")
@RestController
public class TutoringAppRestController {
	
	@Autowired TutoringAppService service;
	
	//NOTE:
	//get mappings such as "get all session requests" are done automatically by @RepositoryRestResource in each CRUD repository
		//e.g. /sessionrequests/ to get all sessionrequests, or /students/{username}/privaterequests to get all session requests by a student
		//e.g. /teachinginstitutions/{name}/courses to get all courses at that teaching institution
	
	//***********	APP USER, STUDENT, TUTOR, MANAGER *******************
	/** @author Helen Lin */
	@PostMapping(value = { "/students/{username}", "/students/{username}/" })
	public Student createStudent(@PathVariable("username") String username, @RequestParam(name="first", required=true) String first, @RequestParam(name="last", required=true) String last) {
		Student student =service.createStudent(username, first, last);
		return student;
	}
	
	/** @author Helen Lin */
	@PostMapping(value = { "/tutors/{username}", "/students/{username}/" })
	public Tutor createTutor(@PathVariable("username") String username, @RequestParam(name="first", required=true) String first, @RequestParam(name="last", required=true) String last, @RequestParam(name="status", required=false) String status) {
		Tutor tutor = service.createTutor(username, first, last);
		if (status !=null) {
			//also update status if given
			service.changeTutorStatus(username, status);
		}
		return tutor;
	}
	
	/** @author Helen Lin */
	@PostMapping(value = { "/managers/{username}", "/managers/{username}/" })
	public Manager createManager(@PathVariable("username") String username, @RequestParam(name="first", required=true) String first, @RequestParam(name="last", required=true) String last) {
		Manager manager = service.createManager(username, first, last);
		return manager;
	}
	
	/** @author Helen Lin */
	@PostMapping(value = { "/appusers/{username}", "/appusers/{username}/" })
	public AppUser createAppUser(@PathVariable("username") String username, @RequestParam(name="first", required=true) String first, @RequestParam(name="last", required=true) String last, @RequestParam(name="usertype", required=true) String usertype, @RequestParam(name="status", required=true) String status) {
		AppUser user;
		if (usertype.equals("manager")) {
			user = this.createManager(username, first, last);
		} else if (usertype.equals("student")) {
			user = this.createStudent(username, first, last);
		} else if (usertype.equals("tutor")) {
			user = this.createTutor(username, first, last, status);
		} else {
			throw new IllegalArgumentException("Invalid user type");
		}
		return user;
	}
	
	//TODO: add course for a tutor and vice versa (add a qualifiedtutor for a course)
	
	//TODO: change tutor status
	
	//**** COURSE, SUBJECT, TEACHING INSTITUTION **************
	//TODO: createSubject
	
	//TODO: createTeachingInstitutions (with type)
	/** @author Helen Lin */
	@PostMapping(value = { "/courses/{coursecode}", "/courses/{coursecode}/" })
	public Course createCourse(@PathVariable("coursecode") String coursecode, @RequestParam(name="name", required=true) String name, @RequestParam(name="school", required=true) String school) throws IllegalArgumentException {
		Course course = service.createCourse(coursecode, name, school);
		return course;
	}
	
	
	//************ SESSION REQUESTS (group, private and general) ***************
		//NOTE: @GetMapping is done automatically through @RepositoryRestController in each CRUD repo
		//e.g. to get all session requests by a student, rest url is /student/sessionrequests
	
	/** @author Helen Lin */
	@PostMapping(value = { "/createprivaterequest/", "/createprivaterequest" })
	public PrivateRequest createPrivateRequest(@RequestParam(name = "username", required=true) String username, @RequestParam(name="course", required=false) String course, @RequestParam(name="subject", required=false) String subject) throws IllegalArgumentException {
		PrivateRequest request;
		if (course != null) { //create course request
			request = service.createPrivateRequest(username, course, true);
		} else if (subject != null) { //create subject request
			request = service.createPrivateRequest(username, subject, false);
		} else {
			throw new IllegalArgumentException("Must provide a course or subject parameter for the request!");
		}
	
		return request;
	}
	
	/** @author Helen Lin */
	@PostMapping(value = { "/creategrouprequest/", "/creategrouprequest" })
	public GroupRequest createGroupRequest(@RequestParam(name = "username", required=true) String username, @RequestParam(name="course", required=false) String course, @RequestParam(name="subject", required=false) String subject) throws IllegalArgumentException {
		GroupRequest request;
		if (course != null) { //create course request
			request = service.createGroupRequest(username, course, true);
		} else if (subject != null) { //create subject request
			request = service.createGroupRequest(username, subject, false);
		} else {
			throw new IllegalArgumentException("Must provide a course or subject parameter for the request!");
		}
	
		return request;
	}
	
	/** @author Helen Lin */
	@PostMapping(value = { "/createsessionrequest/", "/createsessionrequest" })
	public SessionRequest createSessionRequest(@RequestParam(name = "username", required=true) String username, @RequestParam(name="course", required=false) String course, @RequestParam(name="subject", required=false) String subject, @RequestParam(name="isPrivateRequest", required=true) boolean isPrivateRequest) throws IllegalArgumentException {
		SessionRequest request;
		if (isPrivateRequest) { //create private request
			request = this.createPrivateRequest(username,course, subject);
		} else {
			request = this.createGroupRequest(username, course, subject);
		}

		return request;
	}
	
	//***************EVALUATIONS ****************
	//TODO: create evaluation for student or for tutor
	//TODO: delete evaluation comment
	
}