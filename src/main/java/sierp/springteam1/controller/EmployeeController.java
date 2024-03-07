package sierp.springteam1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.model.dto.EmployeeCareerDto;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.model.dto.EmployeeLicenseDto;
import sierp.springteam1.service.EmployeeSrvice;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeSrvice employeeSrvice;
    //================ 등록 요청
    //사원등록 요청
    @PostMapping("/esignup")
    @ResponseBody
    public boolean eSignup(EmployeeDto employeeDto){
        System.out.println("EmployeeController.eSignup");
        return employeeSrvice.eSignup(employeeDto);
    }

    // 경력로그 등록 요청
    @PostMapping("/csignup")
    @ResponseBody
    public boolean cSignup(EmployeeCareerDto careerDto){
        System.out.println("EmployeeController.cSignup");
        return employeeSrvice.cSignup(careerDto);
    }

    // 자격증로그 등록 요청
    @PostMapping("/lsignup")
    @ResponseBody
    public boolean lSignup(EmployeeLicenseDto licenseDto){
        System.out.println("EmployeeController.lSignup");
        return employeeSrvice.lSignup(licenseDto);
    }

    //================= 페이지 요청
    //사원등록 페이지 요청
    @GetMapping("/signup")
    public String viewSignup(){
        System.out.println("EmployeeController.viewSignup");
        return "/signup";
    }
}
