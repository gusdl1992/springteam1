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

    // 출석 요청
    public boolean attendanceWrite(String eno){
        System.out.println("AttendanceService.attendanceWrite");
        AttendanceLogDto attendanceLogDto = AttendanceLogDto.builder()
                .jday(nowDay())
                .stat_time(nowTime())
                .jip(getUserIp())
                .eno(Integer.parseInt(eno))
                .build();
        return attendanceDao.attendanceWrite(attendanceLogDto);
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

    public List<Map<String , Object>> getEvent(){
        String now = nowTime();

        System.out.println("AttendanceService.getEvent");
        Map<String, Object> event = new HashMap<String, Object>();
        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
        event.put("start", LocalDate.now());
        event.put("title", now+"출근");
        System.out.println("formatter = " + now);
        event.put("end",LocalDate.now());
        eventList.add(event);
        event = new HashMap<String, Object>();
        event.put("start", LocalDate.now().plusDays(3));
        event.put("title", "test2");
        event.put("end",LocalDate.now().plusDays(4));
        eventList.add(event);
        return eventList;
    }


    // 클라이언트 IP 가져오기
    public String getUserIp() {

        String ip = null;
        try {HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            ip = request.getHeader("X-Forwarded-For");

            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-RealIP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("REMOTE_ADDR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return ip;
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
