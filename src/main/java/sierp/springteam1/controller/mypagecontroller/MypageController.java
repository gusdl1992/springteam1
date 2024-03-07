package sierp.springteam1.controller.mypagecontroller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.service.mypageService.MypageService;

@Controller
public class MypageController {

    @Autowired
    private MypageService mypageService;
    @Autowired
    private HttpServletRequest request;

    // 마이페이지 페이지 요청
    @GetMapping("/mypage")
    public String mypageView(){
        System.out.println("MypageController.mypageView");
//        request.getSession().setAttribute("eno" ,1);
        return "mypages/mypage";
    }

    // 사원번호로 사원 정보 가져오기.
    @GetMapping("/mypage/info")
    @ResponseBody
    public EmployeeDto doGetLoginInfo(){
        System.out.println("MypageController.doGetLoginInfo");
        String eno = null;
        Object sessionObj = request.getSession().getAttribute("eno");
        if(sessionObj != null){ eno = String.valueOf(sessionObj); }
        // 만약에 로그인했으면(세션에 데이터가 있으면) 강제형변환을 통해 데이터 호출 아니면 0
        EmployeeDto employeeDto = mypageService.doGetLoginInfo(eno);
        System.out.println("employeeDto = " + employeeDto);
        return employeeDto;
    }



    // 마이페이지 수정 페이지 요청
    @GetMapping("/mypage/updateView")
    public String doGetUpdateView(){
        System.out.println("MypageController.doGetUpdateView");
        return "mypages/mypageupdate";
    }

    // 마이페이지 내 개인정보 수정 요청
    @PutMapping("/mypage/update.do")
    @ResponseBody
    public boolean doMypageUpdate(@RequestParam String eno){
        System.out.println("MypageController.doMypageUpdate");
        System.out.println("eno = " + eno);
        return mypageService.doMypageUpdate(eno);
    }




}
/*
    - 내정보 보기 ( 사원번호 , 아이디 , 이름 , 이메일 , 전화번호 , 주소 , 성별 , 자기이미지 , 입사일 , 파트 ,  )
    GET 방식


*/
