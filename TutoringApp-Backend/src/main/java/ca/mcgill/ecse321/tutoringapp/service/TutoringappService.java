package ca.mcgill.ecse321.tutoringapp.service;

import java.sql.Date;
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
import ca.mcgill.ecse321.tutoringapp.dao.TutorEvaluationRepository;
import ca.mcgill.ecse321.tutoringapp.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringapp.model.Course;
import ca.mcgill.ecse321.tutoringapp.model.Student;
import ca.mcgill.ecse321.tutoringapp.model.Subject;
import ca.mcgill.ecse321.tutoringapp.model.TeachingInstitution;
import ca.mcgill.ecse321.tutoringapp.model.Tutor;
import ca.mcgill.ecse321.tutoringapp.model.TutorEvaluation;

@Service
public class TutoringappService {
	
	@Autowired
	TutorEvaluationRepository tutorEvaluationRepository;
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
	@SuppressWarnings("deprecation")
	@Transactional
	public TutorEvaluation createTutorEvaluation (int rating,Student student, Tutor tutor) {
		if (rating == 0) {
			throw new IllegalArgumentException("Rating cannot be empty!");
		}
		String error = "";
	    if (tutor == null) {
	        error = error + "Tutor needs to be selected for Tutor Evaluation! ";
	    } else if (!tutorRepository.existsByUsername(tutor.getUsername()); {
	        error = error + "Tutor does not exist! ";
	    }
	    if (student == null) {
	        error = error + "Student needs to be selected for Tutor Evaluation!";
	    } else if (!studentRepository.existsByUsername(student.getUsername())) {
	        error = error + "Student does not exist!";
	    }
	    
		if (tutorEvaluationRepository.existsByStudentAndTutor(student, tutor)) {
	        error = error + "Student already gave an evaluation for this Tutor!";
	    }
	    error = error.trim();

	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		Date date;
		date.getDate();
		
		TutorEvaluation tutorEval = new TutorEvaluation();
		tutorEval.setRating(rating);
		
		tutorEval.setDate(date);
		tutorEval.setId((student.getUsername().hashCode())*(tutor.getUsername().hashCode()));
		tutorEval.setAuthor(student);
		tutorEval.setRecipient(tutor);
		
		tutorEvaluationRepository.save(tutorEval);
		return tutorEval;
	}
	
	@Transactional
	public TutorEvaluation getTutorEvaluation (Student student,Tutor tutor) {
		String error = "";
	    if (tutor == null) {
	        error = error + "Tutor needs to be selected for Tutor Evaluation! ";
	    } else if (!tutorRepository.existsByUsername(tutor.getUsername()); {
	        error = error + "Tutor does not exist! ";
	    }
	    if (student == null) {
	        error = error + "Student needs to be selected for Tutor Evaluation!";
	    } else if (!studentRepository.existsByUsername(student.getUsername())) {
	        error = error + "Student does not exist!";
	    }
	    
		if (tutorEvaluationRepository.existsByStudentAndTutor(student, tutor)) {
	        error = error + "Student already gave an evaluation for this Tutor!";
	    }
	    error = error.trim();

	    if (error.length() > 0) {
	        throw new IllegalArgumentException(error);
	    }
		
		TutorEvaluation tutorEval = tutorEvaluationRepository.findByStudentAndTutor(student, tutor);
		return tutorEval;
	}
	
	@Transactional
	public List<TutorEvaluation> getAllTutorEvaluation(){
		return toList(tutorEvaluationRepository.findAll());
	}

}
