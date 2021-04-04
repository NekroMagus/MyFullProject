package net.skideo.service.country;

import lombok.RequiredArgsConstructor;
import net.skideo.model.Academy;
import net.skideo.model.Country;
import net.skideo.repository.base.BaseRepository;
import net.skideo.repository.base.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final LocationRepository<Country> repository;

    @Override
    public Country getCountry(String name) {
        Optional<Country> optionalCountry = repository.findByName(name);
        if(!optionalCountry.isPresent()) {
            Country country = new Country(name);
            repository.save(country);
            return country;
        }
        return optionalCountry.get();
    }
}
