package sierp.springteam1.service.employeeserive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import sierp.springteam1.model.dao.EmployeeDao;
import sierp.springteam1.model.dao.mypageDao.MypageDao;
import sierp.springteam1.model.dto.*;
import sierp.springteam1.service.mypageService.MypageService;

import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private FileService fileService;
    @Autowired
    private MypageDao mypageDao;

    //사원등록 요청
    public boolean eSignup(EmployeeDto employeeDto){
        System.out.println("EmployeeService.eSignup");
        //증명사진 파일 처리
        String fileName="";
        System.out.println("employeeDto.getMfile() = " + employeeDto.getMfile());
        if(!employeeDto.getMfile().isEmpty()) {
            fileName = fileService.eFileUpload(employeeDto.getMfile());
            if (fileName == null) { // 업로드 성공했으면
                return false;
            }
        }
        //2. DB
        //dto에 업로드 성공한 파일명을 대입한다
        employeeDto.setImg(fileName);

        //*이메일 테스트
        //if(result){emailService.send();}

        //1. 아이디 생성 / 후보 : 1.사원번호+이름 2.사원이메일 앞부분 3.?
        //샘플
        String id= employeeDto.getEmail().split("@")[0];
        //2. 초기 비밀번호 난수 부여
        String newPw="";
        Random random=new Random();
        for(int i=1; i<=6; i++){
            newPw+=random.nextInt(10);
        }
        System.out.println("newPw = " + newPw);

        employeeDto.setId(id);
        employeeDto.setPw(newPw);
        System.out.println(employeeDto);
        return employeeDao.eSignup(employeeDto);
    }

    // 경력로그 등록 요청
    public boolean careerPost(EmployeeCareerDto careerDto){
        System.out.println("EmployeeService.cSignup");
        //경력증명서 파일 처리
        String fileName="";
        System.out.println("careerDto.getCimg() = " + careerDto.getCimg());
        if(!careerDto.getCimg().isEmpty()) {
            fileName = fileService.cFileUpload(careerDto.getCimg());
            if (fileName == null) { // 업로드 성공했으면
                return false;
            }
        }
        //2. DB
        //dto에 업로드 성공한 파일명을 대입한다
        careerDto.setEimg(fileName);
        return employeeDao.careerPost(careerDto);
    }

    // 자격증로그 등록 요청
    public boolean lSignup(EmployeeLicenseDto licenseDto){
        System.out.println("EmployeeService.lSignup");
        return employeeDao.lSignup(licenseDto);
    }
    //=================== 삭제
    //사원 삭제
    public boolean employeeDelete(int eno){
        return employeeDao.employeeDelete(eno);
    }

    //=================== 수정
    // 사원정보 수정
    public boolean employeeUpdate (EmployeeDto employeeDto){
            //1. 기존 첨부파일명 구하고
        String bfile = mypageDao.doGetLoginInfo((employeeDto.getEno()+"")).getImg();
        // - 새로운 첨부파일이 있다. 없다.
        if(!employeeDto.getMfile().isEmpty()){// 수정시 새로운 첨부파일이 있으면
            //새로운  첨부파일을 업로드하고 기존 첨부파일 삭제
            String fileName =fileService.eFileUpload(employeeDto.getMfile());
            if(fileName !=null){
                employeeDto.setImg(fileName); // 새로운첨부파일의 이름 Dto 대입
                // 기존 첨부파일 삭제
                //2. 기존 첨부파일 삭제
                fileService.fileDelete(bfile);
            }else {
                return false; // 업로드 실패
            }
        }else {
            employeeDto.setImg(bfile); // 새로운 첨부파일이 없으면 기존 첨부파일명 그대로 대입
        }
        return employeeDao.employeeUpdate(employeeDto);
    }

    //===================호출
    // 부서 전체 호출
    public List<PartDto> partList (){
        System.out.println("EmployeeService.partDtoList");
        return employeeDao.partList();
    }
    //자격증 선택 호출
    public List<LicenseDto> licenseList(){
        return employeeDao.licenseList();
    }

    // 사원 전체 호출
    public List<EmployeeDto> employeeList( int key,  String keyword){
        System.out.println("EmployeeService.employeeList");
        return employeeDao.employeeList(key, keyword);
    }

    //경력 전체 호출
    public List<EmployeeCareerDto> careerList(int eno){
        System.out.println("EmployeeService.careerList");
        return employeeDao.careerList(eno);
    }
    //자격증 호출
    public List<EmployeeLicenseDto> licenseViewList(int eno){
        System.out.println("eno = " + eno);
        return employeeDao.licenseViewList(eno);
    }
}


