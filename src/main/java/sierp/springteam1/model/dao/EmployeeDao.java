package sierp.springteam1.model.dao;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dto.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDao extends SuperDao{

    //사원등록 요청
    public boolean eSignup(EmployeeDto employeeDto){
        System.out.println("EmployeeDao.eSignup");
        try{
            String sql= "insert into employee(id, pw, ename,email,  phone, address,sex,img,pno,eeducation)" +
                    " values(?,?,?,?,?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,employeeDto.getId());
            ps.setString(2,employeeDto.getPw());
            ps.setString(3,employeeDto.getEname());
            ps.setString(4,employeeDto.getEmail());
            ps.setString(5,employeeDto.getPhone());
            ps.setString(6,employeeDto.getAddress());
            ps.setBoolean(7,employeeDto.isSex());
            ps.setString(8,employeeDto.getImg());
            ps.setInt(9,employeeDto.getPno());
            ps.setString(10,employeeDto.getEeducation());

            System.out.println(employeeDto);
            int count= ps.executeUpdate();
            if(count==1){return true;}
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return  false;
    }

    // 경력로그 등록 요청
    public boolean cSignup(EmployeeCareerDto careerDto){
        System.out.println("EmployeeDao.cSignup");
        try {
            String sql="insert into employeecareer";
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }

    // 자격증로그 등록 요청
    public boolean lSignup(EmployeeLicenseDto licenseDto){
        System.out.println("EmployeeDao.lSignup");
        try {
            String sql="";
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }
    //====================== 호출

    //부서명 전체 출력
    public List<PartDto> partList(){
        System.out.println("EmployeeController.partDtoList");
        List<PartDto>list=new ArrayList<>();
        PartDto partDto=null;
        try {
            String sql="select * from part";
            ps= conn.prepareStatement(sql);
            rs= ps.executeQuery();
            while (rs.next()){
                partDto=new PartDto(
                        rs.getInt("pno"),
                        rs.getString("pname")
                                );
                list.add(partDto);
            }
        }catch (Exception e){
            System.out.println("e");
        }
        return list;
    }

    public List<LicenseDto> licenseList(){
        List<LicenseDto>llist=new ArrayList<>();
        LicenseDto licenseDto=null;
        try {
            String sql="select * from license;";
            ps= conn.prepareStatement(sql);
            rs= ps.executeQuery();
            while (rs.next()){
                licenseDto=new LicenseDto(
                        rs.getInt("lno"),
                        rs.getString("lname"),
                        rs.getInt("lprice")
                );
                llist.add(licenseDto);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return llist;
    }

}
