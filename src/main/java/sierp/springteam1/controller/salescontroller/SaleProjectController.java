package sierp.springteam1.controller.salescontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.model.dto.ProjectDto2;
import sierp.springteam1.service.salesService.SalesService;

@Controller
@RequestMapping("/sales")
public class SaleProjectController {

    @Autowired
    SalesService salesService;

    //1.등록
    @PostMapping("/Post.do")
    @ResponseBody
    public boolean salesPost(ProjectDto2 projectDto2){
        System.out.println("SaleProjectController.salesPost");
        return salesService.salesPost(projectDto2);
    }
    //2.리스트

    //1.등록

    //2.리스트

}
