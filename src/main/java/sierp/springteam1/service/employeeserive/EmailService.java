package sierp.springteam1.service.employeeserive;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    //SMTP : 간이 우편 전송 프로토콜 ( 메일 전송)
        //- 자바에서 메일 보내기 (네이버 계정 *2차 비밀번호가 없는)
        //1. SMTP 라이브러리 필요
        //2. Dependencies: Java Mail Sender
            //implementation 'org.springframework.boot:spring-boot-starter-mail'
        //3. 이메일 전송
            //1. 이메일 내용을 구성=> 구성 포멧 : MIME(SMTP사용할 때 사용되는 포멧)
        //4. 보내는 사람 이메일 인증

    @Autowired
    private JavaMailSender javaMailSender;
    public void send(String toEmail, String subject, String content){
        try {
            //*메일 내용물들을 포멧하기 위한 MIME 형식 객체
            MimeMessage message = javaMailSender.createMimeMessage();
            //1. 메일 기본 구성 //MimeMessageHelper(mime 객체, 첨부파일 여부, 인코딩타입); 내용물 구성
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "utf-8");
            //2. 메일 보내는 사람
            mimeMessageHelper.setFrom("@naver.com"); //관리자 이메일
            //3. 메일 받는 사람
            mimeMessageHelper.setTo(toEmail); // 클라이언트(회원) 이메일(매개변수)
            //4. 메일 제목
            mimeMessageHelper.setSubject(subject);//매개변수
            //5. 메일 내용
            mimeMessageHelper.setText(content);//매개변수
            //메일 전송
            javaMailSender.send(message);
        }catch (Exception e){
            System.out.println("e = " + e);
        }


    }

}
