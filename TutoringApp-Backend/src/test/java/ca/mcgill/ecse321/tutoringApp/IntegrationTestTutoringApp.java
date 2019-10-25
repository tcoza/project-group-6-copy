package ca.mcgill.ecse321.tutoringApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
public class IntegrationTestTutoringApp {

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
	
	/** 
	 * TODO write test for create multiple students, multiple tutors, setting course and subjects
	 * Validation tutor and add course to them, create requests and book them to rooms
	 * Give feedback (evaluation) 
	 */
	

}
