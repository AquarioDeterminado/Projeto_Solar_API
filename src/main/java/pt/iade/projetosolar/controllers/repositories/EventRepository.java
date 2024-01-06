package pt.iade.projetosolar.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.models.dao.Event;

import java.util.ArrayList;

public interface EventRepository extends CrudRepository<Event, Integer> {
    ArrayList<Event> findByIsPublicIs(boolean isPublic);
}
