package net.skideo.service.city;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.City;
import net.skideo.repository.CityRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public City getCity(String name, String country) {
        return cityRepository.findByNameAndCountryName(name,country).orElseThrow(
                () -> new NotFoundException("City not found")
        );
    }
}
