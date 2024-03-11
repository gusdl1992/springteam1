package sierp.springteam1.model.dao.mypageDao;


import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.MyProjectDto;

@Component
public class MyProjectDao extends SuperDao {


    // 진행 중인 프로젝트 전체 출력
    public MyProjectDto myProjectList(String eno){
        System.out.println("MyProjectDao.myProjectList");
        MyProjectDto myProjectDto = null;
        try {
            String sql = "select * from project p inner join projectlog l on p.pjno = l.pjno where eno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,eno);
            rs = ps.executeQuery();
            if (rs.next()){
                myProjectDto = new MyProjectDto(
                rs.getInt("pjno") ,
                rs.getString("start_date"),
                rs.getString("end_date"),
                rs.getString("title") ,
                rs.getString("compannyname"),
                rs.getInt("eno") ,
                null,   // 회원 아이디  서비스 에서 mypageDao 에 연결 후 가져올것.
                rs.getInt("state")
            );
        }
            System.out.println("myProjectDto = " + myProjectDto);
            return myProjectDto;
        }catch (Exception e){
            System.out.println("MyProjectDao.myProjectList : e = " + e);
        }
        return null;
    }


}
