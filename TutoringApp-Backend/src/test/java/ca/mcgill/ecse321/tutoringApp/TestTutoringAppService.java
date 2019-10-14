package ca.mcgill.ecse321.tutoringApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

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
import ca.mcgill.ecse321.tutoringapp.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringapp.dao.SubjectRepository;
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
	private GroupRequestRepository groupSessionRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private OfferingRepository offeringRepository;
	@Autowired
	private ScheduledPrivateSessionRepository privateSessionRepository;
	@Autowired
	private ScheduledGroupSessionRepository scheduledGroupSessionRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private ScheduledSessionRepository scheduledSessionRepository;
	@Autowired
	private SmallRoomRepository smallRoomRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private TutorRepository tutorRepository;
	@Autowired
	GroupRequestRepository groupRequestRepository;
	@Autowired
	PrivateRequestRepository privateRequestRepository;
	@Autowired
	SessionRequestRepository sessionRequestRepository;

	/** @author Alba */
	@Before
	public void clearDatabase() {
		appUserRepository.deleteAll();
		classRoomRepository.deleteAll();
		courseRepository.deleteAll();
		evaluationCommentRepository.deleteAll();
		evaluationRepository.deleteAll();
		groupSessionRepository.deleteAll();
		managerRepository.deleteAll();
		offeringRepository.deleteAll();
		privateSessionRepository.deleteAll();
		roomRepository.deleteAll();
		scheduledSessionRepository.deleteAll();
		smallRoomRepository.deleteAll();
		studentRepository.deleteAll();
		subjectRepository.deleteAll();
		tutorRepository.deleteAll();
		scheduledGroupSessionRepository.deleteAll();
		sessionRequestRepository.deleteAll();
		groupRequestRepository.deleteAll();
		privateRequestRepository.deleteAll();
	}

	/** @author Alba */
	@Test
	public void testCreateCourse() {
		assertEquals(0, service.getAllCourse());

		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";
		String school = "McGill";

		try {
			service.createCourse(courseCode, name, school);
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
		assertEquals(1, service.getAllCourse());

		String testName = "";
		String testCourseCode = "";
		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";
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
		assertEquals(1, service.getAllCourse().size());

		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";

		Course course = service.getCourse(courseCode, name);

		String error = "";

		try {
			TeachingInstitution uni = course.getSchool();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("TeachingInstitution does not exist!", error);
		assertEquals(0, service.getAllTeachingInstitutiont().size());

	}

	/** @author Alba */
	@Test
	public void testCreateSubject() {
		assertEquals(0, service.getAllSubject().size());

		String name = "Math";

		try {
			service.createSubject(name);
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
		assertEquals(1, service.getAllSubject().size());

		String testName = "";
		String name = "Math";
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
		assertEquals(1, service.getAllSubject().size());

		String name = "Math";

		Subject sbj = service.getSubject(name);

		String error = "";

		try {
			TeachingInstitution uni = sbj.getSchool();
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("TeachingInstitution does not exist!", error);
		assertEquals(0, service.getAllTeachingInstitutiont().size());
	}

	/** @author Alba */
	@Test
	public void testTeachingInstitutionCourse() {
		assertEquals(0, service.getAllTeachingInstitutiont().size());

		String name = "Mcgill";

		try {
			service.createTeachingInstitution(name);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<TeachingInstitution> allSchools = service.getAllTeachingInstitutiont();

		assertEquals(1, allSchools.size());
		assertEquals(name, allSchools.get(0).getName());
	}

	/** @author Alba */
	@Test
	public void testGetTeachingInstitutionAttribute() {
		assertEquals(1, service.getAllTeachingInstitutiont().size());

		String testName = "";
		String name = "Mcgill";

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
		assertEquals(1, service.getAllTeachingInstitutiont().size());
		assertEquals(1, service.getAllSubject().size());
		assertEquals(1, service.getAllCourse().size());

		String nameSc = "Mcgill";
		String nameC = "Intro To Software ENG";
		String courseCode = "ECSE321";
		String nameSb = "Math";

		TeachingInstitution uny = service.getTeachingInstitution(nameSc);
		Subject sbj = service.getSubject(nameSb);
		Course course = service.getCourse(courseCode, nameC);

		course.setSchool(uny);
		sbj.setSchool(uny);
		TeachingInstitution school1 = null;
		TeachingInstitution school2 = null;

		try {
			school1 = sbj.getSchool();
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			school2 = course.getSchool();
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(uny, school1);
		assertEquals(uny, school2);

	}
	
	
	
	/** Test to create a private session request for a course
	 * @author Helen
	 * */
	@Test
	public void testCreatePrivateRequest() {
		assertEquals(0, service.getAllPrivateRequests().size());

		String username = "helen-m-lin";
		String course = "ECSE321";
		boolean isCourse = true;

		try {
			service.createCourse("ECSE321", "Software Engineering", "McGill");
			service.createStudent(username);
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
			service.createStudent(username);
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
	 * Test that group request repository can persist information to students @author Helen */
	@Test
	public void testGetGroupRequestAttributeAndAssociation() {
		assertEquals(0, service.getAllGroupRequests());

		String name = "testUser1";
		String course = "MATH240"; 
		boolean isCourse = true;
		service.createStudent(name);
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

		String name = "user1";
		String c1 = "ECSE321";
		String c2 = "Math240";

		Student testStudent = new Student();
		try {
			GroupRequest r1 = service.createGroupRequest(name, c1, true);
			PrivateRequest r2 = service.createPrivateRequest(name, c2, true);
			testStudent = studentRepository.findByUsername(name);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, studentRepository.count());
		assertEquals(2, sessionRequestRepository.findByRequestor(testStudent).size());
		assertEquals(testStudent, sessionRequestRepository.findByRequestor(testStudent).get(0));
	}

}