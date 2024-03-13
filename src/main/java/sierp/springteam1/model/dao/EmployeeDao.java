package sierp.springteam1.model.dao;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dto.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDao extends SuperDao{
    //로그인 요청
    public int login(String id, String pw){
        try {
            String sql="select eno from employee where id=? and pw=?";
            ps= conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,pw);
            rs= ps.executeQuery();
            if(rs.next()){
                int eno=rs.getInt("eno");
                return eno;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return -1;
    }

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
    public boolean careerPost(EmployeeCareerDto careerDto){
        System.out.println("EmployeeDao.cSignup");
        try {
            String sql="insert into employeecareer( companyname, note, eimg ,start_date,  end_date, eno)" +
                    "values(?,?,?,?,?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,careerDto.getCompanyname());
            ps.setString(2,careerDto.getNote());
            ps.setString(3,careerDto.getEimg());
            ps.setString(4,careerDto.getStart_date());
            ps.setString(5,careerDto.getEnd_date());
            ps.setInt(6,careerDto.getEno());
            System.out.println(careerDto);
            int count= ps.executeUpdate();
            if(count==1){return true;}
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
    //================== 삭제
    //사원 삭제
    public boolean employeeDelete(int eno){
        try {
            String sql="delete from employee where eno ="+eno;
            ps= conn.prepareStatement(sql);
            int count=ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    //====================== 호출
    //부서명 전체 출력
    public List<PartDto> partList(){
        System.out.println("EmployeeDao.partDtoList");
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
    // 자격증 전체 호출
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
    // 전체 사원 출력
    public List<EmployeeDto> employeeList(){
        System.out.println("EmployeeDao.employeeList");
        List<EmployeeDto>list=new ArrayList<>();
        EmployeeDto employeeDto=null;
        try {
            String spl=" select * from employee em , part p where em.pno = p.pno;";
            ps= conn.prepareStatement(spl);
            rs=ps.executeQuery();
            while (rs.next()){
                employeeDto=EmployeeDto.builder()
                        .eno(rs.getInt("eno"))
                        .ename(rs.getString("ename"))
                        .phone(rs.getString("phone"))
                        .edate(rs.getString("edate"))
                        .pname(rs.getString("pname"))
                        .build()
                        /*rs.getInt("eno"),
                    rs.getString("ename"),
                    rs.getString("phone"),
                    rs.getString("edate"),
                        rs.getInt("pno")*/
                ;
                list.add(employeeDto);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }
    //경력 내역 출력
    public List<EmployeeCareerDto> careerList(int eno){
        System.out.println("EmployeeDao.careerList");
        List<EmployeeCareerDto>clist=new ArrayList<>();
        EmployeeCareerDto careerDto;
        try {
            String sql="select * from employeecareer where eno=?";
            ps= conn.prepareStatement(sql);
            ps.setInt(1,eno);
            rs= ps.executeQuery();
            while (rs.next()){
                careerDto=EmployeeCareerDto.builder()
                        .companyname(rs.getString("companyname"))
                        .start_date(rs.getString("start_date"))
                        .end_date(rs.getString("end_date"))
                        .note(rs.getString("note"))
                        .eimg(rs.getString("eimg"))
                        .build();
                clist.add(careerDto);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        System.out.println(clist);
        return clist;
    }
    /*
    create table employee( #사원테이블
   eno int auto_increment,
    eeducation varchar(10),
    id varchar(30) not null unique,
    pw varchar(15) not null,
    ename varchar(20) not null,
    email varchar(30) not null unique,
    phone varchar(15) not null unique,
    address varchar(15) not null,
    sex bool not null,
    img varchar(255) default 'default.jpg',
    edate datetime default now(),
    pno int not null,
    constraint e_pk primary key(eno),
    foreign key(pno) references part(pno)
);

    */

}
