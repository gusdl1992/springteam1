package sierp.springteam1.service.mypageService;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {


    public List<Map<String , Object>> getEvent(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        System.out.println("AttendanceService.getEvent");
        Map<String, Object> event = new HashMap<String, Object>();
        List<Map<String, Object>> eventList = new ArrayList<Map<String, Object>>();
        event.put("start", LocalDate.now());
        event.put("title", formattedTime+"출근");
        System.out.println("formatter = " + formattedTime);
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
