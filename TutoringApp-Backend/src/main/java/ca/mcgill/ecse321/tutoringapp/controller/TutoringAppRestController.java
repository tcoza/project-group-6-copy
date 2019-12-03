package ca.mcgill.ecse321.tutoringapp.controller;

import java.sql.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.tutoringapp.model.*;
import ca.mcgill.ecse321.tutoringapp.service.TutoringAppService;

@CrossOrigin
@RestController
public class TutoringAppRestController {

	@Autowired
	TutoringAppService service;

//	 NOTE:
//	 get mappings such as "get all session requests" are done automatically by
//	 @RepositoryRestResource in each CRUD repository
//	 e.g. /sessionrequests/ to get all sessionrequests, or
//	 /students/{username}/privaterequests to get all session requests by a student
//	 e.g. /teachinginstitutions/{name}/courses to get all courses at that teaching
//	 institution

	// ******************************* APP USER, STUDENT, TUTOR, MANAGER
	// ***************************************************************************

	/**
	 * This controller method creates a new student account.
	 * 
	 * @author Traian Coza
	 * @param username Username of the new student
	 * @param first    First name of the new student
	 * @param last     Last name of the new student
	 * @return the Student created
	 * 
	 */
	@PostMapping(value = { "/students/{username}", "/students/{username}/" })
	public Student createStudent(@PathVariable("username") String username,
			@RequestParam(name = "first", required = true) String first,
			@RequestParam(name = "last", required = true) String last) {
		return (Student) service.createUser("STUDENT", username, first, last);
	}

	/**
	 * This controller method deletes a student account entirely from the system.
	 * 
	 * @author Traian Coza
	 * @param username Username of student account to delete
	 * 
	 */
	@PostMapping(value = "/students/{username}/delete")
	public void deleteStudent(@PathVariable("username") String username) {
		service.deleteStudent(username);
	}

	/**
	 * This controller method deletes a tutor account entirely from the system.
	 * 
	 * @author Traian Coza
	 * @param username Username of tutor account to delete
	 * 
	 */
	@PostMapping(value = "/tutors/{username}/delete")
	public void deleteTutor(@PathVariable("username") String username) {
		service.deleteTutor(username);
	}

	/**
	 * This controller method creates a new tutor account.
	 * 
	 * @author Traian Coza
	 * @param username Username of the new tutor
	 * @param first    First name of the new tutor
	 * @param last     Last name of the new tutor
	 * @param status   Account status of the new tutor, values can be "VERIFIED",
	 *                 "PENDING", or "TERMINATED"
	 * @return the Tutor created
	 * 
	 */
	@PostMapping(value = { "/tutors/{username}", "/tutors/{username}/" })
	public Tutor createTutor(@PathVariable("username") String username,
			@RequestParam(name = "first", required = true) String first,
			@RequestParam(name = "last", required = true) String last,
			@RequestParam(name = "status", required = false) String status) {
		service.createUser("TUTOR", username, first, last);
		if (status != null)
			service.changeTutorStatus(username, status);
		return (Tutor) service.getUser(username);
	}

	/**
	 * This controller method creates a new manager account.
	 * 
	 * @author Traian Coza
	 * @param username Username of the new manager
	 * @param first    First name of the new manager
	 * @param last     Last name of the new manager
	 * @return the Manager created
	 * 
	 */
	@PostMapping(value = { "/managers/{username}", "/managers/{username}/" })
	public Manager createManager(@PathVariable("username") String username,
			@RequestParam(name = "first", required = true) String first,
			@RequestParam(name = "last", required = true) String last) {
		return (Manager) service.createUser("MANAGER", username, first, last);
	}

	/**
	 * This controller method creates a new appuser account
	 * 
	 * @author Traian Coza
	 * @param username Username of the new appuser
	 * @param first    First name of the new appuser
	 * @param last     Last name of the new appuser
	 * @return the AppUser account created
	 * 
	 */
	@PostMapping(value = { "/appusers/{username}", "/appusers/{username}/" })
	public AppUser createAppUser(@PathVariable("username") String username,
			@RequestParam(name = "first", required = true) String first,
			@RequestParam(name = "last", required = true) String last,
			@RequestParam(name = "usertype", required = true) String userType,
			@RequestParam(name = "status", required = false) String status) {
		if (!userType.equals("TUTOR") && status != null)
			throw new IllegalArgumentException("Status only applies to tutors!");

		AppUser user = service.createUser(userType, username, first, last);
		if (status != null)
			return changeTutorStatus(username, status);

		return user;
	}

