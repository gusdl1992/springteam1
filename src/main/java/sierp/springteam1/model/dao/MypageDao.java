package sierp.springteam1.model.dao;

import org.springframework.stereotype.Component;
import sierp.springteam1.model.dto.EmployeeDto;

@Component
public class MypageDao extends SuperDao{


    // 사원 정보 요청
    public EmployeeDto doGetLoginInfo(String eno){
        System.out.println("MypageDao.doGetLoginInfo");
        System.out.println("MypageDao eno = " + eno);

        EmployeeDto employeeDto = null;
        try {
            String sql = "select * from employee where eno = ?";
            ps = conn.prepareCall(sql);
            ps.setString(1, eno);
            rs = ps.executeQuery();
            // 한명 이라서 if
            if (rs.next()){
                employeeDto = new EmployeeDto(
                        rs.getInt(1),rs.getString(2),
                        rs.getString(3),null // 비밀번호
                        , rs.getString(5), rs.getString(6),
                        rs.getString(7) , rs.getString(8),
                        rs.getBoolean(9)//성별
                        , rs.getString(10) , rs.getString(11),
                        rs.getInt(12), null
                );
            }
        }catch (Exception e){
            System.out.println("EmployeeDto doGetLoginInfo e = " + e);
        }

        return employeeDto;
    }


    // 사원 번호로 사원 아이디 반환
    public String doGetNameInfo(String eno){
        System.out.println("MypageDao.doGetNameInfo");
        System.out.println("MypageDao.doGetNameInfo eno = " + eno);
        String id = "";
        try {
            String sql = "select id from employee where eno = ?;";
            ps = conn.prepareCall(sql);
            ps.setString(1, eno);
            rs = ps.executeQuery();
            // 한명 이라 if
            if (rs.next()){
                id = rs.getString(1);
            }
        }catch (Exception e){
            System.out.println("MypageDao.doGetNameInfo e = " + e);
        }
        System.out.println("id = " + id);
        return id;
    }
/*
    int eno;
    String eeducation;  // 학력 ( 고졸 , 초대졸 , 대졸 )
    String id;          // 아이디
    String pw;          // 비밀번호
    String ename;       // 이름
    String email;       // 이메일
    String phone;       // 전화번호
    String address;     // 주소
    boolean sex;        // 성별 0 남성 1 여성
    String img;         // 업로드한 이미지 이름
    String edate;       // 입사일 ( 등록일 )
    int pno;            // 파트 ( 업무 부서 )
    MultipartFile mfile; // 파일 업로드


* */

}
