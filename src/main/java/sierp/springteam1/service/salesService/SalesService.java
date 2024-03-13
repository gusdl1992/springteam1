package sierp.springteam1.service.salesService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sierp.springteam1.model.dao.salesDao.SalesDao;
import sierp.springteam1.model.dto.ProjectDto2;

import java.util.List;

@Service
public class SalesService {


    @Autowired
    SalesDao salesDao;


    public boolean salesPost(int spjno){
        System.out.println("SalesService.salesPost");
        return salesDao.salesPost( spjno);
    }

    public List<ProjectDto2> saleslist(){
        System.out.println("SaleProjectController.salesPost");
        return salesDao.saleslist();
    }
}
