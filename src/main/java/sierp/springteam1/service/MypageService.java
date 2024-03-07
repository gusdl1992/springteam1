package sierp.springteam1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sierp.springteam1.model.dao.MypageDao;
import sierp.springteam1.model.dto.EmployeeDto;

@Service
public class MypageService {

    @Autowired
    private MypageDao mypageDao;

    // 사원 정보 요청
    public EmployeeDto doGetLoginInfo(String eno){
        System.out.println("MypageService.doGetLoginInfo");
        System.out.println("MypageService eno = " + eno);
        return mypageDao.doGetLoginInfo(eno);
    }

}
