package sierp.springteam1.controller.projectcontroller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.model.dto.ProjectlogDto;
import sierp.springteam1.service.projectservice.OneprojectService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/project")
public class OneprojectController {

    @Autowired
    OneprojectService oneprojectService;

    //세부게시물 페이지 출력
    @GetMapping("/view")
    public String oneProjectview(int pjno){
        return "project/oneproject";
    }
    //게시물의 인원 배정페이지 출력
    @GetMapping("/view/rec")
    public String oneProjectd(int pjno){
        return "project/recproject";
    }

    @GetMapping("/view/re")
    public String updateProjectd(int pjno){
        return "project/upemp";
    }
    @GetMapping("/view/rec.check")
    @ResponseBody
    public int findscore(int eno){

        return oneprojectService.findscore(eno);
    }

    @GetMapping("/view.do")
    @ResponseBody
    public ProjectDto oneProject(int pjno){
        System.out.println("OneprojectController.oneProject");
        System.out.println(pjno);
        return oneprojectService.oneProject(pjno);
    }
    @GetMapping("/view/re.do")
    @ResponseBody
    public ArrayList<EmployeeDto>[] updatememberlist(int pjno){
        System.out.println("\"ddddd\" = " + "ddddd");
        return oneprojectService.updatememberlist(pjno);
    }
    @GetMapping("/view/rec.do")
    @ResponseBody
    public  List<EmployeeDto>[] memberlist(@RequestParam int pjno){
        System.out.println("안뇽안뇽");
        System.out.println(pjno);
        return oneprojectService.memberlist(pjno);
    }

    @PostMapping("/view/assign")
    @ResponseBody
    public boolean createprojectlog(@RequestBody ProjectlogDto projectlogDto){
        return oneprojectService.createprojectlog(projectlogDto);
    }

    @GetMapping("/view/list")
    @ResponseBody
    public ArrayList<Integer> findlog(@RequestParam int pjno){
        return oneprojectService.findlog(pjno);
    }

}
