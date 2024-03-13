package sierp.springteam1.model.dao.mypageDao;


import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;

@Component
public class AttendanceDao extends SuperDao {

    // 출석 체크
    public boolean attendanceWrite(){
        System.out.println("AttendanceDao.attendanceWrite");
        return false;
    }


}
