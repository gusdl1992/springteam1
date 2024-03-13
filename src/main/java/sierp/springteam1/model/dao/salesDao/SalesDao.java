package sierp.springteam1.model.dao.salesDao;


import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.model.dto.ProjectDto2;

import java.util.ArrayList;
import java.util.List;

@Component
public class SalesDao extends SuperDao{

    public boolean salesPost(int spjno){
        try {
            String sql = "insert into uploadproject(spjno) values (?)";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,spjno);
            int count = ps.executeUpdate();
            System.out.println("count = " + count);
            if(count == 1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    public List<ProjectDto2> saleslist(){
        System.out.println("SaleProjectController.salesPost");
        List<ProjectDto2> result = new ArrayList<>();
        try {
            String sql = "select * from project2";
            ps = conn.prepareStatement(sql);
            rs= ps.executeQuery();;
            while (rs.next()){
                result.add(ProjectDto2.builder()
                        .pjno(rs.getInt("pjno"))
                        .start_date(rs.getString("start_date"))
                        .end_date(rs.getString("end_date"))
                        .rank1_count(rs.getInt("rank1_count"))
                        .rank2_count(rs.getInt("rank2_count"))
                        .rank3_count(rs.getInt("rank3_count"))
                        .title(rs.getString("title"))
                        .request(rs.getString("request"))
                        .note(rs.getString("note"))
                        .cno(rs.getInt("cno"))
                        .state(rs.getInt("state"))
                        .price(rs.getString("price"))
                        .build());
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return result;
    }
}
