package sierp.springteam1.controller.j_projectcontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    
    //프로젝트 세부리스트 출력
    @GetMapping("/detail")
    public String printProjectDetail(int pjno){
        System.out.println("J_ProjectPageController.printProjectDetail");

        return "/j_projectPage/projectDetail";
    }//m end

    //프로젝트 세부 리스트 정보 가져오기
    @GetMapping("/detail.do")
    @ResponseBody
    public ProjectDto getProjectDetail(int pjno){
        System.out.println("J_ProjectPageController.printProjectDetail");

        return j_projectPageService.getProjectDetail(pjno);
    }//m end

    //프로젝트 내역 수정페이지 호출
    @GetMapping("/update")
    public String updateProjectDetailPage(){
        System.out.println("J_ProjectPageController.updateProjectDetail");

        return "/j_projectPage/updateProject";
    }//m end

    //프로젝트 내역 수정
    @PutMapping("/update.do")
    @ResponseBody
    public boolean updateProjectDetail(ProjectDto projectDto){
        System.out.println("J_ProjectPageController.updateProjectDetail");
        System.out.println("projectDto = " + projectDto);

        return j_projectPageService.updateProjectDetail(projectDto);
    }//m end

    //프로젝트 내역 삭제

    //프로젝트 등록 페이지 출력
    @GetMapping("/insert")
    public String insertProjectPage(){
        return "/j_projectPage/insertProject";
    }//m end

    //프로젝트 등록
    @PostMapping("/insert.do")
    @ResponseBody
    public int insertProject(ProjectDto projectDto){
        System.out.println("J_ProjectPageController.insertProject");
        System.out.println("projectDto = " + projectDto);

        return j_projectPageService.insertProject(projectDto);
    }//m end

}//c end
