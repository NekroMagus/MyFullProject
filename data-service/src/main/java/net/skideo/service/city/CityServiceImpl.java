package net.skideo.service.city;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Academy;
import net.skideo.model.City;
import net.skideo.model.Country;
import net.skideo.repository.base.BaseRepository;
import net.skideo.repository.base.LocationRepository;
import net.skideo.service.country.CountryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final LocationRepository<City> repository;
    private final CountryService countryService;

    @Override
    public City getCity(String name, String country) {
        Optional<City> optionalCity = repository.findByName(name);
        if(!optionalCity.isPresent()) {
            City city = new City(name,countryService.getCountry(country));
            repository.save(city);
            return city;
        }
        return optionalCity.get();
    }
}
