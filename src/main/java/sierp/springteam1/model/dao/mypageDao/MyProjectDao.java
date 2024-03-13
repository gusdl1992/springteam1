package sierp.springteam1.model.dao.mypageDao;


import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.MyProjectDto;
import sierp.springteam1.model.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;

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


    // 즐겨찾기한 프로젝트 전체 출력
    public List<ProjectDto> myProjectLikeView(String eno){
        System.out.println("MyProjectDao.myProjectLikeView");
        System.out.println("MyProjectDao.myProjectLikeView : eno = " + eno);
        List<ProjectDto> list = new ArrayList<>();
        ProjectDto projectDto = null;

        try {
            String sql = "select DISTINCT * from projectlike p inner join project p2 ON p.pjno = p2.pjno where eno = ? and state = 0;";
            ps = conn.prepareStatement(sql);
            ps.setString(1,eno);
            rs = ps.executeQuery();
            while (rs.next()){
                projectDto = ProjectDto.builder()
                        .spjno(rs.getInt(2))
                        .title(rs.getString(9))
                        .compannyname(rs.getString(12))
                        .price(rs.getString(14))
                        .build();
                list.add(projectDto);
            }
            System.out.println("list = " + list);
            return list;
        }catch (Exception e){
            System.out.println("MyProjectDao.myProjectLikeView : e = " + e);
        }

        return null;
    }

    // 내 이전 프로젝트 전체 출력
    public  List<ProjectDto> myProjectPreviousView(String eno){
        System.out.println("MyProjectDao.myProjectPreviousView");
        List<ProjectDto> list = new ArrayList<>();
        ProjectDto projectDto = null;
        try {
            String sql = "select b.state , b.title , b.pjno, b.start_date , b.end_date from projectlog as a inner join project as b on a.pjno = b.pjno where eno = ? and b.state = 2";
            ps = conn.prepareStatement(sql);
            ps.setString(1,eno);
            rs = ps.executeQuery();
            while (rs.next()){
                projectDto = ProjectDto.builder()
                        .spjno(rs.getInt("pjno"))
                        .title(rs.getString("title"))
                        .start_date(rs.getString("start_date"))
                        .end_date(rs.getString("end_date"))
                        .state(rs.getInt("state"))
                        .build();
                list.add(projectDto);
            }
            System.out.println("list = " + list);
            return list;
        }catch (Exception e){
            System.out.println("MyProjectDao.myProjectPreviousView : e = " + e);
        }
        return null;
    }


} // c e
