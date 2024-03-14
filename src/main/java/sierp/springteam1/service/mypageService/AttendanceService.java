package sierp.springteam1.service.mypageService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sierp.springteam1.model.dao.mypageDao.AttendanceDao;
import sierp.springteam1.model.dto.AttendanceLogDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceDao attendanceDao;

    // 출근 요청
    public boolean attendanceWrite(String eno , String ip){
        System.out.println("AttendanceService.attendanceWrite");
        // 이미 출근을 찍었는지 체크 ( 찍으면 true , 아니면 false )
        boolean result = attendanceWriteCheck(eno);
        if ( !(result) ){
            AttendanceLogDto attendanceLogDto = AttendanceLogDto.builder()
                    .jday(nowDay())
                    .stat_time(nowTime())
                    .jip(ip)
                    .eno(Integer.parseInt(eno))
                    .build();
            return attendanceDao.attendanceWrite(attendanceLogDto);
        }

        return false; // 이미 출근을 찍었으면 false
    }

    // 퇴근 요청
    public boolean attendanceLeaveWork(String eno){
        System.out.println("AttendanceService.attendanceLeaveWork");
        boolean result = attendanceWriteCheck(eno);
        if (result){
            String toDay = nowDay();
            String time = nowTime();
            return attendanceDao.attendanceLeaveWork(eno , toDay , time);
        }
        return result; // false
    }




    public String nowTime(){ // 현재 시간 반환
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        return formattedTime;
    }

    public String nowDay(){ // 현재 날짜 반환
        LocalDate now = LocalDate.now();
        return String.valueOf(now);
    }

    // 출퇴근 출력 1개
    public List<AttendanceLogDto> getEvent(String eno){
        System.out.println("AttendanceService.getEvent");
        String toDay = nowDay();
        return attendanceDao.getEvent(eno , toDay);
    }


    // 오늘 날자로 출근을 찍었는지 검사
    public boolean attendanceWriteCheck(String eno){
        System.out.println("AttendanceService.attendanceWriteCheck");
        String toDay = nowDay();
        System.out.println("toDay = " + toDay);
        System.out.println("eno = " + eno);

        return attendanceDao.attendanceWriteCheck(eno ,toDay);
    }
}
