package ca.mcgill.ecse321.tutoringApp;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import ca.mcgill.ecse321.tutoringapp.dao.*;
import ca.mcgill.ecse321.tutoringapp.model.Evaluation;
import ca.mcgill.ecse321.tutoringapp.service.TutoringAppService;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class IntegrationTestTutoringApp {

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
	 * 			-MockitoJUnitRunner.StrictStubs automatically detects stubbing argument mismatches
	 * 			-Initializes mocks annotated with Mock,so that explicit usage of MockitoAnnotations.initMocks(Object) is not necessary.
	 * 			-Mocks are initialized before each test method. 
	 * 			-It is highly recommended to use MockitoJUnitRunner.StrictStubs variant of the runner.
	 * 				It drives cleaner tests and improves debugging experience.
	 */
	
	
	@Test
	public void integration()
	{
		service.createUser("STUDENT", "tcoza", "Traian", "Coza");
		service.createUser("MANAGER", "odero", "Odero", "Otieno");
		service.createUser("MANAGER", "arianit", "Arianit", "Vavla");
		service.createUser("TUTOR", "alba", "Alba", "Talelli");
		service.createUser("TUTOR", "helen", "Helen", "Lin");
		assertEquals(service.getAllUsers().size(), 5);
		service.changeTutorStatus("alba", "VERIFIED");
		service.changeTutorStatus("helen", "VERIFIED");
		service.createTeachingInstitution("Magill", "UNIVERSITY");
		service.createTeachingInstitution("Concordia", "UNIVERSITY");
		service.createCourse("ECSE321", "Introduction to Software", "Magill");
		service.createCourse("ECSE324", "Introduction to Polish", "Magill");
		service.createCourse("FINE432", "Financial stuff", "Concordia");
		service.addQualifiedCourseForTutor("helen", "ECSE321");
		service.addQualifiedCourseForTutor("alba", "ECSE321");
		service.addQualifiedCourseForTutor("helen", "ECSE324");
		service.createSmallRoom();
		service.createClassRoom();
		int id = service.createSmallRoom().getId();
		assertEquals(service.getAllRooms().size(), 3);
		service.createPrivateRequest("tcoza", "ECSE321", true);
		service.createScheduledPrivateSession("alba", id, new java.sql.Time(System.currentTimeMillis() + 100));
		Evaluation
		eval = service.createStudentEvaluation(7, service.getStudent("tcoza"), service.getTutor("alba"));
		service.createEvalComment(eval, "Great job although your methods should take string arguments");
		eval = service.createTutorEvaluation(9, service.getStudent("tcoza"), service.getTutor("alba"));
		service.createEvalComment(eval, "Bruh Arianit also did them like that but alright");
	}

}



