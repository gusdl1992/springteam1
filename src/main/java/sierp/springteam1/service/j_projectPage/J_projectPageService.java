package sierp.springteam1.service.j_projectPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sierp.springteam1.model.dao.j_projectPageDao.J_projectPageDao;
import sierp.springteam1.model.dto.ProjectDto;

import java.util.List;

@Service
public class J_projectPageService {
    @Autowired
    J_projectPageDao j_projectPageDao;

    //프로젝트 전체 리스트 출력
    public List<ProjectDto> printProjectList(){
        System.out.println("J_projectPageService.printProjectList");

        return j_projectPageDao.printProjectList();
    }//m end

    //프로젝트 세부 리스트 출력
    public ProjectDto getProjectDetail(int pjno){
        System.out.println("J_ProjectPageController.printProjectDetail");

        return j_projectPageDao.getProjectDetail(pjno);
    }//m end

    //프로젝트 내역 수정
    public boolean updateProjectDetail(ProjectDto projectDto){
        System.out.println("J_projectPageService.updateProjectDetail");
        System.out.println("projectDto = " + projectDto);

        return j_projectPageDao.updateProjectDetail(projectDto);
    }//m end

    //프로젝트 내역 삭제
}//c end
