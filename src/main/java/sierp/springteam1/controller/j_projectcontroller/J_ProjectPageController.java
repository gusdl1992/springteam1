package sierp.springteam1.controller.j_projectcontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.model.dao.j_projectPageDao.J_projectPageDao;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.service.j_projectPage.J_projectPageService;

import java.util.List;

@Controller
@RequestMapping("/projectPage")
public class J_ProjectPageController {
    @Autowired
    J_projectPageService j_projectPageService;
    @Autowired
    private HttpServletRequest request;


    //프로젝트 관리메뉴 출력
    @GetMapping("/")
    public String test(){
        System.out.println("Jcontroller1.test");
        return "/j_projectPage/projectPage";
    }//m end
    
    //프로젝트 전체 리스트 출력
    @GetMapping("/list")
    @ResponseBody
    public List<ProjectDto> printProjectList(){
        System.out.println("J_ProjectPageController.printProjectList");

        return j_projectPageService.printProjectList();
    }//m end

    //프로젝트 세부 리스트 출력
    @GetMapping("/detail")
    @ResponseBody
    public ProjectDto printProjectDetail(int pjno){
        System.out.println("J_ProjectPageController.printProjectDetail");

        return j_projectPageService.printProjectDetail(pjno);
    }//m end
}
