package net.skideo.repository;

import net.skideo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CountryRepository extends JpaRepository<Country,Long> {
}
