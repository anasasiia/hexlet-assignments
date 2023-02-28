package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getWeatherInTheCity(@PathVariable Long id) throws JsonProcessingException {
        City city = this.cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));
        return this.weatherService.getWeatherFromInternet(city.getName());
    }

    @GetMapping(path = "/search")
    public Iterable<Map<String, String>> getCities(@RequestParam(required = false) String name) {
        Iterable<City> filteredCities;
        if (name == null) {
            filteredCities = this.cityRepository.findAllByOrderByNameAsc();
        } else {
            filteredCities = this.cityRepository.findByNameStartingWithIgnoreCase(name);
        }
        List<Map<String, String>> citiesList = new ArrayList<>();
        filteredCities.forEach(city -> {
            Map<String, String> fullInformationAboutCity = weatherService.getWeatherFromInternet(city.getName());
            citiesList.add(Map.of("temperature", fullInformationAboutCity.get("temperature"),
                    "name", fullInformationAboutCity.get("name")));
        });
        return citiesList;
    }
    // END
}

