package net.skideo.repository;

import net.skideo.model.City;
import net.skideo.repository.base.LocationRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CityRepository extends LocationRepository<City> {
}
