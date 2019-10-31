package ca.mcgill.ecse321.tutoringApp;

import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.invocation.InvocationOnMock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringapp.dao.*;
import ca.mcgill.ecse321.tutoringapp.model.*;
import ca.mcgill.ecse321.tutoringapp.service.TutoringAppService;
import ca.mcgill.ecse321.tutoringapp.controller.TutoringAppRestController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
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

	//TODO
	//private static final String PERSON_KEY = "TestPerson";
	//private static final String NONEXISTING_KEY = "NotAPerson";
	
	/** 
	 * TODO write test for create multiple students, multiple tutors, setting course and subjects
	 * Validation tutor and add course to them, create requests and book them to rooms
	 * Give feedback (evaluation) 
	 */
	
	@Test
	public void integration()
	{
		service.createUser("STUDENT", "tcoza", "Traian", "Coza");
		service.createUser("MANAGER", "odero", "Odero", "Otieno");
		service.createUser("MANAGER", "arianit", "Arianit", "Vavla");
		service.createUser("TUTOR", "alba", "Alba", "Talelli");
		service.createUser("TUTOR", "helen", "Helen", "Lin");
		assertEquals(appUserRepository.count(), 5);
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
		
		
		
	}
}