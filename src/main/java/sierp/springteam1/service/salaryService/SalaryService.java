package sierp.springteam1.service.salaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import sierp.springteam1.model.dao.EmployeeDao;
import sierp.springteam1.model.dao.salaryDao.SalaryDAO;
import sierp.springteam1.model.dto.EmployeeDto;
import sierp.springteam1.model.dto.SalaryDto;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SalaryService {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    SalaryDAO salaryDAO;


    public void insertsel(){
        //이번달 얼마 출근했는지
        //총 출근일
        int allwork = premonthworking();
        List<EmployeeDto> allEmployee = employeeDao.findAllEmployee();
        List<SalaryDto> result = salaryDAO.findSalary(allEmployee,allwork);
        for (SalaryDto salaryDto : result) {
            System.out.println("salaryDto.getPrice() = " + salaryDto.getPrice());
        }
        salaryDAO.insertSalray(result);
    }

    public int premonthworking(){ //저번달 총 근무일 계산하기

        LocalDate currentDate = LocalDate.now();
        // 이전 달의 날짜 얻기
        LocalDate lastMonthDate = currentDate.minusMonths(1);

        // 이전 달의 월과 년도 구하기
        String lastMonth = lastMonthDate.getMonthValue()>=10?lastMonthDate.getMonthValue()+"":"0"+lastMonthDate.getMonthValue();
        String lastYear = lastMonthDate.getYear()+"";
        System.out.println(lastYear);
        System.out.println("lastMonth = " + lastMonth);
        List<String> dateNames = new ArrayList<>();
        String sb = new String();
        try {
            StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=To%2Baj7g3UQT9b52hpWu7PDbqmXaz%2FHz3yFr%2Fcz7XmCFgGCkL1CwLO9nMSKP4GVRiq1ykfLpd7Kl0NodiNhzuYg%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(lastYear, "UTF-8")); /*연*/
            urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode(lastMonth, "UTF-8")); /*월*/
            urlBuilder.append("&" + URLEncoder.encode("kst","UTF-8") + "=" + URLEncoder.encode("0120", "UTF-8")); /*한국표준시각*/
            urlBuilder.append("&" + URLEncoder.encode("sunLongitude","UTF-8") + "=" + URLEncoder.encode("285", "UTF-8")); /*태양황경*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /**/
            urlBuilder.append("&" + URLEncoder.encode("totalCount","UTF-8") + "=" + URLEncoder.encode("210114", "UTF-8")); /**/
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            String line;
            while ((line = rd.readLine()) != null) {
                sb+= line;
            }
            rd.close();
            conn.disconnect();
        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            // XML 파싱
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(sb)));

            // 루트 요소
            Element root = doc.getDocumentElement();

            // items 아래의 item 태그들
            NodeList itemList = root.getElementsByTagName("item");

            // 결과를 담을 리스트


            // item 태그들을 순회하면서 특정 키값 추출
            for (int i = 0; i < itemList.getLength(); i++) {
                Node itemNode = itemList.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    // 특정 키값(dateName) 추출하여 리스트에 추가
                    String dateName = itemElement.getElementsByTagName("locdate").item(0).getTextContent();
                    dateNames.add(dateName);
                }
            }

            // 결과 출력
            String[] dateNameArray = dateNames.toArray(new String[0]);
            for (String dateName : dateNames) {
                System.out.println(dateName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 각 날짜를 적절한 형식으로 변경
        List<LocalDate> formattedDates = new ArrayList<>();
        for (String dateString : dateNames) {
            LocalDate date = LocalDate.parse(dateString, inputFormatter);
            formattedDates.add(date);
        }

        for (LocalDate formattedDate : formattedDates) {
            System.out.println("formattedDate = " + formattedDate);
        }



        // 이번 달의 첫 번째 날 구하기
        LocalDate firstDayOfCurrentMonth = currentDate.withDayOfMonth(1);

        // 이전 달의 마지막 날 구하기
        LocalDate lastDayOfPreviousMonth = firstDayOfCurrentMonth.minus(1, ChronoUnit.DAYS);
        LocalDate firstDayOfPreviousMonth = lastDayOfPreviousMonth.withDayOfMonth(1);

        // 이전 달의 평일 수 계산
        int weekdayCount = 0;
        LocalDate date = firstDayOfPreviousMonth;

        while (!date.isAfter(lastDayOfPreviousMonth)) {
            // 주말인지 확인하여 평일인 경우 개수 증가
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY && !formattedDates.contains(date)) {
                System.out.println("date = " + date);
                weekdayCount++;
            }
            // 다음 날로 이동
            date = date.plus(1, ChronoUnit.DAYS);
        }

        // 결과 출력
        System.out.println("저번 달의 평일 수: " + weekdayCount);

        return weekdayCount;
    }
}
