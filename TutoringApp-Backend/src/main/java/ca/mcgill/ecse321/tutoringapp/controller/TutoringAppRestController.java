package ca.mcgill.ecse321.tutoringapp.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	/** @author Traian Coza */
	@PostMapping(value = { "/students/{username}", "/students/{username}/" })
	public Student createStudent(
			@PathVariable("username") String username,
			@RequestParam(name="first", required=true) String first,
			@RequestParam(name="last", required=true) String last)
	{
		return (Student)service.createUser("STUDENT", username, first, last);
	}
	
	/** @author Traian Coza */
	@PostMapping(value = { "/tutors/{username}", "/tutors/{username}/" })
	public Tutor createTutor(
			@PathVariable("username") String username,
			@RequestParam(name="first", required=true) String first,
			@RequestParam(name="last", required=true) String last,
			@RequestParam(name="status", required=false) String status)
	{
		service.createUser("TUTOR", username, first, last);
		if (status != null)
			service.changeTutorStatus(username, status);
		return (Tutor)service.getUser(username);
	}
	
	/** @author Traian Coza */
	@PostMapping(value = { "/managers/{username}", "/managers/{username}/" })
	public Manager createManager(
			@PathVariable("username") String username,
			@RequestParam(name="first", required=true) String first,
			@RequestParam(name="last", required=true) String last)
	{
		return (Manager)service.createUser("MANAGER", username, first, last);
	}
	
	/** @author Traian Coza */
	@PostMapping(value = { "/appusers/{username}", "/appusers/{username}/" })
	public AppUser createAppUser(
			@PathVariable("username") String username,
			@RequestParam(name="first", required=true) String first,
			@RequestParam(name="last", required=true) String last,
			@RequestParam(name="usertype", required=true) String userType,
			@RequestParam(name="status", required=false) String status)
	{
		if (!userType.equals("TUTOR") && status != null)
			throw new IllegalArgumentException("Status only applies to tutors!");
		
		AppUser user = service.createUser(userType, username, first, last);
		if (status != null)
			return changeTutorStatus(username, status);
		
		return user;
	}
	
	/** @author Traian Coza */
	@PostMapping(value = { "/tutors/{username}/addcourse", "/tutors/{username}/addcourse/" })
	public Tutor addQualifiedCourseForTutor(
			@PathVariable("username") String username,
			@RequestParam(name="coursecode", required=true) String coursecode)
	{
		service.addQualifiedCourseForTutor(username, coursecode);
		return (Tutor)service.getUser(username);
	}
	
	/** @author Traian Coza */
	@PostMapping(value = { "/tutors/{username}/setstatus", "/tutors/{username}/setstatus/" })
	public Tutor changeTutorStatus(
			@PathVariable("username") String username,
			@RequestParam(name="status", required=true) String status)
	{
		service.changeTutorStatus(username, status);
		return (Tutor)service.getUser(username);
	}
	
	/** @author Traian Coza */
	@PostMapping(value = { "/students/{username}/delete", "/tutors/{username}/delete/" })
	public void deleteStudent(
			@PathVariable("username") String username)
	{
		service.deleteStudent(username);
	}
	
	//**** COURSE, SUBJECT, TEACHING INSTITUTION **************
	
	/** @author Alba Talelli */
	@PostMapping(value = { "/subjects/{name}", "/subjects/{name}/" })
	public Subject createSubject(@PathVariable("name") String name, @RequestParam(name = "school", required = true) String schoolname) throws IllegalArgumentException {
		Subject subject = service.createSubject(name, schoolname);
		return subject;
	}
	
	/** @author Alba Talelli */
	@PostMapping(value = { "/teachinginstitutions/{name}", "/teachinginstitutions/{name}/" })
	public TeachingInstitution createTeachingInstitution(@PathVariable("name") String name, @RequestParam(name="type",required=true) String type) throws IllegalArgumentException {
		service.createTeachingInstitution(name, type);
		return (TeachingInstitution)service.getTeachingInstitution(name);
	}
	/** @author Odero Otieno */
	@PostMapping(value = { "/classrooms/", "/classrooms" })
	public ClassRoom createClassRoom() {
		int id;		// Auto-generated id
		for (id = 1; roomRepository.existsById(id); id++);
		ClassRoom classRoom = new ClassRoom();
		classRoom.setId(id);
		classRoomRepository.save(classRoom);
		return classRoom;
	}
			

	/** @author Odero Otieno */
	@PostMapping(value = { "/smallrooms/", "/smallrooms" })
	public SmallRoom createSmallRoom() {
		int id;		// Auto-generated id
		for (id = 1; roomRepository.existsById(id); id++);
		SmallRoom smalRoom = new Small Room();
		smallRoom.setId(id);
		smallRoomRepository.save(smallRoom);
		return smallRoom;
	}
	
		/** @author Helen Lin */
	@PostMapping(value = { "/courses/{coursecode}", "/courses/{coursecode}/" })
	public Course createCourse(@PathVariable("coursecode") String coursecode, @RequestParam(name="name", required=true) String name, @RequestParam(name="school", required=true) String school) throws IllegalArgumentException {
		service.createCourse(coursecode, name, school);
		return (Course)service.getCourse(coursecode, name);
	}
	
	
	//************ SESSION REQUESTS (group, private and general) ***************
		//NOTE: @GetMapping is done automatically through @RepositoryRestController in each CRUD repo
		//e.g. to get all session requests by a student, rest url is /student/sessionrequests
	
	/** @author Helen Lin */
	@PostMapping(value = { "/privaterequests/", "/privaterequests" })
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
	@PostMapping(value = { "/grouprequests/", "/grouprequests" })
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
	@PostMapping(value = { "/sessionrequests/", "/sessionrequests" })
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
	
	/** @author Alba Talelli */
	@PostMapping(value = { "/studentevaluations/", "/studentevaluations" })
	public StudentEvaluation createStudentEvaluation(@RequestParam(name = "rating", required=true) int rating, @RequestParam(name = "username", required=true) String studentUN, @RequestParam(name = "username", required = true) String tutorUN) throws IllegalArgumentException {
		StudentEvaluation studentEval = new StudentEvaluation();
		if (studentUN == null) { // give student name
			throw new IllegalArgumentException("Must provide a Student Username");
		}
		if (tutorUN == null) { // give tutor name
			throw new IllegalArgumentException("Must provide a Tutor Username");
		}
		if (rating == 0) { // give rating
			throw new IllegalArgumentException("Must provide a Rating");
		}
		else {
			Student student = service.getStudent(studentUN);
			Tutor tutor = service.getTutor(tutorUN);
			studentEval = service.createStudentEvaluation(rating, student, tutor);
		}
		
		return studentEval;
	}
	
	/** @author Alba Talelli */
	@PostMapping(value = { "/tutorevaluations/", "/tutorevaluations" })
	public TutorEvaluation createTutorEvaluation(@RequestParam(name = "rating", required=true) int rating, @RequestParam(name = "username", required=true) String studentUN, @RequestParam(name = "username", required = true) String tutorUN) throws IllegalArgumentException {
		TutorEvaluation tutorEval = new TutorEvaluation();
		if (studentUN == null) { // give student name
			throw new IllegalArgumentException("Must provide a Student Username");
		}
		if (tutorUN == null) { // give tutor name
			throw new IllegalArgumentException("Must provide a Tutor Username");
		}
		if (rating == 0) { // give rating
			throw new IllegalArgumentException("Must provide a Rating");
		}
		else {
			Student student = service.getStudent(studentUN);
			Tutor tutor = service.getTutor(tutorUN);
			tutorEval = service.createTutorEvaluation(rating, student, tutor);
		}
		
		return tutorEval;
	}
	
	/** @author Alba Talelli */
	@PostMapping(value = { "/evaluations/", "/evaluations" })
	public Evaluation createEvaluation(@RequestParam(required=true) int rating, @RequestParam(name = "username", required=true) String studentUN, @RequestParam(name = "username", required = true) String tutorUN, @RequestParam(name="isStudentEvaluation", required=true) boolean isStudentEvaluation) throws IllegalArgumentException {
		Evaluation eval = new Evaluation();
		if (isStudentEvaluation) {
			eval = this.createStudentEvaluation(rating, studentUN, tutorUN);
		} else {
			eval = this.createTutorEvaluation(rating, studentUN, tutorUN);
		}
		return eval;
	}

	/** @author Alba Talelli */
	@PostMapping(value = { "/evaluationcomments/", "/evaluationcomments" })
	public EvaluationComment createEvaluationComment(@RequestParam(name = "username", required=true) String studentUN, @RequestParam(name = "username", required = true) String tutorUN,@RequestParam(name="isStudentEvaluation", required=true) boolean isStudentEvaluation, @RequestParam(required=true) String comment) throws IllegalArgumentException {
		Evaluation eval = new Evaluation();
		Student student = service.getStudent(studentUN);
		Tutor tutor = service.getTutor(tutorUN);
		
		if (isStudentEvaluation) {
			eval = service.getStudentEvaluation(student, tutor);		
		} 
		else {
			eval = service.getTutorEvaluation(student, tutor);		
		}
		
		service.createEvalComment(eval, comment);
		
		return (EvaluationComment)service.getEvalComment(eval);	
	}
	
	/** @author Alba Talelli */
	@DeleteMapping(value = { "/evaluationcomments/", "/evaluationcomments" })
	public void deleteEvaluationComment(@RequestParam(name = "username", required=true) String studentUN, @RequestParam(name = "username", required = true) String tutorUN,@RequestParam(name="isStudentEvaluation", required=true) boolean isStudentEvaluation, @RequestParam(required=true) String comment) throws IllegalArgumentException {
		Evaluation eval = new Evaluation();
		Student student = service.getStudent(studentUN);
		Tutor tutor = service.getTutor(tutorUN);
		
		if (isStudentEvaluation) {
			eval = service.getStudentEvaluation(student, tutor);		
		} 
		else {
			eval = service.getTutorEvaluation(student, tutor);		
		}
		
		service.deletEvalComment(eval);
			
	}

	/** @author Arianit */ 	
	@PostMapping(value = {"/scheduledprivatesessions/", "/scheduledprivatesessions" })
	public ScheduledPrivateSession createScheduledPrivateSession(
			@RequestParam(name = "username", required = true) String tutor,
			@RequestParam (required = true) int smallRoomID, 
			@RequestParam (required = true) Time startTime) throws IllegalArgumentException {
		return service.createScheduledPrivateSession(tutor, smallRoomID, startTime);
		
	}
	/** @author Arianit */
	@PostMapping(value = {"/scheduledgroupsessions/", "/scheduledgroupsessions" })
	public ScheduledGroupSession createScheduledGroupSession(
			@RequestParam(name = "username", required = true) String tutorS,
			@RequestParam (required = true) int classRoomS, 
			@RequestParam (required = true) Time startTime) throws IllegalArgumentException {
		ScheduledGroupSession scheduledGroupSession = new ScheduledGroupSession();
		scheduledGroupSession = service.createScheduledGroupSession(tutorS, classRoomS, startTime);
		return scheduledGroupSession;
		
	}
	
	/** @author Arianit */
	@DeleteMapping(value = {"/scheduledprivatesessions/", "/scheduledprivatesessions" })
	public void deleteScheduledPrivateSession(
			@RequestParam(name = "id", required = true) int id) throws IllegalArgumentException {
		service.deleteScheduledPrivateSession(id);
	}
	/** @author Arianit */
	@DeleteMapping(value = {"/scheduledgroupsessions/", "/scheduledgroupsessions" })
	public void deleteScheduledGroupSession(
			@RequestParam(name = "id", required = true) int id) throws IllegalArgumentException {
		service.deleteScheduledGroupSession(id);
	}
	
	
	
	
	
	
	
	
	
	
}
