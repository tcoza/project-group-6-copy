package ca.mcgill.ecse321.tutoringapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.tutoringapp.dao.AppUserRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ClassRoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringapp.dao.EvaluationCommentRepository;
import ca.mcgill.ecse321.tutoringapp.dao.EvaluationRepository;
import ca.mcgill.ecse321.tutoringapp.dao.GroupRequestRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ManagerRepository;
import ca.mcgill.ecse321.tutoringapp.dao.OfferingRepository;
import ca.mcgill.ecse321.tutoringapp.dao.PrivateRequestRepository;
import ca.mcgill.ecse321.tutoringapp.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ScheduledGroupSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ScheduledPrivateSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ScheduledSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.SessionRequestRepository;
import ca.mcgill.ecse321.tutoringapp.dao.SmallRoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.StudentEvaluationRepository;
import ca.mcgill.ecse321.tutoringapp.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringapp.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringapp.dao.TeachingInstitutionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.TutorEvaluationRepository;
import ca.mcgill.ecse321.tutoringapp.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.Evaluation;
import ca.mcgill.ecse321.tutoringapp.model.GroupRequest;
import ca.mcgill.ecse321.tutoringapp.model.Manager;
import ca.mcgill.ecse321.tutoringapp.model.PrivateRequest;
import ca.mcgill.ecse321.tutoringapp.model.SessionRequest;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.StudentEvaluation;
import ca.mcgill.ecse321.tutoringapp.model.Subject;
import ca.mcgill.ecse321.tutoringapp.model.TeachingInstitution;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;
import ca.mcgill.ecse321.tutoringapp.model.TutorEvaluation;
import ca.mcgill.ecse321.tutoringapp.model.TutorStatus;

@Service
public class TutoringAppService {

	
	@Autowired
	TeachingInstitutionRepository teachingInstitutionRepository;
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ManagerRepository managerRepository;
	@Autowired
	TutorRepository tutorRepository;
	@Autowired
	ClassRoomRepository classRoomRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	EvaluationCommentRepository evaluationCommentRepository;
	@Autowired
	EvaluationRepository evaluationRepository;
	@Autowired
	StudentEvaluationRepository studentEvaluationRepository;
	@Autowired
	TutorEvaluationRepository tutorEvaluationRepository;
	@Autowired
	OfferingRepository offeringRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	ScheduledSessionRepository scheduledSessionRepository;
	@Autowired
	ScheduledGroupSessionRepository scheduledGroupSessionRepository;
	@Autowired
	ScheduledPrivateSessionRepository scheduledPrivateSessionRepository;
	@Autowired
	SmallRoomRepository smallRoomRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	GroupRequestRepository groupRequestRepository;
	@Autowired
	PrivateRequestRepository privateRequestRepository;
	@Autowired
	SessionRequestRepository sessionRequestRepository;

	/** @author Alba */
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	// TODO: Traian's services
	
	// to do: business methods for:
		//TODO: delete student
		//TODO: check user stories/functional requirements
	
	// Tutor
	/**@author = Helen **/
	@Transactional
	public Tutor createTutor(String username, String firstname, String lastname) {
		if (username == null || username.trim().length() == 0)
			throw new IllegalArgumentException("Username cannot be empty!");
		if (firstname == null || firstname.trim().length() == 0)
			throw new IllegalArgumentException("First name cannot be empty!");
		if (lastname == null || lastname.trim().length() == 0)
			throw new IllegalArgumentException("Last name cannot be empty!");

		Tutor tutor = new Tutor();
		tutor.setUsername(username);
		tutor.setFirstName(firstname);
		tutor.setLastName(lastname);
		tutorRepository.save(tutor);
		return tutor;
	}
	
