package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.Room;

public interface RoomReposetory extends CrudRepository<Room, Integer> {

}
