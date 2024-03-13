package sierp.springteam1.controller.mypagecontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.service.mypageService.AttendanceService;
import sierp.springteam1.service.mypageService.MypageService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mypage")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private MypageService mypageService;

    @GetMapping("/attendance")
    public String AttendanceView(){
        System.out.println("AttendanceController.AttendanceView");
        return "/mypages/attendance";
    }

    // 출근 체크
    @GetMapping("/attendance/write")
    @ResponseBody
    public boolean attendanceWrite(){
        System.out.println("AttendanceController.attendanceWrite");
        String eno = mypageService.sessionEno();
        System.out.println("AttendanceController : eno = " + eno);
        return attendanceService.attendanceWrite(eno);
    }

    // 출근 체크 값 가져오기
    @GetMapping("/event")
    @ResponseBody
    public List<Map<String , Object>> getEvent(){
        return attendanceService.getEvent();
    }


}