	/**@author = Helen **/
	@Transactional
	public void changeTutorStatus(String username, String status) {
		if (!tutorRepository.existsByUsername(username)) 
			throw new IllegalArgumentException("This tutor does not exist!");
	
		Tutor tutor = tutorRepository.findTutorByUsername(username);
		if (status == "PENDING") {
			tutor.setStatus(TutorStatus.PENDING);
		} else if (status == "VERIFIED") {
			tutor.setStatus(TutorStatus.VERIFIED);
		} else if (status == "TERMINATED") {
			tutor.setStatus(TutorStatus.TERMINATED);
			
			//TODO: also remove all qualified courses from this tutor because they won't teach anymore
		}
		tutorRepository.save(tutor);
		return;
	}
	
	/**@author = Helen **/
	@Transactional
	public void addQualifiedCourseForTutor(String username, String courseCode) {
		if (username == null || username.trim().length() == 0)
			throw new IllegalArgumentException("Username cannot be empty!");
		if (!tutorRepository.existsByUsername(username)) 
			throw new IllegalArgumentException("This tutor does not exist!");
		if (!courseRepository.existsByCourseCode(courseCode)) {
			throw new IllegalArgumentException("Course to add to tutor does not exist!");
		}
		Tutor tutor = tutorRepository.findTutorByUsername(username);
		Set<Course> qualifiedcourses = tutor.getCourse();
		Course course = courseRepository.findCourseByCourseCode(courseCode);
		qualifiedcourses.add(course);
		
		tutorRepository.save(tutor);//update tutor
		courseRepository.save(course); //update course
		return;
	}
	
	// Student
	/**@author Helen **/
	@Transactional
	public Student createStudent(String username, String firstname, String lastname) {
		if (username == null || username.trim().length() == 0)
			throw new IllegalArgumentException("Username cannot be empty!");
		if (firstname == null || firstname.trim().length() == 0)
			throw new IllegalArgumentException("First name cannot be empty!");
		if (lastname == null || lastname.trim().length() == 0)
			throw new IllegalArgumentException("Last name cannot be empty!");

		Student student = new Student();
		student.setUsername(username);
		student.setFirstName(firstname);
		student.setLastName(lastname);
		studentRepository.save(student);
		return student;
	}
	
	//manager
	/**@author Helen **/
	@Transactional
	public Manager createManager(String username, String firstname, String lastname) {
		if (username == null || username.trim().length() == 0)
			throw new IllegalArgumentException("Username cannot be empty!");
		if (firstname == null || firstname.trim().length() == 0)
			throw new IllegalArgumentException("First name cannot be empty!");
		if (lastname == null || lastname.trim().length() == 0)
			throw new IllegalArgumentException("Last name cannot be empty!");

		Manager manager = new Manager();
		manager.setUsername(username);
		manager.setFirstName(firstname);
		manager.setLastName(lastname);
		managerRepository.save(manager);
		return manager;
	}
	

	// TODO: Odero's services
	// TODO: create Room given a type
	// TODO: create SmallRoom, max 10
	// TODO: create ClassRoom, max 3

	// TODO: Arianit's services
	// TODO: ScheduledGroupSession
	// TODO: ScheduledPrivateSession
	// TODO: ScheduledSession given a type

	/********* START of SessionRequest, GroupSessionRequest, PrivateSessionRequest ***************/
	/**
	 * This method creates a private session request from a student.
	 * @author Helen
	 * @param username
	 * @param courseOrSubject the name of the course or subject
	 * @param isForCourse true if making a course request, false for making a subject request
	 * @return 
	 */
	@Transactional
	public PrivateRequest createPrivateRequest(String username, String courseOrSubject, boolean isForCourse) {

		if (username == null || username.trim().length() == 0 || !studentRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("Username for student requestor does not exist or is incorrect!");
		}

		PrivateRequest request = new PrivateRequest();

		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		request.setDateCreated(date);

		Student requestor = studentRepository.findByUsername(username);
		request.setRequestor(requestor);

		//subject or course
		if (isForCourse) {
			if (!courseRepository.existsById(courseOrSubject)) {
				throw new IllegalArgumentException("Invalid course requested!");
			}
			request.setRequestedCourse(courseRepository.findCourseByCourseCode(courseOrSubject));
		} else {
			if (!subjectRepository.existsById(courseOrSubject)) {
				throw new IllegalArgumentException("Invalid subject requested!");
			}
			request.setRequestedSubject(subjectRepository.findSubjectByName(courseOrSubject));
		}

		privateRequestRepository.save(request);
		return request;
	}

