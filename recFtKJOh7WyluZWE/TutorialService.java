import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorialService {
    @Autowired
    private TutorialRepository tutorialRepository;

}
