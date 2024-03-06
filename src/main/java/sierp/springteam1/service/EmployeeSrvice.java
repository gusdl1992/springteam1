package sierp.springteam1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sierp.springteam1.model.dao.EmployeeDao;

@Service
public class EmployeeSrvice {
    @Autowired
    private EmployeeDao employeeDao;

    //사원등록 요청
    public boolean eSignup(){
        System.out.println("EmployeeSrvice.eSignup");
        return false;
    }

    // 경력로그 등록 요청
    public boolean cSignup(){
        System.out.println("EmployeeSrvice.cSignup");
        return false;
    }

    // 자격증로그 등록 요청
    public boolean lSignup(){
        System.out.println("EmployeeSrvice.lSignup");
        return false;
    }
}


