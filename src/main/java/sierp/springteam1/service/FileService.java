package sierp.springteam1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    public String eFileUpload(MultipartFile multipartFile){
        System.out.println("FileService.eFileUpload");
        String eUpload="C:\\Users\\504\\Desktop\\springteam1\\build\\resources\\main\\static\\img\\eimg";
        String uuid= UUID.randomUUID().toString();
        String filename = "employee_"+uuid+"_"+multipartFile.getOriginalFilename().replace("_","-");
        File file= new File(eUpload+filename);
        System.out.println("file = " + file);
        System.out.println("file.exists() = " + file.exists());
        //2.
        try {
            multipartFile.transferTo(file);
        }catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
        return filename;
    }

    public String cFileUpload(MultipartFile multipartFile){
        String eUpload="C:\\Users\\504\\Desktop\\springteam1\\build\\resources\\main\\static\\img\\cimg";
        String uuid= UUID.randomUUID().toString();
        String filename = "career_"+uuid+"_"+multipartFile.getOriginalFilename().replace("_","-");
        File file= new File(eUpload+filename);
        System.out.println("file = " + file);
        System.out.println("file.exists() = " + file.exists());
        //2.
        try {
            multipartFile.transferTo(file);
        }catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
        return filename;
    }
}
