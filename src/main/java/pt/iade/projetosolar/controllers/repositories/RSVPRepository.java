package pt.iade.projetosolar.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.events.RSPV;

public interface RSVPRepository extends CrudRepository<RSPV, Integer> { }
