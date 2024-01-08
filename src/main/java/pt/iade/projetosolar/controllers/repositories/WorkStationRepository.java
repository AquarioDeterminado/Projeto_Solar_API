package pt.iade.projetosolar.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.models.dao.workstations.WorkStation;

public interface WorkStationRepository extends CrudRepository<WorkStation, Integer> { }
