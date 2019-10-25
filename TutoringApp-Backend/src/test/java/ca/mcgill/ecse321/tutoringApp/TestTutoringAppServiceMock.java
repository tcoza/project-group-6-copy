package ca.mcgill.ecse321.tutoringApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringapp.dao.*;
import ca.mcgill.ecse321.tutoringapp.model.*;
import ca.mcgill.ecse321.tutoringapp.service.TutoringAppService;

@RunWith(MockitoJUnit.class)
public class TestTutoringAppServiceMock {


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
	


	
}