	/** @author Helen */
	@Transactional
	public PrivateRequest getPrivateRequest(int id) {
		if (!privateRequestRepository.existsById(id)) {
			throw new IllegalArgumentException("Invalid PrivateSessionRequest id or request does not exist!");
		}
		return privateRequestRepository.findById(id);
	}

	/** @author Helen */
	public List<PrivateRequest> getAllPrivateRequests() {
		return toList(privateRequestRepository.findAll());
	}


	/**
	 * This method creates a group session request from a student.
	 * @author Helen
	 * @param username
	 * @param courseOrSubject the name of the course or subject
	 * @param isForCourse true if making a course request, false for making a subject request
	 * @return 
	 */
	@Transactional
	public GroupRequest createGroupRequest(String username, String courseOrSubject, boolean isForCourse) {

		if (username == null || username.trim().length() == 0 || !studentRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("Username for student requestor does not exist or is incorrect!");
		}

		GroupRequest request = new GroupRequest();

		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		request.setDateCreated(date);

		Student requestor = studentRepository.findByUsername(username);
		request.setRequestor(requestor);

		//subject or course
		if (isForCourse) {
			if (!courseRepository.existsById(courseOrSubject)) {
				throw new IllegalArgumentException("Invalid course requested!");
			}
			request.setRequestedCourse(courseRepository.findCourseByCourseCode(courseOrSubject));
		} else {
			if (!subjectRepository.existsById(courseOrSubject)) {
				throw new IllegalArgumentException("Invalid subject requested!");
			}
			request.setRequestedSubject(subjectRepository.findSubjectByName(courseOrSubject));
		}

		groupRequestRepository.save(request);
		return request;
	}


	/** @author Helen */
	@Transactional
	public GroupRequest getGroupRequest(int id) {
		if (!privateRequestRepository.existsById(id)) {
			throw new IllegalArgumentException("Invalid GroupSessionRequest id or request does not exist!");
		}
		
		GroupRequest request = groupRequestRepository.findById(id);
		return request;
	}


	/** @author Helen */
	public List<GroupRequest> getAllGroupRequests() {
		return toList(groupRequestRepository.findAll());
	}

	/** @author Helen */
	public List<SessionRequest> getAllSessionRequests() {

		return toList(sessionRequestRepository.findAll());
	}


