package sierp.springteam1.model.dao.projectcontroller;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.ProjectDto;

@Component
public class OneprojectDao extends SuperDao {

    public ProjectDto oneProject(int pjno){
        ProjectDto projectDto = null;
        try {
            String sql = "select * from project where pjno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pjno);
            rs = ps.executeQuery();
            if(rs.next()){
                projectDto= ProjectDto.builder()
                        .pjno(rs.getInt("pjno"))
                        .start_date(rs.getString("start_date"))
                        .end_date(rs.getString("end_date"))
                        .rank1_count(rs.getInt("rank1_count"))
                        .rank2_count(rs.getInt("rank2_count"))
                        .rank3_count(rs.getInt("rank3_count"))
                        .title(rs.getString("title"))
                        .request(rs.getString("request"))
                        .note(rs.getString("note"))
                        .compannyname(rs.getString("compannyname"))
                        .state(rs.getInt("state"))
                        .price(rs.getString("price"))
                        .build();
                ;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }

        return projectDto;
    }

//    public boolean createprojectlog(){
//
//    }
}
