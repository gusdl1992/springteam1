package sierp.springteam1.controller.projectcontroller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.service.projectservice.OneprojectService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @PostMapping("/view/assign")
    @ResponseBody
    public boolean createprojectlog(HttpServletRequest request , int pjno ){
        String[] list = request.getParameterValues("list1");

        List<Integer> list1 = Arrays.stream(list).map(str -> Integer.valueOf(str)).collect(Collectors.toList()); //배열의 타입이 문자열이므로 형변환을 위해 map 돌려줌
        list = request.getParameterValues("list2");
        List<Integer> list2 = Arrays.stream(list).map(str -> Integer.valueOf(str)).collect(Collectors.toList());
        list = request.getParameterValues("list3");
        List<Integer> list3 = Arrays.stream(list).map(str -> Integer.valueOf(str)).collect(Collectors.toList());
        System.out.println("프로젝트로그에 올라갈 초급인원");
        for(int i : list1){
            System.out.print(i+" ");
        }
        System.out.println("\n프로젝트로그에 올라갈 중급인원");
        for(int i : list2){
            System.out.print(i+" ");
        }
        System.out.println("\n프로젝트로그에 올라갈 고급인원 :");
        for(int i : list3){
            System.out.print(i+" ");
        }
        System.out.println("\npjno = " +pjno+ ", list1 = " + list );
        return true;
    }
}
