package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    Daytime daytime;

    @Autowired
    Meal meal;

    @GetMapping(path = "/daytime")
    public String sayBonAppetit() {
        String daytimeName = daytime.getName();
        return "It is " + daytimeName + " now. Enjoy your " + meal.getMealForDaytime(daytimeName);
    }
}
// END
