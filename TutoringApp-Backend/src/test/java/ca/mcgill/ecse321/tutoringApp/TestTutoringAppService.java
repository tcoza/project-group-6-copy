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
import ca.mcgill.ecse321.tutoringapp.dao.ScheduledPrivateSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ScheduledSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.SmallRoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringapp.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringapp.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.Subject;
import ca.mcgill.ecse321.tutoringapp.model.TeachingInstitution;
import ca.mcgill.ecse321.tutoringapp.service.TutoringappService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringAppService {
	
	@Autowired
	private TutoringappService service;
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private ClassRoomRepository calssRoomRepository;
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
	private RoomRepository roomRepository;
	@Autowired
	private ScheduledSessionRepository shceduledSessionRepository;
	@Autowired
	private SmallRoomRepository smallRoomRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private TutorRepository tutorRepository;

	@Before
	public void clearDatabase() {
		appUserRepository.deleteAll();
		calssRoomRepository.deleteAll();
		courseRepository.deleteAll();
		evaluationCommentRepository.deleteAll();
		evaluationRepository.deleteAll();
		groupSessionRepository.deleteAll();
		managerRepository.deleteAll();
		offeringRepository.deleteAll();
		privateSessionRepository.deleteAll();
		roomRepository.deleteAll();
		shceduledSessionRepository.deleteAll();
		smallRoomRepository.deleteAll();
		studentRepository.deleteAll();
		subjectRepository.deleteAll();
		tutorRepository.deleteAll();	
	}
	
	@Test
	public void testCreateCourse() {
		assertEquals(0, service.getAllCourse());
		
		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";

		try {
			service.createCourse(courseCode, name);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Course> allCourse = service.getAllCourse();

		assertEquals(1, allCourse.size());
		assertEquals(name, allCourse.get(0).getName());
		assertEquals(courseCode, allCourse.get(0).getCourseCode());
	}
	
	@Test
	public void testGetCourseAttribute() {
		assertEquals(1, service.getAllCourse());
		
		String testName = "";
		String testCourseCode = "";
		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";
		try {
			testName = service.getCourse(courseCode,name).getName();
		}catch (IllegalArgumentException e){
			fail();
		}
		try {
			testCourseCode = service.getCourse(courseCode,name).getCourseCode();
		}catch (IllegalArgumentException e){
			fail();
		}
		
		
		assertEquals(name,testName);
		assertEquals(courseCode,testCourseCode);
	}
	
	@Test
	public void testCourseAssosiation() {
		assertEquals(1,service.getAllCourse().size());
		
		String name = "Intro To Software ENG";
		String courseCode = "ECSE321";
		
		Course course = service.getCourse(courseCode, name);
				
		String error = "";
		
		try {
			TeachingInstitution uny = course.getSchool();
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("TeachingInstitution does not exist!", error);
		assertEquals(0,service.getAllTeachingInstitutiont().size());
		
	}
	
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
	
	@Test
	public void testGetSubjectAttribute() {
		assertEquals(1, service.getAllSubject().size());
		
		String testName = "";
		String name = "Math";
		try {
			testName = service.getSubject(name).getName();
		}catch (IllegalArgumentException e){
			fail();
		}
		
		assertEquals(name,testName);
	}
	
	@Test
	public void testSubjectAssosiation() {
		assertEquals(1,service.getAllSubject().size());
		
		String name = "Math";
		
		Subject sbj = service.getSubject(name);
				
		String error = "";
		
		try {
			TeachingInstitution uny = sbj.getSchool();
		}catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("TeachingInstitution does not exist!", error);
		assertEquals(0,service.getAllTeachingInstitutiont().size());
	}
	
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
	@Test
	public void testGetTeachingInstitutionAttribute() {
		assertEquals(1, service.getAllTeachingInstitutiont().size());
		
		String testName = "";
		String name = "Mcgill";
		
		try {
			testName = service.getTeachingInstitutiont(name).getName();
		}catch (IllegalArgumentException e){
			fail();
		}
		
		assertEquals(name,testName);
	}
	@Test
	public void testTeachingInstitutionAssosiation() {
		assertEquals(1,service.getAllTeachingInstitutiont().size());
		assertEquals(1,service.getAllSubject().size());
		assertEquals(1,service.getAllCourse().size());
		
		String nameSc = "Mcgill";
		String nameC = "Intro To Software ENG";
		String courseCode = "ECSE321";
		String nameSb =  "Math";
		
		TeachingInstitution uny = service.getTeachingInstitutiont(nameSc);
		Subject sbj = service.getSubject(nameSb);
		Course course = service.getCourse(courseCode, nameC);
		
		course.setSchool(uny);
		sbj.setSchool(uny);
		TeachingInstitution school1 = null;
		TeachingInstitution school2 = null;

		try {
			school1 = sbj.getSchool();
		}catch (IllegalArgumentException e) {
			fail();
		}
		
		try {
			school2 = course.getSchool();
		}catch (IllegalArgumentException e) {
			fail();
		}
		
		assertEquals(uny,school1);
		assertEquals(uny,school2);
	
	}
}