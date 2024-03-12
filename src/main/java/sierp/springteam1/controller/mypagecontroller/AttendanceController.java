package sierp.springteam1.controller.mypagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class AttendanceController {


    @GetMapping("/attendance")
    public String AttendanceView(){
        System.out.println("AttendanceController.AttendanceView");
        return "/mypages/attendance";
    }


}