	/**
	 * This controller method adds an existing course to the list of courses a tutor
	 * is qualified to teach.
	 * 
	 * @author Traian Coza
	 * @param username   Username of the tutor
	 * @param coursecode Coursecode (unique) of the course to add to the tutor's
	 *                   list
	 * @return the updated Tutor account
	 */
	@PostMapping(value = { "/tutors/{username}/addcourse", "/tutors/{username}/addcourse/" })
	public Tutor addQualifiedCourseForTutor(@PathVariable("username") String username,
			@RequestParam(name = "coursecode", required = true) String coursecode) {
		service.addQualifiedCourseForTutor(username, coursecode);
		return (Tutor) service.getUser(username);
	}

	/**
	 * This controller method changes the account status of a Tutor.
	 * 
	 * @author Traian Coza
	 * @param username Username of the tutor
	 * @param status   New account status (values can be "TERMINATED", "PENDING", or
	 *                 "VERIFIED"
	 * @return the updated Tutor account
	 */
	@PostMapping(value = { "/tutors/{username}/setstatus", "/tutors/{username}/setstatus/" })
	public Tutor changeTutorStatus(@PathVariable("username") String username,
			@RequestParam(name = "status", required = true) String status) {
		service.changeTutorStatus(username, status);
		return (Tutor) service.getUser(username);
	}

	/**
	 * This controller method changes the account status of a Student to DEACTIVATED
	 * 
	 * @author Traian Coza
	 * @param username Username of the student
	 * @return the updated Student account
	 */
	@PostMapping(value = { "/students/{username}/deactivate", "/tutors/{username}/deactivate/" })
	public void deactivateStudent(@PathVariable("username") String username) {
		service.setStudentStatus(username, false);
	}

	/**
	 * This controller method changes the account status of a Student to ACTIVE
	 * 
	 * @author Traian Coza
	 * @param username Username of the student
	 * @return the updated Student account
	 */
	@PostMapping(value = { "/students/{username}/reactivate", "/tutors/{username}/reactivate/" })
	public void reactivateStudent(@PathVariable("username") String username) {
		service.setStudentStatus(username, true);
	}

	// ******************************** COURSE, SUBJECT, TEACHING INSTITUTION
	// **********************************************************************

	/**
	 * This controller method creates a new subject associated with an existing
	 * school in the tutoring system
	 * 
	 * @author Alba Talelli
	 * @param name       Subject name
	 * @param schoolname School name of where the subject is taught
	 * @return the new Subject created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/subjects/{name}", "/subjects/{name}/" })
	public Subject createSubject(@PathVariable("name") String name,
			@RequestParam(name = "school", required = true) String schoolname) throws IllegalArgumentException {
		Subject subject = service.createSubject(name, schoolname);
		return subject;
	}

	/**
	 * This controller method creates a new teaching institution/ school in the
	 * tutoring system
	 * 
	 * @author Alba Talelli
	 * @param name School (Teaching Insititution) name
	 * @return the new School created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/teachinginstitutions/{name}", "/teachinginstitutions/{name}/" })
	public TeachingInstitution createTeachingInstitution(@PathVariable("name") String name,
			@RequestParam(name = "type", required = true) String type) throws IllegalArgumentException {
		service.createTeachingInstitution(name, type);
		return (TeachingInstitution) service.getTeachingInstitution(name);
	}

	/**
	 * This controller method creates a new course associated with an existing
	 * school in the tutoring system. Courses should be created only for
	 * universities.
	 * 
	 * @author Helen Lin
	 * @param coursecode Course code of the new course
	 * @param name       Name of the new course
	 * @param school     University it is taught at
	 * @return the new Course created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/courses/{coursecode}", "/courses/{coursecode}/" })
	public Course createCourse(@PathVariable("coursecode") String coursecode,
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "school", required = true) String school) throws IllegalArgumentException {
		service.createCourse(coursecode, name, school);
		return (Course) service.getCourse(coursecode, name);
	}

	/**
	 * This controller method creates a new classroom in the tutoring system for
	 * group tutoring.
	 * 
	 * @author Odero Otieno
	 * 
	 */
	@PostMapping(value = { "/createclassroom/", "/createclassroom" })
	public ClassRoom createClassRoom() {
		return service.createClassRoom();
	}

