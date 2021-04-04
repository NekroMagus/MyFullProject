package net.skideo.repository;

import net.skideo.model.Country;
import net.skideo.repository.base.LocationRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CountryRepository extends LocationRepository<Country> {
}
