package sierp.springteam1.model.dao.projectcontroller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.model.dto.ProjectlogDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public ArrayList<EmployeeDto>[] memberlist(String start_date){
        ArrayList<EmployeeDto>[] result = new ArrayList[3];
        for (int i = 0; i < result.length; i++) {
            result[i] = new ArrayList<>();
        }
        try {
           String sql = "select a.eno, (sum(coalesce(datediff(b.end_date,b.start_date),0))+datediff(now(),a.edate)) as alltime\n" +
                   "from employee as a left outer join  employeecareer as b on a.eno = b.eno\n" +
                   "where a.eno in(\n" +
                   "\t\tselect d.eno\n" +
                   "\t\tfrom \n" +
                   "\t\t(select b.pjno , end_date, a.eno  from project b \n" +
                   "\t\tright outer join projectlog a\n" +
                   "\t\t on a.pjno = b.pjno) as c \n" +
                   "\t\tright outer join\n" +
                   "\t\t employee d \n" +
                   "\t\t on c.eno = d.eno\n" +
                   "\t\t group by d.eno\n" +
                   "\t\t having ( coalesce(datediff(?,max(end_date)),1) > 0)\n" +
                   ")\n" +
                   "group by a.eno";

           ps=conn.prepareStatement(sql);
           ps.setString(1,start_date);
           rs= ps.executeQuery();
           while (rs.next()){
               EmployeeDto employeeDto = EmployeeDto.builder()
                       .eno(rs.getInt("eno"))
                       .build();

               int allday = rs.getInt("alltime");

               System.out.println(allday);

               if(allday < 1459 ){
                   result[0].add(employeeDto);
               }
               else if (allday > 3650) {
                    result[2].add(employeeDto);
               }
               else {
                   result[1].add(employeeDto);
               }

           }
           return result;
        }
        catch (Exception e){

        }

        return result;
    }
    public boolean createprojectlog(ProjectlogDto projectlogDto){
        try {
            for(int i : projectlogDto.getEnos()){
                String sql = "insert into projectlog(eno, pjno) values(?,?);";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,i);
                ps.setInt(2,projectlogDto.getPjno());
                int count = ps.executeUpdate();
                if(count != 1){
                    return false;
                }
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
            return false;
        }
        return true;
    }

    public ArrayList<Integer> findlog( int pjno){
        ArrayList<Integer> result = new ArrayList<>();
        try {
            String sql = "select eno from projectlog where pjno = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,pjno);
            rs = ps.executeQuery();
            while (rs.next()){
                result.add(rs.getInt("eno"));
            }
        }
        catch (Exception e){
            System.out.println("e = " + e);
        }
        return result;
    }
//    public boolean createprojectlog(){
//
//    }
}
