package sierp.springteam1.service.salesService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sierp.springteam1.model.dao.salesDao.SalesDao;
import sierp.springteam1.model.dto.ProjectDto;
import sierp.springteam1.model.dto.ProjectDto2;
import sierp.springteam1.model.dto.ProjectPageDto;

import java.util.List;

@Service
public class SalesService {


    @Autowired
    SalesDao salesDao;


    public boolean salesPost(int spjno){
        System.out.println("SalesService.salesPost");
        return salesDao.salesPost( spjno);
    }

    public ProjectPageDto saleslist(int page, int pageBoardSize,
                                           int sortKey,
                                           String key, String keyword,
                                           int startPrice, int endPrice){

        ProjectPageDto projectPageDto=new ProjectPageDto();
        //start row
        int startRow=(page-1)*pageBoardSize;
        //end row
        int endRow=startRow+pageBoardSize;

        System.out.println("startRow = " + startRow);
        projectPageDto.setPage(page);

        //총 페이지 수
        int totalRecode=salesDao.projectCount();
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
        projectPageDto.setList(salesDao.saleslist(startRow, pageBoardSize, sortKey, key, keyword, startPrice, endPrice));

        return projectPageDto;
    }//m end

    public ProjectDto oneProject(int spjno){
        System.out.println("OneprojectController.oneProject");
        return salesDao.oneProject(spjno);
    }
    public int findpjno(int spjno){return salesDao.findpjno(spjno);}

    public boolean salesdel(int pjno){
        return salesDao.salesdel(pjno);

    }
}