	/********* START of COURSE ***************/
	/** @author Alba */
	@Transactional
	public Course createCourse(String courseCode, String name, String teachingInstitution) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Course name cannot be empty!");
		}
		if (courseCode == null || courseCode.trim().length() == 0) {

			throw new IllegalArgumentException("Course code cannot be empty!");
		}
		if (teachingInstitution == null || teachingInstitution.trim().length() == 0) {

			throw new IllegalArgumentException("Associated school cannot be empty!");
		}

		
		
		Course course = new Course();
		course.setName(name);
		course.setCourseCode(courseCode);
		if (!teachingInstitutionRepository.existsByName(teachingInstitution)) {
			TeachingInstitution school = this.createTeachingInstitution(teachingInstitution);
			course.setSchool(school);
		} else {
			TeachingInstitution school = teachingInstitutionRepository.findTeachingInstitutionByName(teachingInstitution);
			course.setSchool(school);
		}
		
		courseRepository.save(course);
		return course;
	}

	/** @author Alba */
	@Transactional
	public Course getCourse(String courseCode, String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Course name cannot be empty!");
		}
		if (courseCode == null || courseCode.trim().length() == 0) {

			throw new IllegalArgumentException("Course code cannot be empty!");
		}

		Course course = courseRepository.findCourseByCourseCode(courseCode);
		return course;
	}

	/** @author Alba */
	@Transactional
	public List<Course> getAllCourse() {
		return toList(courseRepository.findAll());
	}

	/********* END of COURSE *** ***********/

	/********* START of SUBJECT *** ***********/

	/** @author Alba */
	@Transactional
	public Subject createSubject(String name, String schoolname) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Subject name cannot be empty!");
		}
		if (schoolname == null || schoolname.trim().length() == 0) {

			throw new IllegalArgumentException("Associated school cannot be empty!");
		}

		
		Subject subject = new Subject();
		subject.setName(name);
		if (!teachingInstitutionRepository.existsByName(schoolname)) {
			TeachingInstitution school = this.createTeachingInstitution(schoolname);
			subject.setSchool(school);
		} else {
			TeachingInstitution school = teachingInstitutionRepository.findTeachingInstitutionByName(schoolname);
			subject.setSchool(school);
		}
		subjectRepository.save(subject);
		return subject;
		
	}

	//TODO: add type parameter to create Teaching Institution (must be UNIVERSITY, CEGEP, OTHER)
	
	/** @author Alba */
	@Transactional
	public Subject getSubject(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Subject name cannot be empty!");
		}

		Subject subject = subjectRepository.findSubjectByName(name);
		return subject;
	}

	/** @author Alba */
	@Transactional
	public List<Subject> getAllSubject() {
		return toList(subjectRepository.findAll());
	}

	/********* END of SUBJECT *** ***********/

	/********* START of TEACHING INSTITUTION ***********/
	/** @author Alba */
	@Transactional
	public TeachingInstitution createTeachingInstitution(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Teaching Institution name cannot be empty!");
		}

		TeachingInstitution teachingInstitution = new TeachingInstitution();
		teachingInstitution.setName(name);
		teachingInstitutionRepository.save(teachingInstitution);
		return teachingInstitution;
	}

	/** @author Alba */
	@Transactional
	public TeachingInstitution getTeachingInstitution(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Teaching Institution name cannot be empty!");
		}

		TeachingInstitution teachingInstitution = teachingInstitutionRepository.findTeachingInstitutionByName(name);
		return teachingInstitution;
	}

	/** @author Alba */
	@Transactional
	public List<TeachingInstitution> getAllTeachingInstitution() {
		return toList(teachingInstitutionRepository.findAll());
	}

	/********* END of TEACHING INSTITUTION ***********/

	/********** END of EVALUATION *********/

	/********** START of EVALUATION AND EVALUATION COMMENT *********/

	/** @author Alba */
	@SuppressWarnings("deprecation")
	@Transactional
	public TutorEvaluation createTutorEvaluation(int rating, Student student, Tutor tutor) {
		if (rating == 0) {
			throw new IllegalArgumentException("Rating cannot be empty!");
		}
		String error = "";
		if (tutor == null) {
			error = error + "Tutor needs to be selected for Tutor Evaluation! ";
		} else if (!tutorRepository.existsByUsername(tutor.getUsername()))
			;
		{
			error = error + "Tutor does not exist! ";
		}
		if (student == null) {
			error = error + "Student needs to be selected for Tutor Evaluation!";
		} else if (!studentRepository.existsByUsername(student.getUsername())) {
			error = error + "Student does not exist!";
		}

		if (tutorEvaluationRepository.existsByAuthorAndRecipient(student, tutor)) {
			error = error + "Student already gave an evaluation for this Tutor!";
		}
		error = error.trim();

		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		// add today's date
		java.util.Date utilToday = new java.util.Date();
		java.sql.Date date = new java.sql.Date(utilToday.getTime()); // convert util todays date in ms and add to
																		// sqldate

		TutorEvaluation tutorEval = new TutorEvaluation();
		tutorEval.setRating(rating);

		tutorEval.setDate(date);
		tutorEval.setId((student.getUsername().hashCode()) * (tutor.getUsername().hashCode()));
		tutorEval.setAuthor(student);
		tutorEval.setRecipient(tutor);

		tutorEvaluationRepository.save(tutorEval);
		return tutorEval;
	}

	/** @author Alba */
	@Transactional
	public TutorEvaluation getTutorEvaluation(Student student, Tutor tutor) {
		String error = "";
		if (tutor == null) {
			error = error + "Tutor needs to be selected for Tutor Evaluation! ";
		} else if (!tutorRepository.existsByUsername(tutor.getUsername()))
			;
		{
			error = error + "Tutor does not exist! ";
		}
		if (student == null) {
			error = error + "Student needs to be selected for Tutor Evaluation!";
		} else if (!studentRepository.existsByUsername(student.getUsername())) {
			error = error + "Student does not exist!";
		}

		if (tutorEvaluationRepository.existsByAuthorAndRecipient(student, tutor)) {
			error = error + "Student already gave an evaluation for this Tutor!";
		}
		error = error.trim();

		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		TutorEvaluation tutorEval = tutorEvaluationRepository.findByAuthorAndRecipient(student, tutor);
		return tutorEval;
	}


	/** @author Alba */
	@Transactional
	public List<TutorEvaluation> getAllTutorEvaluation() {
		return toList(tutorEvaluationRepository.findAll());
	}
	
	/** @author Alba */
	@Transactional
	public StudentEvaluation createStudentEvaluation(int rating, Student student, Tutor tutor) {
		if (rating == 0) {
			throw new IllegalArgumentException("Rating cannot be empty!");
		}
		String error = "";
		if (tutor == null) {
			error = error + "Tutor needs to be selected for Student Evaluation! ";
		} else if (!tutorRepository.existsByUsername(tutor.getUsername()))
			;
		{
			error = error + "Tutor does not exist! ";
		}
		if (student == null) {
			error = error + "Student needs to be selected for Student Evaluation!";
		} else if (!studentRepository.existsByUsername(student.getUsername())) {
			error = error + "Student does not exist!";
		}

		if (studentEvaluationRepository.existsByRecipientAndAuthor(student, tutor)) {
			error = error + "Tutor already gave an evaluation for this Student!";
		}
		error = error.trim();

		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		// add today's date
		java.util.Date utilToday = new java.util.Date();
		java.sql.Date date = new java.sql.Date(utilToday.getTime()); // convert util todays date in ms and add to
																		// sqldate

		StudentEvaluation studentEval = new StudentEvaluation();
		studentEval.setRating(rating);

		studentEval.setDate(date);
		studentEval.setId((student.getUsername().hashCode()) * (tutor.getUsername().hashCode()));
		studentEval.setAuthor(tutor);;
		studentEval.setRecipient(student);

		studentEvaluationRepository.save(studentEval);
		return studentEval;
	}

	/** @author Alba */
	@Transactional
	public StudentEvaluation getStudentEvaluation(Student student, Tutor tutor) {
		String error = "";
		if (tutor == null) {
			error = error + "Tutor needs to be selected for Student Evaluation! ";
		} else if (!tutorRepository.existsByUsername(tutor.getUsername()))
			;
		{
			error = error + "Tutor does not exist! ";
		}
		if (student == null) {
			error = error + "Student needs to be selected for Student Evaluation!";
		} else if (!studentRepository.existsByUsername(student.getUsername())) {
			error = error + "Student does not exist!";
		}

		if (studentEvaluationRepository.existsByRecipientAndAuthor(student, tutor)) {
			error = error + "Tutor already gave an evaluation for this Student!";
		}
		error = error.trim();

		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		StudentEvaluation studentEval = studentEvaluationRepository.findByRecipientAndAuthor(student, tutor);
		return studentEval;
	}

	/** @author Alba */
	@Transactional
	public List<StudentEvaluation> getAllStudentEvaluation() {
		return toList(studentEvaluationRepository.findAll());
	}
	
	/** @author Alba */
	@Transactional
	public List<Evaluation> getAllEvaluation() {
		return toList(evaluationRepository.findAll());
	}

	
	//TODO: get a comment of an given eval id
	//TODO: delete a comment of a given eval id
	
	/********** END of EVALUATION *********/
	


}
