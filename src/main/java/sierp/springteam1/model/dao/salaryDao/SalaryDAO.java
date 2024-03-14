package sierp.springteam1.model.dao.salaryDao;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dao.SuperDao;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.model.dto.SalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SalaryDAO extends SuperDao {
    public void insertSalray(List<SalaryDto> result){
        try {
            String sql = "insert into salary(eno,price) values" ;
            for (SalaryDto i : result) {
                     sql += "("+i.getEmployeeDto().getEno()+","+i.getPrice()+"),";
            }
            String sql2 = sql.substring(0, sql.length()-1);
            System.out.println("sql = " + sql2);
            ps = conn.prepareStatement(sql2);
            int count = ps.executeUpdate();
            System.out.println("count = " + count);
        }
        catch (Exception e){
            System.out.println("임금 삽입중 발생한 오류 = " + e);
        }

    }

    public List<SalaryDto> findSalary(List<EmployeeDto> allEmployee , int allwork){
        List<SalaryDto> result = new ArrayList<>();
        try {
            for (EmployeeDto employeeDto : allEmployee) {
                String sql = "select a.eno as eno , a.pprice as pprice , b.근무일자 as 근무일자 from\n" +
                        "(\n" +
                        "select eno,pprice from\n" +
                        "price as z\n" +
                        "cross join \n" +
                        "(\n" +
                        "select a.eno , (sum(coalesce(datediff(b.end_date,b.start_date),0))+datediff(now(),a.edate)) as alltime \n" +
                        "from employee as a left outer join  employeecareer as b on a.eno = b.eno\n" +
                        "where a.eno in(\n" +
                        "      select d.eno\n" +
                        "      from \n" +
                        "      (select b.pjno , end_date, a.eno  from uploadproject b join salesproject d on b.spjno = d.spjno \n" +
                        "      right outer join projectlog a\n" +
                        "       on a.pjno = b.pjno) as c \n" +
                        "      right outer join\n" +
                        "       employee d \n" +
                        "       on c.eno = d.eno\n" +
                        "       group by d.eno\n" +
                        "\t)\t\n" +
                        "\tgroup by a.eno\n" +
                        ")\n" +
                        "as t\n" +
                        "where z.p_start<= t.alltime and t.alltime <= z.p_end ) as a \n" +
                        "inner join \n" +
                        "(select  eno, count(*) as 근무일자   from attendance_log where TIMESTAMPDIFF(HOUR, stat_time, end_time)>=8 group by eno) as b\n" +
                        "on a.eno = b.eno\n" +
                        "where a.eno = "+employeeDto.getEno();
                ps =conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    double price = Math.ceil(1.0*rs.getInt("근무일자")/allwork*rs.getInt("pprice"));
                    System.out.println("price = " + price);
                    result.add(SalaryDto.builder()
                            .employeeDto(employeeDto)
                            .price(price)
                            .build());
                }
            }

        }
        catch (Exception e){
            System.out.println("샐러리다오의 파인드샐러리함수 오류 = " + e);
        }

        return result;
    }
}
