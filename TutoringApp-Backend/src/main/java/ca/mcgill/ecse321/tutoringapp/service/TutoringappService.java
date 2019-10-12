package ca.mcgill.ecse321.tutoringapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import ca.mcgill.ecse321.tutoringapp.dao.TeachingInstitutionRepository;
import ca.mcgill.ecse321.tutoringapp.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.Subject;
import ca.mcgill.ecse321.tutoringapp.model.TeachingInstitution;

@Service
public class TutoringappService {
	
	@Autowired
	TeachingInstitutionRepository teachingInstitutionRepository;
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
	GroupRequestRepository groupSessionRepository;
	@Autowired
	ManagerRepository managerRepository;
	@Autowired
	OfferingRepository offeringRepository;
	@Autowired 
	ScheduledPrivateSessionRepository privateSessionRepository;
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
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	@Transactional
	public Course createCourse (String courseCode,String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Course name cannot be empty!");
		}
		if (courseCode == null || courseCode.trim().length() == 0) {
			
			throw new IllegalArgumentException("Course code cannot be empty!");
		}
		
		Course course = new Course();
		course.setName(name);
		course.setCourseCode(courseCode);
		courseRepository.save(course);
		return course;
	}
	
	@Transactional
	public Course getCourse (String courseCode,String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Course name cannot be empty!");
		}
		if (courseCode == null || courseCode.trim().length() == 0) {
			
			throw new IllegalArgumentException("Course code cannot be empty!");
		}
		
		Course course = courseRepository.findCourseByName(courseCode);
		return course;
	}
	
	@Transactional
	public List<Course> getAllCourse(){
		return toList(courseRepository.findAll());
	}
	
	@Transactional
	public Subject createSubject (String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Subject name cannot be empty!");
		}
		
		Subject subject = new Subject();
		subject.setName(name);
		subjectRepository.save(subject);
		return subject;
	}
	
	@Transactional
	public Subject getSubject (String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Subject name cannot be empty!");
		}
		
		Subject subject = subjectRepository.findSubjectByName(name);
		return subject;
	}
	
	@Transactional
	public List<Subject> getAllSubject(){
		return toList(subjectRepository.findAll());
	}
	
	@Transactional
	public TeachingInstitution createTeachingInstitution (String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Teaching Institution name cannot be empty!");
		}
		
		TeachingInstitution teachingInstitution = new TeachingInstitution();
		teachingInstitution.setName(name);
		teachingInstitutionRepository.save(teachingInstitution);
		return teachingInstitution;
	}
	
	@Transactional
	public TeachingInstitution getTeachingInstitutiont (String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Teaching Institutiont name cannot be empty!");
		}
		
		TeachingInstitution teachingInstitution = teachingInstitutionRepository.findTeachingInstitutionByName(name);
		return teachingInstitution;
	}
	
	@Transactional
	public List<TeachingInstitution> getAllTeachingInstitutiont(){
		return toList(teachingInstitutionRepository.findAll());
	}

}
