package sierp.springteam1.service.projectservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import sierp.springteam1.model.dao.projectcontroller.OneprojectDao;
import sierp.springteam1.model.dto.ProjectDto;

@Service
public class OneprojectService {

    @Autowired
    OneprojectDao oneprojectDao;
    public ProjectDto oneProject(int pjno){
        System.out.println("OneprojectController.oneProject");
        System.out.println(pjno);
        return oneprojectDao.oneProject(pjno);
    }
}
