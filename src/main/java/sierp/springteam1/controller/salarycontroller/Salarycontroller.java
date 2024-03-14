package sierp.springteam1.controller.salarycontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.service.salaryService.SalaryService;

@Controller
public class Salarycontroller {
    @Autowired
    SalaryService salaryService;
//
//    @Scheduled(cron = "0 0 0 1 * *")
    @GetMapping("/test")
    @ResponseBody
    public void insertsal(){
        salaryService.insertsel();
    }


}
