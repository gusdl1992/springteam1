package sierp.springteam1.controller.j_projectcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sierp.springteam1.model.dao.j_projectPageDao.J_projectPageDao;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.model.dto.ProjectPageDto;
import sierp.springteam1.service.j_projectPage.J_projectPageService;

import java.util.List;
import java.util.Map;

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
    public ProjectPageDto printProjectList(int page, int pageBoardSize,
                                           int sortKey,
                                           String key, String keyword,
                                            int startPrice, int endPrice){
        System.out.println("J_ProjectPageController.printProjectList");
        System.out.println("page = " + page + ", pageBoardSize = " + pageBoardSize + ", key = " + key + ", keyword = " + keyword + ", startPrice = " + startPrice + ", endPrice = " + endPrice);

        return j_projectPageService.printProjectList(page, pageBoardSize,sortKey, key, keyword, startPrice, endPrice);
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

    //프로젝트 삭제
    @DeleteMapping("/delete")
    @ResponseBody
    public boolean deleteProject(int pjno){
        System.out.println("J_ProjectPageController.deleteProject");
        System.out.println("pjno = " + pjno);

        return j_projectPageService.deleteProject(pjno);
    }

    //평가 가능한 프로젝트 출력
    @GetMapping("/perform")
    public String pringPerform(){
        System.out.println("J_ProjectPageController.pringPerform");
        return "/j_projectPage/perform";
    }
}//c end
