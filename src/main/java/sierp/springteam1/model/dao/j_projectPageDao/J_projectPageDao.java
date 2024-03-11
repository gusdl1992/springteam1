package sierp.springteam1.model.dao.j_projectPageDao;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class J_projectPageDao extends SuperDao {
    //프로젝트 전체(진행전) 리스트 출력
    public List<ProjectDto> printProjectList(){
        System.out.println("J_projectPageDao.printProjectList");
        List<ProjectDto> projectDtos=new ArrayList<>();
        try{
            String sql="select * from project ;";
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
    public ProjectDto getProjectDetail(int pjno){
        System.out.println("J_projectPageDao.getProjectDetail");
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

    //프로젝트 내역 수정
    public boolean updateProjectDetail(ProjectDto projectDto){
        System.out.println("J_projectPageDao.updateProjectDetail");
        System.out.println("projectDto = " + projectDto);
        try{
            String sql="update project set \n" +
                    "\tstart_date=?, \n" +
                    "\tend_date=? ,\n" +
                    "\trank1_count=? ,\n" +
                    "\trank2_count=? ,\n" +
                    "\trank3_count=? ,\n" +
                    "\ttitle=? ,\n" +
                    "\trequest=? ,\n" +
                    "\tnote=? ,\n" +
                    "\tcompannyname=? ,\n" +
                    "\tprice=?\n" +
                    "where pjno=?;";
            ps=conn.prepareStatement(sql);
            ps.setString(1,projectDto.getStart_date());
            ps.setString(2,projectDto.getEnd_date());
            ps.setInt(3,projectDto.getRank1_count());
            ps.setInt(4,projectDto.getRank2_count());
            ps.setInt(5,projectDto.getRank3_count());
            ps.setString(6,projectDto.getTitle());
            ps.setString(7,projectDto.getRequest());
            ps.setString(8,projectDto.getNote());
            ps.setString(9,projectDto.getCompannyname());
            ps.setString(10,projectDto.getPrice());
            ps.setInt(11,projectDto.getPjno());

            int count= ps.executeUpdate();
            System.out.println("count = " + count);
            if(count==1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }

        return false;
    }//m end

    //프로젝트 내역 삭제

    //프로젝트 등록
    public int insertProject(Map<String, String> insertArray){
        System.out.println("J_projectPageDao.insertProject");
        System.out.println("insertArray = " + insertArray);
        try{
            String[] key={"pjno"};
            String sql="insert into project(start_date, end_date, rank1_count, rank2_count, rank3_count, title, request, note, compannyname, state, price) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql, key);
            ps.setString(1,insertArray.get("start_date"));
            ps.setString(2,insertArray.get("end_date"));
            ps.setString(3,insertArray.get("rank1_count"));
            ps.setString(4,insertArray.get("rank2_count"));
            ps.setString(5,insertArray.get("rank3_count"));
            ps.setString(6,insertArray.get("title"));
            ps.setString(7,insertArray.get("request"));
            ps.setString(8,insertArray.get("note"));
            ps.setString(9,insertArray.get("compannyname"));
            ps.setString(10,insertArray.get("state"));
            ps.setString(11,insertArray.get("price"));

            ps.executeUpdate();
            rs=ps.getGeneratedKeys();

            if(rs.next()){
                return rs.getInt(1);
            }//if end
        }//try end
        catch (Exception e ){
            System.out.println("e = " + e);
        }
        return 0;
    }//m end

}//c end
