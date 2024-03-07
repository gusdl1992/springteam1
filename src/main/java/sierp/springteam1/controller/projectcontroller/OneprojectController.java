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

    @GetMapping("/view")
    public String oneProjectview(int pjno){
        return "project/oneproject";
    }
    @GetMapping("/view/rec")
    public String oneProjectd(int pjno){
        return "project/recproject";
    }

    @GetMapping("/view.do")
    @ResponseBody
    public ProjectDto oneProject(int pjno){
        System.out.println("OneprojectController.oneProject");
        System.out.println(pjno);
        return oneprojectService.oneProject(pjno);
    }

    @GetMapping("/view/rec.do")
    @ResponseBody
    public ArrayList<EmployeeDto>[] memberlist(@RequestParam int pjno){
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
