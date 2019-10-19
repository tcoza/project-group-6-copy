package ca.mcgill.ecse321.tutoringApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringapp.dao.AppUserRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ClassRoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringapp.dao.EvaluationCommentRepository;
import ca.mcgill.ecse321.tutoringapp.dao.EvaluationRepository;
import ca.mcgill.ecse321.tutoringapp.dao.GroupRequestRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ManagerRepository;
import ca.mcgill.ecse321.tutoringapp.dao.OfferingRepository;
import ca.mcgill.ecse321.tutoringapp.dao.PrivateRequestRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ScheduledPrivateSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ScheduledGroupSessionRepository;
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
import ca.mcgill.ecse321.tutoringapp.model.GroupRequest;
import ca.mcgill.ecse321.tutoringapp.model.PrivateRequest;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Subject;
import ca.mcgill.ecse321.tutoringapp.model.TeachingInstitution;
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
	private ManagerRepository managerRepository;
	@Autowired
	private OfferingRepository offeringRepository;
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
	private StudentRepository studentRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private TutorRepository tutorRepository;
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
		//order matters to avoid inconsistencies
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
		offeringRepository.deleteAll();
		

	}
	
	
	/** @author Helen */
	@Test
	public void testCreateStudent() {
		assertEquals(0, studentRepository.count());
		
		String username = "testusername";
		String first = "First";
		String last = "Last";
		
		service.createStudent(username, first, last);
		assertEquals(studentRepository.findByUsername(username).getUsername(), username);
		assertEquals(studentRepository.findByUsername(username).getFirstName(), first);
		assertEquals(studentRepository.findByUsername(username).getLastName(), last);
		
	}
	
	/** @author Alba */
	@SuppressWarnings("null")
	@Test
	public void testCreateCourse() {
		assertEquals(0, service.getAllCourse().size());
		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";
		String nameS = "Mcgill";
		
		service.createTeachingInstitution(nameS);
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
		
		service.createTeachingInstitution(nameS);
		
		assertEquals(1, service.getAllTeachingInstitution().size());

		
		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();
		
		String school = allSchools.get(0).getClass().getName();
		
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
		
		service.createTeachingInstitution(nameS);
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
		
		assertEquals(uny, school);

	}

	/** @author Alba */
	@Test
	public void testCreateSubject() {
		assertEquals(0, service.getAllSubject().size());

		String name = "Math";
		
		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String nameS = "MSL";
		
		service.createTeachingInstitution(nameS);
		
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

		String testName = "";
		String name = "Math";

		assertEquals(0, service.getAllTeachingInstitution().size());
		
		String nameS = "MSL";
		
		service.createTeachingInstitution(nameS);
		
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
		
		service.createTeachingInstitution(nameS);
		
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
	public void testTeachingInstitutionCreate() {
		assertEquals(0, service.getAllTeachingInstitution().size());

		String name = "Mcgill";

		try {
			service.createTeachingInstitution(name);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<TeachingInstitution> allSchools = service.getAllTeachingInstitution();

		assertEquals(1, allSchools.size());
		assertEquals(name, allSchools.get(0).getName());
	}

	/** @author Alba */
	@Test
	public void testGetTeachingInstitutionAttribute() {
		assertEquals(0, service.getAllTeachingInstitution().size());

		String testName = "";
		String name = "Mcgill";
		
		service.createTeachingInstitution(name);
		
		assertEquals(1, service.getAllTeachingInstitution().size());

		try {
			testName = service.getTeachingInstitution(name).getName();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(name, testName);
	}

	/** @author Alba */
	@Test
	public void testTeachingInstitutionAssosiation() {
		assertEquals(0, service.getAllTeachingInstitution().size());
		assertEquals(0, service.getAllSubject().size());
		assertEquals(0, service.getAllCourse().size());
		
		String unyName = "Mcgill";
		String schoolName = "Happy HighSchool";
		String nameC = "Intro To Software ENG";
		String courseCode = "ECSE321";
		String nameSb = "Math";
		
		service.createTeachingInstitution(unyName);
		service.createTeachingInstitution(schoolName);
		
		assertEquals(2, service.getAllTeachingInstitution().size());
		
		TeachingInstitution uny = service.getTeachingInstitution(unyName);

		service.createCourse(courseCode, nameC, uny.getName());
		
		assertEquals(1, service.getAllCourse().size());

		TeachingInstitution school = service.getTeachingInstitution(schoolName);
		
		service.createSubject(nameSb, school.getName());
		
		assertEquals(1, service.getAllSubject().size());
		
		Course course = service.getCourse(courseCode, nameC);
		Subject sbj = service.getSubject(nameSb);


		Set<Course> courseTest = null;
		Set<Subject> subjectTest = null;

		try {
			courseTest = uny.getCourse();
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			subjectTest = school.getSubject();
		} catch (IllegalArgumentException e) {
			fail();
		}

		

		assertEquals(course, courseTest);
		assertEquals(sbj, subjectTest);

	}	
	
	/** Test to create a private session request for a course
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
			service.createStudent(username, "Helen", "Lin");
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

	/** Test to create a group session request for a subject
	 * @author Helen
	 * */
	public void testCreateGroupRequest() {
		assertEquals(0, service.getAllPrivateRequests());

		String username = "Bob1";
		String subject = "Math";
		boolean isCourse = false;

		try {
			service.createStudent(username, "Bob", "Test");
			service.createGroupRequest(username, subject, isCourse);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<GroupRequest> allRequests = service.getAllGroupRequests();

		assertEquals(1, allRequests.size());
		assertEquals(username, allRequests.get(0).getRequestor().getUsername());
		assertEquals(subject, allRequests.get(0).getRequestedCourse().getName());
	}

	/**
	 * Test that group request repository can persist information to students
	 * @author Helen */
	@Test
	public void testGetGroupRequestAttributeAndAssociation() {
		assertEquals(0, service.getAllGroupRequests());

		String name = "testUser1";
		String course = "MATH240"; 
		boolean isCourse = true;
		service.createStudent(name, "TestUser", "TestLastName");
		GroupRequest request = service.createGroupRequest(name, course, isCourse);

		String testCourseCode="";
		int testId =0;
		Student testStudent = new Student();;

		try {
			testId = request.getId();
			testCourseCode = service.getAllGroupRequests().get(0).getRequestedCourse().getCourseCode();
			testStudent = service.getAllGroupRequests().get(0).getRequestor();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(name, request.getRequestor().getUsername());
		assertEquals(course, testCourseCode);
		assertEquals(testStudent, studentRepository.findByUsername(name));
	}

	/** @author Helen */
	@Test
	public void testSessionRequestAssociation() {
		assertEquals(0, service.getAllSessionRequests().size());

		String username = "user1";
		String first = "testFirst";
		String last = "testLast";
		String c1 = "ECSE321";
		String c1Name = "SoftwareEng";
		String c2 = "Math240";
		String c2Name = "Discrete Structures";
		String school = "McGill University";

		Student testStudent = service.createStudent(username,first, last);
		service.createTeachingInstitution(school);
		service.createCourse(c1, c1Name, school);
		service.createCourse(c2, c2Name, school);
		try {
			service.createGroupRequest(username, c1, true);
			service.createPrivateRequest(username, c2, true);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(2, courseRepository.count());
		assertEquals(1, studentRepository.count());
		assertEquals(2, service.getAllSessionRequests().size());
		assertEquals(2, sessionRequestRepository.findByRequestor(testStudent).size());
		sessionRequestRepository.deleteAll();
		
	}

}