package pt.iade.projetosolar.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.events.EventType;

import java.util.ArrayList;

public interface EventTypeRepository extends CrudRepository<EventType, Integer> { }
