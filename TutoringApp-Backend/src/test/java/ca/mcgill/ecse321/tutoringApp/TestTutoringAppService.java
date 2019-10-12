package ca.mcgill.ecse321.tutoringApp;

import org.junit.Before;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringAppService {
	
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

}
