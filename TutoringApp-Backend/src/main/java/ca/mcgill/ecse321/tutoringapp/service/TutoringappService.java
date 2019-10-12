package ca.mcgill.ecse321.tutoringapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.tutoringapp.dao.AppUserRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ClassRoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringapp.dao.EvaluationCommentRepository;
import ca.mcgill.ecse321.tutoringapp.dao.EvaluationRepository;
import ca.mcgill.ecse321.tutoringapp.dao.GroupSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ManagerRepository;
import ca.mcgill.ecse321.tutoringapp.dao.OfferingRepository;
import ca.mcgill.ecse321.tutoringapp.dao.PrivateSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.ScheduledSessionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.SmallRoomRepository;
import ca.mcgill.ecse321.tutoringapp.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringapp.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringapp.dao.TutorRepository;

@Service
public class TutoringappService {
	
	@Autowired
	AppUserRepository appUserRepository;
	@Autowired
	ClassRoomRepository calssRoomRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	EvaluationCommentRepository evaluationCommentRepository;
	@Autowired
	EvaluationRepository evaluationRepository;
	@Autowired
	GroupSessionRepository groupSessionRepository;
	@Autowired
	ManagerRepository managerRepository;
	@Autowired
	OfferingRepository offeringRepository;
	@Autowired 
	PrivateSessionRepository privateSessionRepository;
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	ScheduledSessionRepository shceduledSessionRepository;
	@Autowired
	SmallRoomRepository smallRoomRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	TutorRepository tutorRepository;

}
