
	package ca.mcgill.ecse321.tutoringapp.service;

	import java.sql.Date;
	import java.sql.Time;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Set;

	import javax.transaction.Transactional;

	import org.hibernate.hql.internal.ast.tree.DeleteStatement;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import ca.mcgill.ecse321.tutoringapp.dao.*;
	import ca.mcgill.ecse321.tutoringapp.model.*;

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


		// Tutor
		/** @author Traian **/
		@Transactional
		public AppUser createUser(String userType, String username, String firstname, String lastname) {
			if (userType == null || !userType.matches("(STUDENT|TUTOR|MANAGER)"))
				throw new IllegalArgumentException("Unknown user type '" + userType + "'!");
			if (username == null || username.trim().length() == 0)
				throw new IllegalArgumentException("Username cannot be empty!");
			if (appUserRepository.existsByUsername(username))
				throw new IllegalArgumentException("User '" + username + "' already exists!");
			if (firstname == null || firstname.trim().length() == 0)
				throw new IllegalArgumentException("First name cannot be empty!");
			if (lastname == null || lastname.trim().length() == 0)
				throw new IllegalArgumentException("Last name cannot be empty!");

			AppUser user =
					userType.equals("STUDENT") ? new Student() :
					userType.equals("TUTOR") ? new Tutor() :
					userType.equals("MANAGER") ? new Manager() :
					null;

			user.setUsername(username);
			user.setFirstName(firstname);
			user.setLastName(lastname);

			switch(userType)
			{
			case "STUDENT": studentRepository.save((Student)user); break;
			case "TUTOR": tutorRepository.save((Tutor)user); break;
			case "MANAGER": managerRepository.save((Manager)user); break;
			default: break;
			}
			
			return user;
		}

		/** @author Traian **/
		@Transactional
		public AppUser getUser(String username)
		{
			if (username == null || !appUserRepository.existsByUsername(username))
				throw new IllegalArgumentException("User '" + username + "' does not exist");
			return appUserRepository.findAppUserByUsername(username);
		}

		/** @author Traian Coza **/
		@Transactional
		public void changeTutorStatus(String username, String status) {
			if (username == null || !tutorRepository.existsByUsername(username)) 
				throw new IllegalArgumentException("This tutor does not exist!");
			if (status == null || !status.matches("(PENDING|VERIFIED|TERMINATED)"))
				throw new IllegalArgumentException("Unknown status '" + status + "'!");

			Tutor tutor = tutorRepository.findTutorByUsername(username);
			tutor.setStatus(
					status.equals("PENDING") ? TutorStatus.PENDING :
					status.equals("VERIFIED") ? TutorStatus.VERIFIED :
					status.equals("TERMINATED") ? TutorStatus.TERMINATED :
					null);
			
			if (status.equals("TERMINATED"))
				tutor.getCourse().clear();

			tutorRepository.save(tutor);
			return;
		}

		/** @author Traian */
		@Transactional
		public void setStudentStatus(String username, boolean active)
		{
			if (username == null || !studentRepository.existsByUsername(username))
				throw new IllegalArgumentException("Student '" + username + "' does not exist!");
			studentRepository.findByUsername(username).setIsActiveAccount(active);
		}
		
		@Transactional
		public Room getRoomByID(int id)
		{
			if (!roomRepository.existsById(id))
				throw new IllegalArgumentException("Room with id " + id + " does not exist!");
			return roomRepository.findById(id).get();
		}

		/**@author Helen **/
		@Transactional
		public void addQualifiedCourseForTutor(String username, String courseCode) {
			if (!tutorRepository.existsByUsername(username)) 
				throw new IllegalArgumentException("Tutor '" + username + "' does not exist!");
			if (!courseRepository.existsByCourseCode(courseCode)) {
				throw new IllegalArgumentException("Course '" + courseCode + "' does not exist!");
			}
			Tutor tutor = tutorRepository.findTutorByUsername(username);
			Set<Course> qualifiedcourses = tutor.getCourse();
			Course course = courseRepository.findCourseByCourseCode(courseCode);
			qualifiedcourses.add(course);

			tutorRepository.save(tutor);//update tutor
			courseRepository.save(course); //update course
			return;
		}


		// Get Student
		/** @author Alba Talelli */
		@Transactional
		public Student getStudent(String userName) {
			String error = "";
			if (userName == null) {
				error = error + "Student name needs to be provided";
			} else if (!studentRepository.existsByUsername(userName)){
				error = error + "Student does not exist!";
			}
			error = error.trim();

			if (error.length() > 0) {
				throw new IllegalArgumentException(error);
			}
			Student student = studentRepository.findByUsername(userName);
			return student;
		}
		
		/**@author Helen **/
		@Transactional
		public List<Student> getAllStudents() {
			return toList(studentRepository.findAll());
		}
		
		/**@author Helen **/
		@Transactional
		public List<Tutor> getAllTutors() {
			return toList(tutorRepository.findAll());
		}
		
		/**@author Helen **/
		@Transactional
		public List<Manager> getAllManagers() {
			return toList(managerRepository.findAll());
		}
		
		/**@author Helen **/
		@Transactional
		public List<AppUser> getAllUsers() {
			return toList(appUserRepository.findAll());
		}

		@Transactional
		public void deleteStudent(String username) {
			if (!studentRepository.existsByUsername(username))
				throw new IllegalArgumentException("Student does not exist!");
			studentRepository.delete(studentRepository.findByUsername(username));
		}
		
		@Transactional
		public void deleteTutor(String username) {
			if (!tutorRepository.existsByUsername(username))
				throw new IllegalArgumentException("Tutor does not exist!");
			tutorRepository.delete(tutorRepository.findTutorByUsername(username));
		}
		
		// Get Tutor
		/** @author Alba Talelli */
		@Transactional
		public Tutor getTutor(String userName){
			if (userName == null) {
				throw new IllegalArgumentException("Tutor name needs to be provided");
			}else if (!tutorRepository.existsByUsername(userName))
				throw new IllegalArgumentException("User '" + userName + "' does not exist");
			return tutorRepository.findTutorByUsername(userName);
		}


		// TODO: Odero's services
		
		
		/** @author Odero Otieno */	
		@Transactional
		public Room getRoomById(int id)
		{
			if (!roomRepository.existsById(id))
				throw new IllegalArgumentException("Room with id " + id + " does not exist!");
			return roomRepository.findById(id).get();
		}
		
		/** @author Odero Otieno */	
		@Transactional
		public List<Room> getAllRooms() {
			return toList(roomRepository.findAll());
		}
		
		
		// Create SmallRoom, max 10
		/** @author Odero Otieno */	
		@Transactional
		public SmallRoom createSmallRoom() {
			int id;		// Auto-generated id
			for (id = 1; roomRepository.existsById(id); id++);
			
			SmallRoom smallRoom = new SmallRoom();
			smallRoom.setId(id);
			smallRoomRepository.save(smallRoom);
			return smallRoom;
		}
		/** @author Odero Otieno */
		@Transactional
		public SmallRoom getSmallRoomById(int id)
		{
			if (!smallRoomRepository.existsById(id))
				throw new IllegalArgumentException("Small room with id " + id + " does not exist!");
			return smallRoomRepository.findById(id).get();
		}
		
		/** @author Odero Otieno */	
		public List<SmallRoom> getAllSmallRooms() {
			return toList(smallRoomRepository.findAll());
		}
		
		// Create ClassRoom, max 3
		/** @author Odero Otieno */	
		@Transactional
		
		public ClassRoom createClassRoom() {
			int id;		// Auto-generated id
			for (id = 1; roomRepository.existsById(id); id++);
			
			ClassRoom classRoom = new ClassRoom();
			classRoom.setId(id);
			classRoomRepository.save(classRoom);
			return classRoom;
		}
		
		/** @author Odero Otieno */	
		@Transactional
		public ClassRoom getClassRoomByID(int id)
		{
			if (!classRoomRepository.existsById(id))
				throw new IllegalArgumentException("Class room with id " + id + " does not exist!");
			return classRoomRepository.findById(id).get();
		}
		
		/** @author Odero Otieno */
		public List<ClassRoom> getAllClassRooms() {
			return toList(classRoomRepository.findAll());
		}


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
			if (courseOrSubject == null || courseOrSubject.trim().length() == 0) {
				throw new IllegalArgumentException("Invalid course or subject requested!");
			}
			if (isForCourse && !courseRepository.existsById(courseOrSubject))
				throw new IllegalArgumentException("Invalid course requested!");
			else if (!isForCourse && !subjectRepository.existsById(courseOrSubject))
				throw new IllegalArgumentException("Invalid subject requested!");
					
			PrivateRequest request = new PrivateRequest();
			request.setId((username.hashCode()) * (courseOrSubject.hashCode()));

			java.util.Date today = new java.util.Date();
			java.sql.Date date = new java.sql.Date(today.getTime());
			request.setDateCreated(date);

			Student requestor = studentRepository.findByUsername(username);
			request.setRequestor(requestor);

			//set subject or course
			if (isForCourse) {
				request.setRequestedCourse(courseRepository.findCourseByCourseCode(courseOrSubject));
			} else {
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
		@Transactional
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
			if (courseOrSubject == null || courseOrSubject.trim().length() == 0) {
				throw new IllegalArgumentException("Invalid course or subject requested!");
			}
			if (isForCourse && !courseRepository.existsById(courseOrSubject))
				throw new IllegalArgumentException("Invalid course requested!");
			else if (!isForCourse && !subjectRepository.existsById(courseOrSubject))
				throw new IllegalArgumentException("Invalid subject requested!");

			GroupRequest request = new GroupRequest();
			request.setId((username.hashCode()) * (courseOrSubject.hashCode()));
			java.util.Date today = new java.util.Date();
			java.sql.Date date = new java.sql.Date(today.getTime());
			request.setDateCreated(date);

			Student requestor = studentRepository.findByUsername(username);
			request.setRequestor(requestor);

			//set subject or course
			if (isForCourse) {
				request.setRequestedCourse(courseRepository.findCourseByCourseCode(courseOrSubject));
			} else {
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
		@Transactional
		public List<GroupRequest> getAllGroupRequests() {
			return toList(groupRequestRepository.findAll());
		}

		/** @author Helen */
		@Transactional
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
			} else if (!teachingInstitutionRepository.existsByName(teachingInstitution)) {
				throw new IllegalArgumentException("Associated school does not exist! School must be created first.");
			}

			Course course = new Course();
			course.setName(name);
			course.setCourseCode(courseCode);
			TeachingInstitution school = teachingInstitutionRepository.findTeachingInstitutionByName(teachingInstitution);
			course.setSchool(school);

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
			} else if (!teachingInstitutionRepository.existsByName(schoolname)) {
				throw new IllegalArgumentException("Associated school does not exist! School must be created first.");
			}

			Subject subject = new Subject();
			subject.setName(name);
			TeachingInstitution school = teachingInstitutionRepository.findTeachingInstitutionByName(schoolname);
			subject.setSchool(school);
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
		public TeachingInstitution createTeachingInstitution(String name, String type) {
			if (name == null || name.trim().length() == 0) {
				throw new IllegalArgumentException("Teaching Institution name cannot be empty!");
			}
			if (type == null || type.trim().length() == 0) {
				throw new IllegalArgumentException("Teaching Institution type cannot be empty!");
			}
			InstitutionType iType;
			switch (type) {
			case "UNIVERSITY":
				iType = InstitutionType.UNIVERSITY;
				break;
			case "CEGEP":
				iType = InstitutionType.CEGEP;
				break;
			case "HIGHSCHOOL":
				iType = InstitutionType.HIGHSCHOOL;
				break;
			case "OTHER":
				iType = InstitutionType.OTHER;
				break;
			default:
				throw new IllegalArgumentException("Invalid institution type! Options are UNIVERSITY, CEGEP, HIGHSCHOOL, or OTHER!");
			}

			TeachingInstitution teachingInstitution = new TeachingInstitution();
			teachingInstitution.setName(name);
			teachingInstitution.setType(iType);

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
			} else if (!tutorRepository.existsByUsername(tutor.getUsername())){
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
			} else if (!tutorRepository.existsByUsername(tutor.getUsername()))	{
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
			} else if (!tutorRepository.existsByUsername(tutor.getUsername()))	{
				error = error + "Tutor does not exist! ";
			}
			if (student == null) {
				error = error + "Student needs to be selected for Student Evaluation!";
			} else if (!studentRepository.existsByUsername(student.getUsername())) {
				error = error + "Student does not exist!";
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

		public EvaluationComment createEvalComment(Evaluation eval, String comment) {
			if (eval == null) {
				throw new IllegalArgumentException ("Evaluation can't be empty");
			}
			if (comment == null) {
				throw new IllegalArgumentException ("Comment can't be empty");
			}
			EvaluationComment evalComment = new EvaluationComment();
			evalComment.setComment(comment);
			evalComment.setEvaluation(eval);
			evaluationCommentRepository.save(evalComment);

			return evalComment;
		}

		/** @author Alba */
		@Transactional
		public EvaluationComment getEvalComment(Evaluation eval) {

			if (eval == null) {
				throw new IllegalArgumentException ("Evaluation can't be empty");
			}

			EvaluationComment evalComment = evaluationCommentRepository.findByEvaluation(eval);

			return evalComment;
		}
		// Can only be done from manager
		/** @author Alba */
		@Transactional
		public void deletEvalComment(Evaluation eval) {

			if (eval == null) {
				throw new IllegalArgumentException ("Evaluation can't be empty");
			}

			int ID = evaluationCommentRepository.findByEvaluation(eval).getId();

			if (!evaluationCommentRepository.existsById(ID)) {
				throw new IllegalArgumentException ("This evaluation do not have a comment");
			}
			else {


				EvaluationComment evalComment = evaluationCommentRepository.findByEvaluation(eval);
				evaluationCommentRepository.delete(evalComment);

			}
		}

		/********** END of EVALUATION *********/

		/** @author Arianit */
		@Transactional
		public ScheduledPrivateSession createScheduledPrivateSession(String tutorname, int smallRoomID, Time startTime) {
			Tutor tutor = getTutor(tutorname);
			SmallRoom smallRoom = getSmallRoomById(smallRoomID);
			
			String error = "";
			if (tutor == null) {
				error = error + "Tutor needs to be selected to create a scheduled private session!";
			} else if (!tutorRepository.existsByUsername(tutor.getUsername())){
				error = error + "Tutor does not exist!";
			}
			if (smallRoom == null) {
				error = error + "Room needs to be selected to create a scheduled private session!";
			} else if (!roomRepository.existsById((smallRoom.getId()))){
				error = error + "Room does not exist!";
			}
			error = error.trim();

			if (error.length() > 0) {
				throw new IllegalArgumentException(error);
			}
			ScheduledPrivateSession scheduledPrivateSession = new ScheduledPrivateSession();
			scheduledPrivateSession.setId((tutor.getUsername().hashCode()) * (startTime.toString().hashCode())*smallRoom.getId());
			scheduledPrivateSession.setAssignedTutor(tutor);
			scheduledPrivateSession.setDate(new java.sql.Date(System.currentTimeMillis()));
			scheduledPrivateSession.setStartTime(startTime);
			scheduledPrivateSession.setRoom(smallRoom);
			scheduledPrivateSessionRepository.save(scheduledPrivateSession);
			return scheduledPrivateSession;
		}

		/** @author Arianit */
		@Transactional
		public ScheduledPrivateSession getScheduledPrivateSession(String tutorname) {
			Tutor tutor = getTutor(tutorname);
			
			String error = "";
			if (tutor == null) {
				error = error + "Tutor needs to be selected to find a scheduled private session!";
			} else if (!tutorRepository.existsByUsername(tutor.getUsername())){
				error = error + "Tutor does not exist!";
			}
			error = error.trim();

			if (error.length() > 0) {
				throw new IllegalArgumentException(error);
			}
			return scheduledPrivateSessionRepository.findByAssignedTutor(tutor);
		}
		/** @author Arianit */
		@Transactional
		public void deleteScheduledPrivateSession(int id) {
			if (!scheduledPrivateSessionRepository.existsById(id))
				throw new IllegalArgumentException("Private session with id " + id + " does not exist!");
			scheduledPrivateSessionRepository.deleteById(id);
		}


		/** @author Arianit */
		@Transactional
		public List<ScheduledPrivateSession> getAllScheduledPrivateSession(){
			return toList(scheduledPrivateSessionRepository.findAll());
		}

		/** @author Arianit */
		@Transactional
		public ScheduledGroupSession createScheduledGroupSession(String tutorname, int classroomID, Time startTime) {
			Tutor tutor = getTutor(tutorname);
			ClassRoom classRoom = getClassRoomByID(classroomID);
			
			String error = "";
			if (tutor == null) {
				error = error + "Tutor needs to be selected to create a scheduled group session!";
			} else if (!tutorRepository.existsByUsername(tutor.getUsername())){
				error = error + "Tutor does not exist!";
			}
			if (classRoom == null) {
				error = error + "Room needs to be selected to create a scheduled group session!";
			} 
			else if (!roomRepository.existsById(classRoom.getId())){
				error = error + "Room does not exist!";
			}
			error = error.trim();

			if (error.length() > 0) {
				throw new IllegalArgumentException(error);
			}
			ScheduledGroupSession scheduledGroupSession = new ScheduledGroupSession();
			scheduledGroupSession.setId((tutor.getUsername().hashCode()) * (startTime.toString().hashCode())*classRoom.getId());
			scheduledGroupSession.setAssignedTutor(tutor);
			scheduledGroupSession.setDate(new java.sql.Date(System.currentTimeMillis()));
			scheduledGroupSession.setStartTime(startTime);
			scheduledGroupSession.setRoom(classRoom);
			scheduledGroupSessionRepository.save(scheduledGroupSession);
			return scheduledGroupSession;
		}

		/** @author Arianit */
		@Transactional
		public ScheduledGroupSession getScheduledGroupSession(String tutorname) {
			Tutor tutor = getTutor(tutorname);
			
			String error = "";
			if (tutor == null) {
				error = error + "Tutor needs to be selected to find a scheduled group session!";
			} else if (!tutorRepository.existsByUsername(tutor.getUsername())){
				error = error + "Tutor does not exist!";
			}
			error = error.trim();

			if (error.length() > 0) {
				throw new IllegalArgumentException(error);
			}
			ScheduledGroupSession scheduledGroupSession = scheduledGroupSessionRepository.findByAssignedTutor(tutor);
			return scheduledGroupSession;
		}
		/** @author Arianit */
		@Transactional
		public void deleteScheduledGroupSession(int id) {	
			if (!scheduledGroupSessionRepository.existsById(id))
				throw new IllegalArgumentException("Group session with id " + id + " does not exist!");
			
			scheduledGroupSessionRepository.deleteById(id);
				
		}
		
		/** @author Arianit */
		@Transactional
		public List<ScheduledGroupSession> getAllScheduledGroupSession(){
			return toList(scheduledGroupSessionRepository.findAll());
		}

		
	}

	
	