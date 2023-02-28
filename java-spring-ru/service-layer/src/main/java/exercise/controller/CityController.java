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
    public Iterable<Map<String, String>> getCities(@RequestParam(value = "name", required = false) String prefix) {
        Iterable<City> cities;
        if (prefix == null) {
            cities = this.cityRepository.findAllByOrderByNameAsc();
        } else {
            cities = this.cityRepository.findByNameStartingWithIgnoreCase(prefix);
        }
        List<Map<String, String>> citiesList = new ArrayList<>();
        cities.forEach(city -> {
            try {
                Map<String, String> fullInformationAboutCity = weatherService.getWeatherFromInternet(city.getName());
                Map<String, String> nameAndTemperature = new HashMap<>();
                nameAndTemperature.put("temperature", fullInformationAboutCity.get("temperature"));
                nameAndTemperature.put("name", fullInformationAboutCity.get("name"));
                citiesList.add(nameAndTemperature);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return citiesList;
    }
    // END
}

