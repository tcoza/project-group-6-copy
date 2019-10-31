package ca.mcgill.ecse321.tutoringApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringapp.dao.*;
import ca.mcgill.ecse321.tutoringapp.model.*;
import ca.mcgill.ecse321.tutoringapp.service.TutoringAppService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringAppService {

	@Autowired
	private TutoringAppService service;
	@Autowired
	private TeachingInstitutionRepository teachingInstitutionRepository;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private TutorRepository tutorRepository;
	@Autowired
	private ClassRoomRepository classRoomRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private EvaluationCommentRepository evaluationCommentRepository;
	@Autowired
	private EvaluationRepository evaluationRepository;
	@Autowired
	private StudentEvaluationRepository studentEvaluationRepository;
	@Autowired
	private TutorEvaluationRepository tutorEvaluationRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private ScheduledSessionRepository scheduledSessionRepository;
	@Autowired
	private ScheduledGroupSessionRepository scheduledGroupSessionRepository;
	@Autowired
	private ScheduledPrivateSessionRepository scheduledPrivateSessionRepository;
	@Autowired
	private SmallRoomRepository smallRoomRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private GroupRequestRepository groupRequestRepository;
	@Autowired
	private PrivateRequestRepository privateRequestRepository;
	@Autowired
	private SessionRequestRepository sessionRequestRepository;
	

	/** @author Alba */
	@Before
	@After
	public void clearDatabase() {
		
		evaluationRepository.deleteAll();
		evaluationCommentRepository.deleteAll();
		tutorEvaluationRepository.deleteAll();
		studentEvaluationRepository.deleteAll();
		
		scheduledSessionRepository.deleteAll();
		scheduledGroupSessionRepository.deleteAll();
		scheduledPrivateSessionRepository.deleteAll();
		
		sessionRequestRepository.deleteAll();
		groupRequestRepository.deleteAll();
		privateRequestRepository.deleteAll();

		appUserRepository.deleteAll();
		studentRepository.deleteAll();
		tutorRepository.deleteAll();
		managerRepository.deleteAll();
		
		courseRepository.deleteAll();
		subjectRepository.deleteAll();
		teachingInstitutionRepository.deleteAll();
		
		classRoomRepository.deleteAll();
		smallRoomRepository.deleteAll();
		roomRepository.deleteAll();
	}
	
	// TODO write null tests and illegal argument imputs and more if you think of
	
	/** @author Helen */
	@Test
	public void testCreateStudent() {
		assertEquals(0, studentRepository.count());
		
		String username = "testusername";
		String first = "First";
		String last = "Last";
		
		try {
			service.createUser("STUDENT",username, first, last);
		}catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(studentRepository.findByUsername(username).getUsername(), username);
		assertEquals(studentRepository.findByUsername(username).getFirstName(), first);
		assertEquals(studentRepository.findByUsername(username).getLastName(), last);	
	}
	
	/** @author Alba */
	@Test
	public void testCreateCourse() {
		assertEquals(0, service.getAllCourse().size());
		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";
		String nameS = "Mcgill";
		
		service.createTeachingInstitution(nameS, "UNIVERSITY");
		TeachingInstitution school = service.getAllTeachingInstitution().get(0);

		try {
			service.createCourse(courseCode, name, school.getName());
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Course> allCourse = service.getAllCourse();

		assertEquals(1, allCourse.size());
		assertEquals(name, allCourse.get(0).getName());
		assertEquals(courseCode, allCourse.get(0).getCourseCode());
	}

	/** @author Alba */
	@Test
	public void testGetCourseAttribute() {
		assertEquals(0, service.getAllCourse().size());

		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";
		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String nameS = "Mcgill";
		service.createTeachingInstitution(nameS, "UNIVERSITY");
		assertEquals(1, service.getAllTeachingInstitution().size());

		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();
		
		String school = allSchools.get(0).getName();
		service.createCourse(courseCode, name, school);

		String testName = "";
		String testCourseCode = "";
		
		assertEquals(1, service.getAllCourse().size());
		
		try {
			testName = service.getCourse(courseCode, name).getName();
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			testCourseCode = service.getCourse(courseCode, name).getCourseCode();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(name, testName);
		assertEquals(courseCode, testCourseCode);
	}

	/** @author Alba */
	@Test
	public void testCourseAssosiation() {
		assertEquals(0, service.getAllCourse().size());
		assertEquals(0, service.getAllTeachingInstitution().size());

		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";
		String nameS = "Mcgill";
		
		service.createTeachingInstitution(nameS, "UNIVERSITY");
		assertEquals(1, service.getAllTeachingInstitution().size());
		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();
		
		TeachingInstitution school = allSchools.get(0);
		service.createCourse(courseCode, name, school.getName());
		assertEquals(1, service.getAllCourse().size());
		
		List<Course> courses = service.getAllCourse();
		Course course = courses.get(0);
		
		TeachingInstitution uny = null;

		try {
			uny = course.getSchool();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(uny.getName(), school.getName());

	}
	
	/** @author Alba */
	@Test
	public void testCreateNullUNCourse() {
		assertEquals(0, service.getAllCourse().size());
		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String name = "Software";
		String courseCode = null;
		String nameS = "Mcgill";
		String error = null;
		
		service.createTeachingInstitution(nameS, "UNIVERSITY");
		TeachingInstitution school = service.getAllTeachingInstitution().get(0);

		try {
			service.createCourse(courseCode, name, school.getName());
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Course code cannot be empty!", error);
		
	}
	
	/** @author Alba */
	@Test
	public void testCreateNullNameCourse() {
		assertEquals(0, service.getAllCourse().size());
		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String name = null;
		String courseCode = "ECSE321";
		String nameS = "Mcgill";
		String error = null;
		
		service.createTeachingInstitution(nameS, "UNIVERSITY");
		TeachingInstitution school = service.getAllTeachingInstitution().get(0);

		try {
			service.createCourse(courseCode, name, school.getName());
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Course name cannot be empty!", error);
		
	}


	/** @author Alba */
	@Test
	public void testCreateSubject() {
		assertEquals(0, service.getAllSubject().size());
		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String name = "Math";
		String nameS = "test high school";
		service.createTeachingInstitution(nameS, "HIGHSCHOOL");
		
		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();
		String school = allSchools.get(0).getName();
	
		try {
			service.createSubject(name,school);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Subject> allSubjects = service.getAllSubject();

		assertEquals(1, allSubjects.size());
		assertEquals(name, allSubjects.get(0).getName());
	}

	/** @author Alba */
	@Test
	public void testGetSubjectAttribute() {
		assertEquals(0, service.getAllSubject().size());
		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String testName = "";
		String name = "Math";
		String nameS = "Dawson CEGEP";
		service.createTeachingInstitution(nameS, "CEGEP");
		assertEquals(1, service.getAllTeachingInstitution().size());

		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();
		
		String school = allSchools.get(0).getName();
		service.createSubject(name, school);

		assertEquals(1, service.getAllSubject().size());
		
		try {
			testName = service.getSubject(name).getName();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(name, testName);
	}

	/** @author Alba */
	@Test
	public void testSubjectAssosiation() {
		assertEquals(0, service.getAllSubject().size());
		assertEquals(0, service.getAllTeachingInstitution().size());

		String name = "Math";
		String nameS = "MSL";
		
		service.createTeachingInstitution(nameS, "OTHER");
		
		assertEquals(1, service.getAllTeachingInstitution().size());

		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();
		
		String school = allSchools.get(0).getName();
		service.createSubject(name, school);
		assertEquals(1, service.getAllSubject().size());
		List<Subject> subjects = service.getAllSubject();
		
		Subject sbj = subjects.get(0);
		String uny = null;

		try {
			uny = sbj.getSchool().getName();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(uny, school);
	}
	
	/** @author Alba */
	@Test
	public void testCreateNullNameSubejct() {
		assertEquals(0, service.getAllSubject().size());
		assertEquals(0, service.getAllTeachingInstitution().size());
		String error = null;
		
		String name = null;
		String nameS = "test high school";
		service.createTeachingInstitution(nameS, "HIGHSCHOOL");
		
		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();
		String school = allSchools.get(0).getName();
	
		try {
			service.createSubject(name,school);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();;
		}
		
		assertEquals("Subject name cannot be empty!", error);
		
	}
	
	/** @author Alba */
	@Test
	public void testTeachingInstitutionCreate() {
		assertEquals(0, service.getAllTeachingInstitution().size());

		String name = "Mcgill";
		String iType = "UNIVERSITY";

		try {
			service.createTeachingInstitution(name, iType);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();

		assertEquals(1, allSchools.size());
		assertEquals(name, allSchools.get(0).getName());
		assertEquals(InstitutionType.UNIVERSITY, allSchools.get(0).getType());
	}

	/** @author Alba */
	@Test
	public void testGetTeachingInstitutionAttribute() {
		assertEquals(0, service.getAllTeachingInstitution().size());

		String testName = "";
		String testType = "";
		String name = "Mcgill";
		String iType = "UNIVERSITY";
		
		service.createTeachingInstitution(name, iType);
		
		assertEquals(1, service.getAllTeachingInstitution().size());

		try {
			testName = service.getTeachingInstitution(name).getName();
			testType = service.getTeachingInstitution(name).getType().toString();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(name, testName);
		assertEquals(InstitutionType.UNIVERSITY.toString(),testType);
	}

	/** @author Alba */
	@Test
	public void testTeachingInstitutionAssosiation() {
		assertEquals(0, teachingInstitutionRepository.count());
		assertEquals(0, subjectRepository.count());
		assertEquals(0, courseRepository.count());
		
		String unyName = "Mcgill";
		String schoolName = "Happy HighSchool";
		String nameC = "Intro To Software ENG";
		String courseCode = "ECSE321";
		String nameSb = "Math";
		
		service.createTeachingInstitution(unyName, "UNIVERSITY");
		service.createTeachingInstitution(schoolName, "HIGHSCHOOL");
		assertEquals(2, service.getAllTeachingInstitution().size());
		
		TeachingInstitution uny = service.getTeachingInstitution(unyName);
		service.createCourse(courseCode, nameC, uny.getName());
		assertEquals(1, service.getAllCourse().size());

		TeachingInstitution school = service.getTeachingInstitution(schoolName);
		service.createSubject(nameSb, school.getName());
		assertEquals(1, service.getAllSubject().size());
		
		Course course = service.getCourse(courseCode, nameC);
		Subject sbj = service.getSubject(nameSb);

		TeachingInstitution testSchool = null;
		TeachingInstitution testUny = null;
		try {
			testUny = teachingInstitutionRepository.findTeachingInstitutionByName(unyName);
			testSchool = teachingInstitutionRepository.findTeachingInstitutionByName(schoolName);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(course.getSchool().getName(), testUny.getName());
		assertEquals(sbj.getSchool().getName(), testSchool.getName());

	}	
	
	/** Positive test to create a private session request for a course
	 * @author Helen
	 * */
	@Test
	public void testCreatePrivateRequest() {
		assertEquals(0, service.getAllPrivateRequests().size());

		String username = "helen-m-lin";
		String course = "ECSE321";
		String courseName = "Software Eng";
		String school = "McGill";
		boolean isCourse = true;

		try {
			service.createUser("STUDENT",username, "Helen", "Lin");
			service.createTeachingInstitution(school, "UNIVERSITY");
			service.createCourse(course, courseName , school);
			service.createPrivateRequest(username, course, isCourse);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<PrivateRequest> allRequests = service.getAllPrivateRequests();

		assertEquals(1, allRequests.size());
		assertEquals(username, allRequests.get(0).getRequestor().getUsername());
		assertEquals(course, allRequests.get(0).getRequestedCourse().getCourseCode());
	}
	
	/** Tests all null and invalid input parameters to createPrivateRequest()
	 * @author Helen
	 * */
	@Test
	public void testInvalidCreatePrivateRequest() {
		assertEquals(0, service.getAllPrivateRequests().size());

		//set up test for course
		String username = "helen-m-lin";
		String course = "ECSE321";
		String courseName = "Software Eng";
		String school = "McGill";
		boolean isCourse = true;
		
		service.createUser("STUDENT", username, "Helen", "Lin");
		service.createTeachingInstitution(school, "UNIVERSITY");
		service.createCourse(course, courseName , school);
		
		try { //test null username
			service.createPrivateRequest(null, course, isCourse);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Username for student requestor does not exist or is incorrect!");
		}
		try { //test empty username
			service.createPrivateRequest("", course, isCourse);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Username for student requestor does not exist or is incorrect!");
		}
		try { //test invalid username
			service.createPrivateRequest("helen-lin", course, isCourse); //doesn't exist (the correct one is "helen-m-lin"
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Username for student requestor does not exist or is incorrect!");
		}
		try { //test null course
			service.createPrivateRequest(username, null, isCourse);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course or subject requested!");
		}
		try { //test empty course
			service.createPrivateRequest(username, "", isCourse); 
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course or subject requested!");
		}
		try { //test invalid course
			service.createPrivateRequest(username, "ECSE21", isCourse);  //incorrect course code
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course requested!");
		}
		
		//set up test for subject
		String sbj = "Math";
		school = "TestSchool";
		isCourse = false;
		service.createTeachingInstitution(school, "HIGHSCHOOL");
		service.createSubject(sbj, school);
		
		try { //test null sbj
			service.createPrivateRequest(username, null, isCourse);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course or subject requested!");
		}
		try { //test empty sbj
			service.createPrivateRequest(username, "", isCourse); 
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course or subject requested!");
		}
		try { //test invalid sbj
			service.createPrivateRequest(username, "mat", isCourse);  //incorrect course code
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid subject requested!");
		}
		List<PrivateRequest> allRequests = service.getAllPrivateRequests();	
		assertEquals(0, allRequests.size());
		assertEquals(1, studentRepository.count());
		assertEquals(1, courseRepository.count());
		assertEquals(1, subjectRepository.count());
		assertEquals(2, teachingInstitutionRepository.count());

	}

	/** Test to create a group session request for a subject
	 * @author Helen
	 * */
	@Test
	public void testCreateGroupRequest() {
		assertEquals(0, service.getAllGroupRequests().size());

		String username = "Bob1";
		String subject = "Math";
		String school = "schooltest";
		boolean isCourse = false;

		try {
			service.createUser("STUDENT",username, "Bob", "Test");
			service.createTeachingInstitution(school, "HIGHSCHOOL");
			service.createSubject(subject, school);
			service.createGroupRequest(username, subject, isCourse);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<GroupRequest> allRequests = service.getAllGroupRequests();

		assertEquals(1, allRequests.size());
		assertEquals(username, allRequests.get(0).getRequestor().getUsername());
		assertEquals(subject, allRequests.get(0).getRequestedSubject().getName());
	}
	
	/** Tests all null and invalid input parameters to createGroupRequest()
	 * @author Helen
	 * */
	@Test
	public void testInvalidCreateGroupRequest() {
		assertEquals(0, service.getAllGroupRequests().size());

		//set up test for course
		String username = "helen-m-lin";
		String course = "ECSE321";
		String courseName = "Software Eng";
		String school = "McGill";
		boolean isCourse = true;
		
		service.createUser("STUDENT", username, "Helen", "Lin");
		service.createTeachingInstitution(school, "UNIVERSITY");
		service.createCourse(course, courseName , school);
		
		try { //test null username
			service.createGroupRequest(null, course, isCourse);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Username for student requestor does not exist or is incorrect!");
		}
		try { //test empty username
			service.createGroupRequest("", course, isCourse);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Username for student requestor does not exist or is incorrect!");
		}
		try { //test invalid username
			service.createGroupRequest("helen-lin", course, isCourse); //doesn't exist (the correct one is "helen-m-lin"
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Username for student requestor does not exist or is incorrect!");
		}
		try { //test null course
			service.createGroupRequest(username, null, isCourse);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course or subject requested!");
		}
		try { //test empty course
			service.createGroupRequest(username, "", isCourse); 
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course or subject requested!");
		}
		try { //test invalid course
			service.createGroupRequest(username, "ECSE21", isCourse);  //incorrect course code
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course requested!");
		}
		
		//set up test for subject
		String sbj = "Math";
		school = "TestSchool";
		isCourse = false;
		service.createTeachingInstitution(school, "HIGHSCHOOL");
		service.createSubject(sbj, school);
		
		try { //test null sbj
			service.createGroupRequest(username, null, isCourse);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course or subject requested!");
		}
		try { //test empty sbj
			service.createGroupRequest(username, "", isCourse); 
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid course or subject requested!");
		}
		try { //test invalid sbj
			service.createGroupRequest(username, "mat", isCourse);  //incorrect course code
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Invalid subject requested!");
		}

		List<GroupRequest> allRequests = service.getAllGroupRequests();	
		assertEquals(0, allRequests.size());
		assertEquals(1, studentRepository.count());
		assertEquals(1, courseRepository.count());
		assertEquals(1, subjectRepository.count());
		assertEquals(2, teachingInstitutionRepository.count());
	}
	

	/**
	 * Test that group request repository can persist information to students
	 * @author Helen */
	@Test
	public void testGetGroupRequestAttributeAndAssociation() {
		assertEquals(0, groupRequestRepository.count());

		String name = "testUser1";
		String coursecode = "MATH240"; 
		String courseName = "Discrete Structures";
		String school = "McGill";
		boolean isCourse = true;
		service.createUser("STUDENT",name, "TestUser", "TestLastName");
		service.createTeachingInstitution(school, "UNIVERSITY");
		service.createCourse(coursecode, courseName, school);
		GroupRequest request = service.createGroupRequest(name, coursecode, isCourse);

		String testCourseCode="";
		String testStudent="";

		try {
			testCourseCode = service.getAllGroupRequests().get(0).getRequestedCourse().getCourseCode();
			testStudent = service.getAllGroupRequests().get(0).getRequestor().getFirstName();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(name, request.getRequestor().getUsername());
		assertEquals(coursecode, testCourseCode);
		assertEquals(testStudent, studentRepository.findByUsername(name).getFirstName());
	}

	/** @author Helen */
	@Test
	public void testSessionRequestAssociation() {
		assertEquals(0, sessionRequestRepository.count());

		String username1 = "user1";
		String username2 = "user2";
		String first = "testFirst";
		String last = "testLast";
		service.createUser("STUDENT",username1,first, last);
		service.createUser("STUDENT",username2,first, last);
		
		String school = "McGill University";
		service.createTeachingInstitution(school, "UNIVERSITY");
		
		String c1 = "ECSE321";
		String c1Name = "SoftwareEng";
		String c2 = "Math240";
		String c2Name = "Discrete Structures";
		service.createCourse(c1, c1Name, school);
		service.createCourse(c2, c2Name, school);
		
		boolean isCourse = true;
		
		service.createGroupRequest(username1, c1, isCourse);
		PrivateRequest request2 = service.createPrivateRequest(username2, c2, isCourse);
		service.createGroupRequest(username2, c1,isCourse);
		
		String testC1="";

		try {
			testC1 = service.getAllGroupRequests().get(0).getRequestedCourse().getCourseCode();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(2, courseRepository.count());
		assertEquals(2, studentRepository.count());
		assertEquals(1, privateRequestRepository.count());
		assertEquals(2, groupRequestRepository.count());
		assertEquals(3, sessionRequestRepository.count());
		
		assertEquals(c1, testC1);
		assertEquals(username2, request2.getRequestor().getUsername());
		assertEquals(username1, studentRepository.findByUsername(username1).getUsername());
		assertEquals(2, sessionRequestRepository.findByRequestor((Student)appUserRepository.findAppUserByUsername(username2)).size());
	}

	@Test
	public void testOneUser()
	{
		service.createUser("STUDENT", "tcoza", "Traian", "Coza");
		assertEquals(service.getUser("tcoza").getUsername(), "tcoza");
		assertEquals(appUserRepository.count(), 1);
		assertEquals(studentRepository.count(), 1);
		assertEquals(tutorRepository.count(), 0);
		assertThrows(IllegalArgumentException.class, () -> service.getUser("Non existent username haha"));
		assertThrows(IllegalArgumentException.class, () -> service.getUser(null));
		assertThrows(IllegalArgumentException.class, () -> service.createUser("Uhh type", "name", "Name", "Fancy name"));
		assertThrows(IllegalArgumentException.class, () -> service.createUser(null, "name", "Name", "Fancy name"));
		assertThrows(IllegalArgumentException.class, () -> service.createUser("STUDENT", null, "Name", "Fancy name"));
		assertThrows(IllegalArgumentException.class, () -> service.createUser("STUDENT", "name", null, "Fancy name"));
		assertThrows(IllegalArgumentException.class, () -> service.createUser("STUDENT", "name", "Name", null));
		assertThrows(IllegalArgumentException.class, () -> service.createUser("STUDENT", "tcoza", "Already", "Exists"));
		assertThrows(IllegalArgumentException.class, () -> service.deleteStudent(null));
		assertThrows(IllegalArgumentException.class, () -> service.deleteStudent("non existent"));
		service.deleteStudent("tcoza");
		assertEquals(studentRepository.count(), 0);
		assertEquals(appUserRepository.count(), 0);
	}
	
	@Test
	public void testTutor()
	{
		service.createUser("TUTOR", "alba", "Alba", "Talelli");
		service.createUser("TUTOR", "helen", "Helen", "Lin");
		assertEquals(tutorRepository.count(), 2);
		assertEquals(service.getUser("alba").getLastName(), "Talelli");
		assertEquals(((Tutor)service.getUser("helen")).getStatus(), TutorStatus.PENDING);
		assertThrows(IllegalArgumentException.class, () -> service.changeTutorStatus("helen", "Uhh status"));
		assertThrows(IllegalArgumentException.class, () -> service.changeTutorStatus(null, "Uhh status"));
		assertThrows(IllegalArgumentException.class, () -> service.changeTutorStatus("helen", null));
		service.changeTutorStatus("helen", "VERIFIED");
		assertEquals(((Tutor)service.getUser("helen")).getStatus(), TutorStatus.VERIFIED);
	}
	
	@Test
	public void testManager()
	{
		service.createUser("MANAGER", "odero", "Odero", "Otieno");
		service.createUser("MANAGER", "arianit", "Arianit", "Vavla");
		assertEquals(appUserRepository.count(), 2);
		assertEquals(managerRepository.count(), 2);
		assertEquals(service.getUser("arianit").getLastName(), "Vavla");
	}
	@Test
	public void testCreateScheduledPrivateSession() {
		Tutor tutor = (Tutor) service.createUser("TUTOR", "a", "a", "a");
		SmallRoom smallRoom = service.createSmallRoom(2);
		@SuppressWarnings("deprecation")
		Date date = new Date(2019, 10, 20);
		@SuppressWarnings("deprecation")
		Time startTime = new Time(13, 35, 00);
		service.createScheduledPrivateSession(tutor, smallRoom, date, startTime);
		assertEquals(1, scheduledPrivateSessionRepository.count());
	}
	
	@Test 
	public void testCreateScheduledGroupSession() {
		Tutor tutor = (Tutor) service.createUser("TUTOR", "a", "a", "a");
		ClassRoom classRoom = service.createClassRoom(1);
		@SuppressWarnings("deprecation")
		Date date = new Date(2019, 10, 20);
		@SuppressWarnings("deprecation")
		Time startTime = new Time(13, 35, 00);
		service.createScheduledGroupSession(tutor, classRoom, date, startTime);
		assertEquals(1, scheduledGroupSessionRepository.count());
	}
	
	@Test
	public void createNullScheduledPrivateSession() {
		Tutor tutor = null;
		SmallRoom smallRoom = null;
		Date date = null;
		Time startTime = null;
		service.createScheduledPrivateSession(tutor, smallRoom, date, startTime);
		assertEquals(0, scheduledPrivateSessionRepository.count());
	}
	
	@Test 
	public void createNullScheduledGroupSession() {
		Tutor tutor = null;
		ClassRoom classRoom = null;
		Date date = null;
		Time startTime = null;
		service.createScheduledGroupSession(tutor, classRoom, date, startTime);
		assertEquals(0, scheduledGroupSessionRepository.count());
	}
	

}
