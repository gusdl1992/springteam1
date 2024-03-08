package sierp.springteam1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sierp.springteam1.model.dao.EmployeeDao;
import sierp.springteam1.model.dto.*;

import java.util.List;
import java.util.Random;

@Service
public class EmployeeSrvice {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private FileService fileService;


    //사원등록 요청
    public boolean eSignup(EmployeeDto employeeDto){
        System.out.println("EmployeeSrvice.eSignup");
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
    public boolean cSignup(EmployeeCareerDto careerDto){
        System.out.println("EmployeeSrvice.cSignup");
        //경력증명서 파일 처리
        String fileName="";
        System.out.println("employeeDto.getMfile() = " + careerDto.getCimg());
        if(!careerDto.getCimg().isEmpty()) {
            fileName = fileService.cFileUpload(careerDto.getCimg());
            if (fileName == null) { // 업로드 성공했으면
                return false;
            }
        }
        //2. DB
        //dto에 업로드 성공한 파일명을 대입한다
        careerDto.setEimg(fileName);
        return employeeDao.cSignup(careerDto);
    }

    // 자격증로그 등록 요청
    public boolean lSignup(EmployeeLicenseDto licenseDto){
        System.out.println("EmployeeSrvice.lSignup");
        return employeeDao.lSignup(licenseDto);
    }

    //===================호출
    public List<PartDto> partList (){
        System.out.println("EmployeeController.partDtoList");
        return employeeDao.partList();
    }

    public List<LicenseDto> licenseList(){
        return employeeDao.licenseList();
    }
}


