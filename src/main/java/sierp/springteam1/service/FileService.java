package sierp.springteam1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    public String eFileUpload(MultipartFile multipartFile){
        String eUpload="C:\\Users\\504\\Desktop\\springteam1\\build\\resources\\main\\static\\img\\eimg";

        return null;
    }

    public String cFileUpload(MultipartFile multipartFile){
        String eUpload="C:\\Users\\504\\Desktop\\springteam1\\build\\resources\\main\\static\\img\\cimg";
        return null;
    }
}
