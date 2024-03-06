package sierp.springteam1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sierp.springteam1.model.dao.EmployeeDao;
import sierp.springteam1.model.dto.EmployeeCareerDto;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.model.dto.EmployeeLicenseDto;

@Service
public class EmployeeSrvice {
    @Autowired
    private EmployeeDao employeeDao;

    //사원등록 요청
    public boolean eSignup(EmployeeDto employeeDto){
        System.out.println("EmployeeSrvice.eSignup");
        //1. 아이디 생성 / 후보 : 사원번호+이름
        //2. 초기 비밀번호 난수 부여
        return employeeDao.eSignup(employeeDto);
    }

    // 경력로그 등록 요청
    public boolean cSignup(EmployeeCareerDto careerDto){
        System.out.println("EmployeeSrvice.cSignup");
        return employeeDao.cSignup(careerDto);
    }

    // 자격증로그 등록 요청
    public boolean lSignup(EmployeeLicenseDto licenseDto){
        System.out.println("EmployeeSrvice.lSignup");
        return employeeDao.lSignup(licenseDto);
    }
}


