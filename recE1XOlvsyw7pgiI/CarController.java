import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/search")
    public List<Car> searchCars(@RequestParam String brand,
                                @RequestParam int year,
                                @RequestParam double price) {
        return carService.findCarsByCriteria(brand, year, price);
    }
}
