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
    // 개별인사관리 페이지 요청
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
