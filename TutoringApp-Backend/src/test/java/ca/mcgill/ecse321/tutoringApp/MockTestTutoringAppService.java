package ca.mcgill.ecse321.tutoringApp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import ca.mcgill.ecse321.tutoringapp.dao.*;
import ca.mcgill.ecse321.tutoringapp.model.*;
import ca.mcgill.ecse321.tutoringapp.service.TutoringAppService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class MockTestTutoringAppService {

	@Mock
	private TeachingInstitutionRepository teachingInstitutionRepository;
	@Mock
	private AppUserRepository appUserRepository;
	@Mock
	private StudentRepository studentRepository;
	@Mock
	private ManagerRepository managerRepository;
	@Mock
	private TutorRepository tutorRepository;
	@Mock
	private ClassRoomRepository classRoomRepository;
	@Mock
	private CourseRepository courseRepository;
	@Mock
	private EvaluationCommentRepository evaluationCommentRepository;
	@Mock
	private EvaluationRepository evaluationRepository;
	@Mock
	private StudentEvaluationRepository studentEvaluationRepository;
	@Mock
	private TutorEvaluationRepository tutorEvaluationRepository;
	@Mock
	private RoomRepository roomRepository;
	@Mock
	private ScheduledSessionRepository scheduledSessionRepository;
	@Mock
	private ScheduledGroupSessionRepository scheduledGroupSessionRepository;
	@Mock
	private ScheduledPrivateSessionRepository scheduledPrivateSessionRepository;
	@Mock
	private SmallRoomRepository smallRoomRepository;
	@Mock
	private SubjectRepository subjectRepository;
	@Mock
	private GroupRequestRepository groupRequestRepository;
	@Mock
	private PrivateRequestRepository privateRequestRepository;
	@Mock
	private SessionRequestRepository sessionRequestRepository;
	
	@InjectMocks
	private TutoringAppService service;

	
	/*
	 * NOTE: In this test suite, we use Mockito and dependency injection to run MOCK tests.
	 * 
	 * By running this class with @RunWith(MockitoJUnitRunner.StrictStubs.class),
	 * we are able to skip the setUpMockOutput() and also skip MockitoAnnotations.initMocks(Object).
	 * 
	 * So, using this StrictStubs runner also allows us to create cleaner tests.
	 * 
	 * From the MockitoJUnitRunner.StrictStubs JavaDoc: 
	 * 			• MockitoJUnitRunner.StrictStubs automatically detects stubbing argument mismatches
	 * 			• Initializes mocks annotated with Mock,so that explicit usage of MockitoAnnotations.initMocks(Object) is not necessary.
	 * 			• Mocks are initialized before each test method. 
	 * 			•It is highly recommended to use MockitoJUnitRunner.StrictStubs variant of the runner.
	 * 				It drives cleaner tests and improves debugging experience.
	 */
	
	
	//TODO add all unit tests from TestTutoringAppService.java once they are done to run as mock tests

	/** @author Helen */
	@Test
	public void testCreateStudent() {
		assertEquals(0, studentRepository.count());
		
		String username = "testusername";
		String first = "First";
		String last = "Last";
		Student student = null;
		try {
			student = (Student) service.createUser("STUDENT",username, first, last);
		}catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(student.getUsername(), username);
		assertEquals(student.getFirstName(), first);
		assertEquals(student.getLastName(), last);	
	}
}
