package sierp.springteam1.model.dao;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dto.EmployeeCareerDto;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.model.dto.EmployeeLicenseDto;

@Component
public class EmployeeDao extends SuperDao{

    //사원등록 요청
    public boolean eSignup(EmployeeDto employeeDto){
        System.out.println("EmployeeDao.eSignup");
        return false;
    }

    // 경력로그 등록 요청
    public boolean cSignup(EmployeeCareerDto careerDto){
        System.out.println("EmployeeDao.cSignup");
        return false;
    }

    // 자격증로그 등록 요청
    public boolean lSignup(EmployeeLicenseDto licenseDto){
        System.out.println("EmployeeDao.lSignup");
        return false;
    }

}
