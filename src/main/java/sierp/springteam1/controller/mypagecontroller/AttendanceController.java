package sierp.springteam1.controller.mypagecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.service.mypageService.AttendanceService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping("/attendance")
    public String AttendanceView(){
        System.out.println("AttendanceController.AttendanceView");
        return "/mypages/attendance";
    }

    @GetMapping("/event")
    @ResponseBody
    public List<Map<String , Object>> getEvent(){
        return attendanceService.getEvent();
    }


}
