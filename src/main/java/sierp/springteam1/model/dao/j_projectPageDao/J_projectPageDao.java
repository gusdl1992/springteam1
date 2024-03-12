package sierp.springteam1.model.dao.j_projectPageDao;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.ProjectDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class J_projectPageDao extends SuperDao {
    //프로젝트 전체 리스트 출력
    public List<ProjectDto> printProjectList(int startRow, String key, String keyword){
        System.out.println("J_projectPageDao.printProjectList");
        List<ProjectDto> projectDtos=new ArrayList<>();
        try{
            String sql="select * from project order by start_date limit ? , ?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,startRow);
            ps.setInt(2,startRow+5);
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
            }//w end
            return projectDtos;
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return null;
    }//m end

    //전체 프로젝드 수 추출
    public int projectCount(){
        System.out.println("J_projectPageDao.projectCount");
        try{
            String sql="select count(*) from project";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return 0;
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
                    "\tstate=? ,\n" +
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
            ps.setInt(10,projectDto.getState());
            ps.setString(11,projectDto.getPrice());
            ps.setInt(12,projectDto.getPjno());

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
    public int insertProject(ProjectDto projectDto){
        System.out.println("J_projectPageDao.insertProject");
        System.out.println("projectDto = " + projectDto);
        try{
            String[] key={"pjno"};
            String sql="insert into project(start_date, end_date, rank1_count, rank2_count, rank3_count, title, request, note, compannyname, state, price) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql, key);
            ps.setString(1,projectDto.getStart_date());
            ps.setString(2,projectDto.getEnd_date());
            ps.setInt(3,projectDto.getRank1_count());
            ps.setInt(4,projectDto.getRank2_count());
            ps.setInt(5,projectDto.getRank3_count());
            ps.setString(6,projectDto.getTitle());
            ps.setString(7,projectDto.getRequest());
            ps.setString(8,projectDto.getNote());
            ps.setString(9,projectDto.getCompannyname());
            ps.setInt(10,projectDto.getState());
            ps.setString(11,projectDto.getPrice());

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

    //프로젝트 삭제
    public boolean deleteProject(int pjno){
        System.out.println("J_projectPageDao.deleteProject");
        System.out.println("pjno = " + pjno);
        try{
            String sql="delete from project where pjno="+pjno;
            ps=conn.prepareStatement(sql);
            int count= ps.executeUpdate();

            if(count==1){
                return true;
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }//m end

}//c end
