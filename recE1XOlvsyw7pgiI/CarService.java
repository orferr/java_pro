import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> findCarsByCriteria(String brand, int year, double price) {
        return carRepository.findByBrandAndYearAndPriceLessThanEqual(brand, year, price);
    }
}
