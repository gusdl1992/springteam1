package sierp.springteam1.model.dao.j_projectPageDao;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class J_projectPageDao extends SuperDao {
    //프로젝트 전체 리스트 출력
    public List<ProjectDto> printProjectList(){
        System.out.println("J_projectPageDao.printProjectList");
        List<ProjectDto> projectDtos=new ArrayList<>();
        try{
            String sql="select * from project;";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                ProjectDto projectDto=ProjectDto.builder()
                        .pjno(rs.getInt("pjno"))
                        .start_date(rs.getString("start_date"))
                        .end_date(rs.getString("end_date"))
                        .rank1_count(rs.getInt("rank1_count"))
                        .rank2_count(rs.getInt("rank2_count"))
                        .rank3_count(rs.getInt("rank3_count"))
                        .title(rs.getString("title"))
                        .compannyname(rs.getString("compannyname"))
                        .state(rs.getInt("state"))
                        .price(rs.getString("price"))
                        .build();

                projectDtos.add(projectDto);
            }
            return projectDtos;
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }//m end

    //프로젝트 세부 리스트 출력
    public ProjectDto printProjectDetail(int pjno){
        System.out.println("J_ProjectPageController.printProjectDetail");
        try{
            String sql="select * from project where pjno=?;";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, pjno);
            rs=ps.executeQuery();
            if(rs.next()){
                ProjectDto projectDto=new ProjectDto( rs.getInt("pjno")
                        ,rs.getString("start_date")
                        ,rs.getString("end_date")
                        ,rs.getInt("rank1_count")
                        ,rs.getInt("rank2_count")
                        ,rs.getInt("rank3_count")
                        ,rs.getString("title")
                        ,rs.getString("request")
                        ,rs.getString("note")
                        ,rs.getString("compannyname")
                        ,rs.getInt("state")
                        ,rs.getString("price")
                        );
                return projectDto;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }//m end

}
