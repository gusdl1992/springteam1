package sierp.springteam1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.model.dao.mypageDao.MypageDao;

@Controller
public class IndexController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MypageDao mypageDao;

    @GetMapping("/")
    public String indexview(){
        request.getSession().setAttribute("eno" ,1);
        return "index";
    }

    // 로그인 여부 확인
    @GetMapping("employee/login/check")
    @ResponseBody
    public String getLoginCheck(){
        String loginId = null;
        Object sessionObj = request.getSession().getAttribute("eno");
        if(sessionObj != null){ loginId = String.valueOf(sessionObj); }
        // 만약에 로그인했으면(세션에 데이터가 있으면) 강제형변환을 통해 데이터 호출 아니면 0
        return loginId;
    } // getLoginCheck end

    // 로그인 후 사원이름 가져오기
    @GetMapping("employee/login/checkname")
    @ResponseBody
    public String doGetLoginName(String eno){
        System.out.println("IndexController.doGetLoginName");
        return mypageDao.doGetNameInfo(eno); // 세션에 사원번호로 DAO 접근 후 아이디 표시로 전환
    }


} // c e
