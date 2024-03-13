package sierp.springteam1.service.j_projectPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import sierp.springteam1.model.dao.j_projectPageDao.J_projectPageDao;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.model.dto.ProjectDto3;
import sierp.springteam1.model.dto.ProjectPageDto;

import java.util.List;
import java.util.Map;

@Service
public class J_projectPageService {
    @Autowired
    J_projectPageDao j_projectPageDao;

    //프로젝트 전체 리스트 출력
    public ProjectPageDto printProjectList(int page, int pageBoardSize,
                                           int sortKey,
                                           String key, String keyword,
                                           int startPrice, int endPrice){
        System.out.println("J_projectPageService.printProjectList");

        ProjectPageDto projectPageDto=new ProjectPageDto();
        //start row
        int startRow=(page-1)*pageBoardSize;
        //end row
        int endRow=startRow+pageBoardSize;

        System.out.println("startRow = " + startRow);
        projectPageDto.setPage(page);

        //총 페이지 수
        int totalRecode=j_projectPageDao.projectCount();
        int totalPage=totalRecode%pageBoardSize>0 ? totalRecode/pageBoardSize+1 : totalRecode/pageBoardSize;
        projectPageDto.setTotalPage(totalPage);

        //제한할 페이지수
        int pageLimit=5;

        //시작페이지
        int startPage=((page-1)/pageLimit)*pageLimit+1;
        System.out.println("startPage = " + startPage);
        projectPageDto.setStartPage(startPage);

        //마지막 페이지
        int endPage=((page-1)/pageLimit)*pageLimit+pageLimit;
        endPage=endPage>totalPage ? totalPage : endPage;
        System.out.println("endPage = " + endPage);
        projectPageDto.setEndPage(endPage);

        //한페이지당 List
        projectPageDto.setList(j_projectPageDao.printProjectList(startRow, pageBoardSize, sortKey, key, keyword, startPrice, endPrice));

        return projectPageDto;
    }//m end

    //프로젝트 세부 리스트 출력
    public ProjectDto getProjectDetail(int pjno){
        System.out.println("J_projectPageService.getProjectDetail");

        return j_projectPageDao.getProjectDetail(pjno);
    }//m end

    //프로젝트 내역 수정
    public boolean updateProjectDetail(ProjectDto projectDto){
        System.out.println("J_projectPageService.updateProjectDetail");
        System.out.println("projectDto = " + projectDto);

        return j_projectPageDao.updateProjectDetail(projectDto);
    }//m end

    //프로젝트 내역 삭제

    //프로젝트 등록
    public int insertProject(ProjectDto projectDto){
        System.out.println("J_projectPageService.insertProject");
        System.out.println("projectDto = " + projectDto);

        return j_projectPageDao.insertProject(projectDto);
    }//m end

    //프로젝트 삭제
    public boolean deleteProject(int pjno){
        System.out.println("J_projectPageService.deleteProject");
        System.out.println("pjno = " + pjno);

        return j_projectPageDao.deleteProject(pjno);
    }

    //평가 가능한 프로젝트 리스트 출력
    public List<ProjectDto3> doPrintPerform(){
        System.out.println("J_projectPageService.doPrintPerform");

        return j_projectPageDao.doPrintPerform();
    }//m end
}//c end
