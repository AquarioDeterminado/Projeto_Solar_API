package pt.iade.projetosolar.controllers.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;

public interface SubscriptionRecordRepository extends CrudRepository<SubscriptionRecord, Integer> { }
