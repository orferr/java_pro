import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tea")
public class TeaController {
    private List<Tea> teaList = new ArrayList<>();

    @GetMapping("/list")
    public List<Tea> getTeaList() {
        return teaList;
    }

    @PostMapping("/add")
    public void addTea(@RequestBody Tea tea) {
        teaList.add(tea);
    }
}
