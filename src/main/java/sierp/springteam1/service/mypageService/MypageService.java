package sierp.springteam1.service.mypageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import sierp.springteam1.model.dao.mypageDao.MypageDao;
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



    // 마이페이지 내 개인정보 수정 요청
    public boolean doMypageUpdate( String eno){
        System.out.println("MypageService.doMypageUpdate");
        System.out.println("eno = " + eno);
        return mypageDao.doMypageUpdate(eno);
    }

}
