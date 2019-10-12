package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.ScheduledSession;

public interface ScheduledSessionReposetory extends CrudRepository<ScheduledSession, Integer> {

}
