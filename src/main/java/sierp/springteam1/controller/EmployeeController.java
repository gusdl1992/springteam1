package sierp.springteam1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.model.dao.EmployeeDao;
import sierp.springteam1.model.dto.*;
import sierp.springteam1.service.employeeserive.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private HttpServletRequest request;
    //================ 등록 요청

    // 2. 로그인 처리 요청
    @PostMapping("/login")
    @ResponseBody
    public boolean login(String id, String pw){
        System.out.println("id = " + id);
        //---

        boolean result= employeeDao.login(id,pw);
        if(result){
            request.getSession().setAttribute("login",id);
        }
        //로그인 성공시
        /*
        세션 저장소 : 톰켓서버에 브라우저 마다 메모리 할당
        세션 객체 타입 : Object ( 여러가지 타입들을 저장하려고)
        1. 요청 객체 호출 : HttpServletRequest
        2. 세션 객체 호출 : .getSession()
        3. 세션 데이터 저장 : .setAttribute("세션명" , 데이터)     --  자동형 변환(자->부)
        -. 세션 데이터 호출 : .getAttribute("세션명")             -- 강제형 변환 (부->자) /캐스팅
        -http 세션 초기화 : invalidate()
         */
        return result;
    }

    //=======로그인 여부 확인 요청
    // 2-2 ============= 로그인 여부 확인 요청 / 세션 호출  ================
    @GetMapping("/login/check")
    @ResponseBody
    public String doGetLoginCheck(){
        // * 로그인 여부 확인 = 세션이 있다 없다 확인   // 1-> http 요청 객체 호출 , 2->http세션 객체 호출 -> 3.http 세션 데이터 호출
        // null은 형변환이 불가능하기 때문에 유효성검사.
        String id = null;
        Object sessionObj = request.getSession().getAttribute("login");
        if( sessionObj != null ){     id = (String) sessionObj;    }
        // 만약에 로그인했으면(세션에 데이터가 있으면) 강제형변환을 통해 데이터 호출 아니면 0
        return id;
    } // f end

    //========== 로그아웃
    @GetMapping("/logout")
    @ResponseBody
    public boolean doGetLogout(){
        // 1. 로그인 관련 세션 호출
        //1. 모든 세션 초기화 (모든 세션의 속성이 초기화 -> 로그인세션 외 다른 세션도 고려
        request.getSession().invalidate(); // 현재 요청 보낸 브라우저의 모든 세션 초기화
        //2. 특정 세션속성 초기화 => 동일한 세션속성명에 null 대입한다
        //request.getSession().setAttribute("loginDto", null);

        return true;
    }

    //사원등록 요청
    @PostMapping("/signup")
    @ResponseBody
    public boolean eSignup(EmployeeDto employeeDto){
        System.out.println("EmployeeController.eSignup");
        return employeeService.eSignup(employeeDto);
    }

    // 경력로그 등록 요청
    @PostMapping("/careerPost")
    @ResponseBody
    public boolean careerPost(EmployeeCareerDto careerDto){
        System.out.println("EmployeeController.cSignup");
        System.out.println(careerDto);
        return employeeService.careerPost(careerDto);
    }

    // 자격증로그 등록 요청
    @PostMapping("/licensePost")
    @ResponseBody
    public boolean lSignup(EmployeeLicenseDto licenseDto){
        System.out.println("EmployeeController.lSignup");
        return employeeService.lSignup(licenseDto);
    }

    //================= 페이지 요청
    // 인사관리 페이지 요청
    @GetMapping("/employee")
    public String getemployee(){
        return "/employee/employee";
    }
    // 인사관리 페이지 요청
    @GetMapping("/employee/view.do")
    public String employeeView(){
        return "/employee/employeeView";
    }
    //사원등록 페이지 요청
    @GetMapping("/signup")
    public String viewSignup(){
        System.out.println("EmployeeController.viewSignup");
        return "/employee/signup";
    }

    //=========== 출력

    //부서명 전체 출력
    @GetMapping("/partList")
    @ResponseBody
    public List<PartDto> partList (){
        System.out.println("EmployeeController.partDtoList");
        return employeeService.partList();
    }

    // 자격증 종류 출력
    @GetMapping("/license")
    @ResponseBody
    public List<LicenseDto> licenseList(){
        System.out.println("EmployeeController.licenseList");
        return employeeService.licenseList();
    }

    // 전체 사원 호출
    @GetMapping("/employeeList")
    @ResponseBody
    public List<EmployeeDto> employeeList(){
        System.out.println("EmployeeController.employeeList");
        return employeeService.employeeList();
    }
    // 개별 사원 호출
   /* @GetMapping
    @ResponseBody
    public EmployeeDto*/

    //경력 내역 출력
    @GetMapping("/careerView")
    @ResponseBody
    public List<EmployeeCareerDto> careerList(@RequestParam int eno){
        System.out.println("EmployeeController.careerList");
        return employeeService.careerList(eno);
    }
}
