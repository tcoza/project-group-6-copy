package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.ScheduledGroupSession;

public interface ScheduledGroupSessionRepository extends CrudRepository<ScheduledGroupSession, Integer> {

}
