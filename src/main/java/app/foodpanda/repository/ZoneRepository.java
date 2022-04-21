package app.foodpanda.repository;

import app.foodpanda.model.Zone;

public interface ZoneRepository extends AbstractRepository<Zone> {

    Zone findByName(String name);
}
