package sierp.springteam1.service.mypageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    // 출석 체크
    public boolean attendanceWrite(String eno){
        System.out.println("AttendanceService.attendanceWrite");
        AttendanceLogDto attendanceLogDto = new AttendanceLogDto();


        
        return attendanceDao.attendanceWrite();
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

}
