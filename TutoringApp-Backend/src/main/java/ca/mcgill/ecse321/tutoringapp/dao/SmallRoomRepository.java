package ca.mcgill.ecse321.tutoringapp.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringapp.model.SmallRoom;

public interface SmallRoomReposetory extends CrudRepository<SmallRoom, Integer> {

}
