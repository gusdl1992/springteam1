package sierp.springteam1.model.dao.salesDao;


import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.ProjectDto2;

@Component
public class SalesDao extends SuperDao{

    public boolean salesPost(ProjectDto2 projectDto2){
        try {
            String sql = "insert into project2(start_date, end_date, rank1_count, rank2_count, rank3_count, title, request, note, cno, state, price) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,projectDto2.getStart_date());
            ps.setString(2,projectDto2.getEnd_date());
            ps.setInt(3,projectDto2.getRank1_count());
            ps.setInt(4,projectDto2.getRank2_count());
            ps.setInt(5,projectDto2.getRank3_count());
            ps.setString(6,projectDto2.getTitle());
            ps.setString(7,projectDto2.getRequest());
            ps.setString(8,projectDto2.getNote());
            ps.setInt(9,projectDto2.getCno());
            ps.setInt(10,projectDto2.getState());
            ps.setString(11,projectDto2.getPrice());
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
}