	/**
	 * This controller method creates a new small room in the tutoring system for
	 * private tutoring.
	 * 
	 * @author Odero Otieno
	 */
	@PostMapping(value = { "/createsmallroom/", "/createsmallroom" })
	public SmallRoom createSmallRoom() {
		return service.createSmallRoom();
	}

	// ******************************** SESSION REQUESTS (group, private and
	// general) *****************************************************

//	 NOTE: @GetMapping is done automatically through @RepositoryRestController in
//	 each CRUD repository
//	 e.g. to get all session requests by a student, rest url is
//	 /student/sessionrequests

	/**
	 * This controller method creates a new private tutoring REQUEST from a student
	 * for either a course OR a subject.
	 * 
	 * @author Helen Lin
	 * @param username Username of the student requesting the private tutoring
	 *                 session
	 * @param course   Course code of the course requested (optional)
	 * @param subject  Subject name of the subject requested (optional)
	 * @return the new PrivateRequest created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createprivaterequest/", "/createprivaterequest" })
	public PrivateRequest createPrivateRequest(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "course", required = false) String course,
			@RequestParam(name = "subject", required = false) String subject) throws IllegalArgumentException {
		PrivateRequest request;
		if (course != null) { // create course request
			request = service.createPrivateRequest(username, course, true);
		} else if (subject != null) { // create subject request
			request = service.createPrivateRequest(username, subject, false);
		} else {
			throw new IllegalArgumentException("Must provide a course or subject parameter for the request!");
		}

		return request;
	}

	/**
	 * This controller method creates a new group tutoring REQUEST from a student
	 * for either a course OR a subject.
	 * 
	 * @author Helen Lin
	 * @param username Username of the student requesting the group tutoring session
	 * @param course   Course code of the course requested (optional)
	 * @param subject  Subject name of the subject requested (optional)
	 * @return the new GroupRequest created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/creategrouprequest/", "/creategrouprequest" })
	public GroupRequest createGroupRequest(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "course", required = false) String course,
			@RequestParam(name = "subject", required = false) String subject) throws IllegalArgumentException {
		GroupRequest request;
		if (course != null) { // create course request
			request = service.createGroupRequest(username, course, true);
		} else if (subject != null) { // create subject request
			request = service.createGroupRequest(username, subject, false);
		} else {
			throw new IllegalArgumentException("Must provide a course or subject parameter for the request!");
		}

		return request;
	}

	/**
	 * This controller method creates a new private or group tutoring REQUEST from a
	 * student for either a course OR a subject.
	 * 
	 * @author Helen Lin
	 * @param username         Username of the student requesting the tutoring
	 *                         session
	 * @param course           Course code of the course requested (optional)
	 * @param subject          Subject name of the subject requested (optional)
	 * @param isPrivateRequest true if requesting a private session, false if
	 *                         requesting a group session
	 * @return the new SessionRequest created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createsessionrequest/", "/createsessionrequest" })
	public SessionRequest createSessionRequest(@RequestParam(name = "username", required = true) String username,
			@RequestParam(name = "course", required = false) String course,
			@RequestParam(name = "subject", required = false) String subject,
			@RequestParam(name = "isPrivateRequest", required = true) boolean isPrivateRequest)
			throws IllegalArgumentException {
		SessionRequest request;
		if (isPrivateRequest) { // create private request
			request = this.createPrivateRequest(username, course, subject);
		} else {
			request = this.createGroupRequest(username, course, subject);
		}

		return request;
	}

	// ************************************EVALUATIONS (TUTOR AND STUDENT
	// EVALUATIONS) *************************************************************

	/**
	 * This controller method creates an evaluation/feedback for a student
	 * (recipient) from a tutor (author).
	 * 
	 * @author Alba Talelli
	 * @param rating    The rating given to the student, integer value from 0 to 5.
	 * @param studentUN The username of the student recipient
	 * @param tutorUN   The username of the tutor author
	 * @param comment   An optional evaluation comment
	 * @return the new StudentEvaluation created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createstudentevaluation/", "/createstudentevaluation" })
	public StudentEvaluation createStudentEvaluation(@RequestParam(name = "rating", required = true) int rating,
			@RequestParam(name = "studentUN", required = true) String studentUN,
			@RequestParam(name = "tutorUN", required = true) String tutorUN,
			@RequestParam(name = "comment", required = false) String comment) throws IllegalArgumentException {
		StudentEvaluation studentEval = new StudentEvaluation();
		if (studentUN == null) { // give student name
			throw new IllegalArgumentException("Must provide a Student Username");
		}
		if (tutorUN == null) { // give tutor name
			throw new IllegalArgumentException("Must provide a Tutor Username");
		}
		if (rating == 0) { // give rating
			throw new IllegalArgumentException("Must provide a Rating");
		} else {
			Student student = service.getStudent(studentUN);
			Tutor tutor = service.getTutor(tutorUN);
			studentEval = service.createStudentEvaluation(rating, student, tutor);
		}

		if (comment != null) {
			service.createEvalComment(studentEval, comment);
		}

		return studentEval;
	}

	/**
	 * This controller method creates an evaluation/feedback for a tutor (recipient)
	 * from a student(author).
	 * 
	 * @author Alba Talelli, Helen Lin
	 * @param rating    The rating given to the tutor, integer value from 0 to 5.
	 * @param studentUN The username of the student author
	 * @param tutorUN   The username of the tutor recipient
	 * @param comment   An optional evaluation comment
	 * @return the new TutorEvaluation created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createtutorevaluation/", "/createtutorevaluation" })
	public TutorEvaluation createTutorEvaluation(@RequestParam(name = "rating", required = true) int rating,
			@RequestParam(name = "studentUN", required = true) String studentUN,
			@RequestParam(name = "tutorUN", required = true) String tutorUN,
			@RequestParam(name = "comment", required = false) String comment) throws IllegalArgumentException {
		TutorEvaluation tutorEval = new TutorEvaluation();
		if (studentUN == null) { // give student name
			throw new IllegalArgumentException("Must provide a Student Username");
		}
		if (tutorUN == null) { // give tutor name
			throw new IllegalArgumentException("Must provide a Tutor Username");
		}
		if (rating == 0) { // give rating
			throw new IllegalArgumentException("Must provide a Rating");
		} else {
			Student student = service.getStudent(studentUN);
			Tutor tutor = service.getTutor(tutorUN);
			tutorEval = service.createTutorEvaluation(rating, student, tutor);
		}

		if (comment != null) {
			this.service.createEvalComment(tutorEval, comment);
		}

		return tutorEval;
	}

	/**
	 * This controller method creates an evaluation/feedback for a tutor or a
	 * student
	 * 
	 * @author Alba Talelli, Helen Lin
	 * @param rating             The rating given to the recipient, integer value
	 *                           from 0 to 5.
	 * @param studentUN          The username of the student
	 * @param tutorUN            The username of the tutor
	 * @param comment            An optional evaluation comment
	 * @param isStudentEvalation true if the student is recipient and tutor is
	 *                           author; false if tutor is recipient and student is
	 *                           author
	 * @return the new Evaluation created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createevaluation/", "/createevaluation" })
	public Evaluation createEvaluation(@RequestParam(required = true) int rating,
			@RequestParam(name = "studentUN", required = true) String studentUN,
			@RequestParam(name = "tutorUN", required = true) String tutorUN,
			@RequestParam(name = "isStudentEvaluation", required = true) boolean isStudentEvaluation,
			@RequestParam(name = "comment", required = false) String comment) throws IllegalArgumentException {
		Evaluation eval = new Evaluation();

		if (isStudentEvaluation) {
			eval = this.createStudentEvaluation(rating, studentUN, tutorUN, comment);
		} else {
			eval = this.createTutorEvaluation(rating, studentUN, tutorUN, comment);
		}

		return eval;
	}

	/**
	 * This controller method creates or updates an evaluation comment for an
	 * existing evaluation.
	 * 
	 * @author Alba Talelli
	 * @param studentUN           username of the Student
	 * @param tutorUN             username of the Tutor
	 * @param isStudentEvaluation true if the student is recipient and tutor is
	 *                            author; false if tutor is recipient and student is
	 *                            author
	 * @param comment             the evaluation comment/ feedback
	 * @return The new comment that was added
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createevaluationcomment/", "/createevaluationcomment" })
	public String createEvaluationComment(@RequestParam(name = "studentUN", required = true) String studentUN,
			@RequestParam(name = "tutorUN", required = true) String tutorUN,
			@RequestParam(name = "isStudentEvaluation", required = true) boolean isStudentEvaluation,
			@RequestParam(name = "comment", required = true) String comment) throws IllegalArgumentException {
		Evaluation eval = new Evaluation();
		Student student = service.getStudent(studentUN);
		Tutor tutor = service.getTutor(tutorUN);

		if (isStudentEvaluation) {
			eval = service.getStudentEvaluation(student, tutor);
		} else {
			eval = service.getTutorEvaluation(student, tutor);
		}

		service.createEvalComment(eval, comment);

		return service.getEvalComment(eval);
	}

	/**
	 * This controller method sets the comment visibility of a evaluation's comment.
	 * This allows the manager to censor or un-censor comments from the system.
	 * 
	 * @author Alba Talelli
	 * @param studentUN           the username of the Student
	 * @param tutorUN             the username of the Tutor
	 * @param isStudentEvaluation true if the student is recipient and tutor is
	 *                            author; false if tutor is recipient and student is
	 *                            author
	 * @param visibility          true for a visible comment; false to hide a
	 *                            comment
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/setcommentvisibility/", "/setcommentvisibility" })
	public void deleteEvaluationComment(@RequestParam(name = "studentUN", required = true) String studentUN,
			@RequestParam(name = "tutorUN", required = true) String tutorUN,
			@RequestParam(name = "isStudentEvaluation", required = true) boolean isStudentEvaluation,
			@RequestParam(name = "visibility", required = true) boolean visibility) throws IllegalArgumentException {
		Evaluation eval = new Evaluation();
		Student student = service.getStudent(studentUN);
		Tutor tutor = service.getTutor(tutorUN);

		if (isStudentEvaluation) {
			eval = service.getStudentEvaluation(student, tutor);
		} else {
			eval = service.getTutorEvaluation(student, tutor);
		}
		if (visibility) {
			service.readdEvalComment(eval);
		} else {
			service.removeEvalComment(eval);
		}

	}

	/**
	 * This controller method creates/ books a private tutoring session.
	 * 
	 * @author Arianit
	 * @param tutor       The username of the tutor for the session.
	 * @param smallRoomID The room ID of the small room for the session
	 * @param startTime   The start time of the tutoring session
	 * @return the new ScheduledPrivateSession created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createscheduledprivatesession/", "/createscheduledprivatesession" })
	public ScheduledPrivateSession createScheduledPrivateSession(
			@RequestParam(name = "username", required = true) String tutor,
			@RequestParam(required = true) int smallRoomID, @RequestParam(required = true) Time startTime)
			throws IllegalArgumentException {
		return service.createScheduledPrivateSession(tutor, smallRoomID, startTime);

	}

	/**
	 * This controller method creates/ books a group tutoring session.
	 * 
	 * @author Arianit
	 * @param tutorS     The username of the tutor for the session.
	 * @param classRoomS The room ID of the class room for the session
	 * @param startTimeS The start time of the tutoring session
	 * @return the new ScheduledGroupSession created
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/createscheduledgroupsession/", "/createscheduledgroupsession" })
	public ScheduledGroupSession createScheduledGroupSession(
			@RequestParam(name = "username", required = true) String tutorS,
			@RequestParam(name = "roomid", required = true) int classRoomS,
			@RequestParam(name = "starttime", required = true) int startTimeS) throws IllegalArgumentException {
		ScheduledGroupSession scheduledGroupSession = new ScheduledGroupSession();
		@SuppressWarnings("deprecation")
		Time startTime = new java.sql.Time(startTimeS / 60, startTimeS % 60, 00);
		scheduledGroupSession = service.createScheduledGroupSession(tutorS, classRoomS, startTime);
		return scheduledGroupSession;

	}

	/**
	 * This controller method deletes a booked private tutoring session by ID
	 * 
	 * @author Arianit
	 * @param id ID of the session to delete
	 */
	@DeleteMapping(value = { "/deletescheduledprivatesession/", "/deletescheduledprivatesession" })
	public void deleteScheduledPrivateSession(@RequestParam(required = true) int id) {
		service.deleteScheduledPrivateSession(id);
	}

	/**
	 * This controller method deletes a booked group tutoring session by ID
	 * 
	 * @author Arianit
	 * @param id ID of the session to delete
	 */
	@DeleteMapping(value = { "/deletescheduledgroupsession/", "/deletescheduledgroupsession" })
	public void deleteScheduledGroupSession(@RequestParam(required = true) int id) {
		service.deleteScheduledGroupSession(id);
	}

}
