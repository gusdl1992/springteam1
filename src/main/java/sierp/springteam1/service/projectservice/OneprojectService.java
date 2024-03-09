package sierp.springteam1.service.projectservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sierp.springteam1.model.dao.projectcontroller.OneprojectDao;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.model.dto.ProjectlogDto;
import sierp.springteam1.model.dto.PsendEmployeeDto;

import java.util.ArrayList;
import java.util.Map;

@Service
public class OneprojectService {

    @Autowired
    OneprojectDao oneprojectDao;
    public ProjectDto oneProject(int pjno){
        System.out.println("OneprojectController.oneProject");
        System.out.println(pjno);
        return oneprojectDao.oneProject(pjno);
    }

    public ArrayList<PsendEmployeeDto>[] memberlist(int pjno){
        System.out.println("안뇽.memberlist");
        System.out.println(pjno);
        String start_date = oneprojectDao.oneProject(pjno).getStart_date();

        return oneprojectDao.memberlist(start_date);
    }

    public boolean createprojectlog(ProjectlogDto projectlogDto){
        return oneprojectDao.createprojectlog(projectlogDto);
    }

    public boolean updateprojectlog(ProjectlogDto projectlogDto) {
        boolean result = oneprojectDao.deleteprojectlog(projectlogDto);
        if (result) {

            return oneprojectDao.createprojectlog(projectlogDto);

        }
        return result;
    }

    public ArrayList<Integer> findlog( int pjno){
        return oneprojectDao.findlog(pjno);
    }

//    public int findscore(int eno){
//        return oneprojectDao.findscore(eno);
//    }
    public ArrayList<PsendEmployeeDto>[] updatememberlist(int pjno){
        String start_date = oneprojectDao.oneProject(pjno).getStart_date();
        return oneprojectDao.updatememberlist(pjno ,start_date);
    }
}
