package sierp.springteam1.controller.salescontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.model.dto.ProjectDto2;
import sierp.springteam1.service.salesService.SalesService;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleProjectController {

    @Autowired
    SalesService salesService;

    //1.등록
    @PostMapping("/Post.do")
    @ResponseBody
    public boolean salesPost(int spjno){
        System.out.println("SaleProjectController.salesPost");
        return salesService.salesPost(spjno);
    }
    //2.리스트
    @GetMapping("/list.do")
    @ResponseBody
    public List<ProjectDto2> saleslist(){
        System.out.println("SaleProjectController.salesPost");
        return salesService.saleslist();
    }
    //1.등록
    @GetMapping("/post")
    public String postview(){
       return "/sales.onesales";
    }
    //2.리스트

}
