package sierp.springteam1.controller.j_projectcontroller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sierp.springteam1.model.dto.NoteDto;
import sierp.springteam1.model.dto.ProjectPageDto;
import sierp.springteam1.service.j_projectPage.J_noteService;

import java.util.List;

@Controller
@RequestMapping("/note")
public class J_NoteController {
    @Autowired
    J_noteService j_noteService;
    @Autowired
    private HttpServletRequest request;

    //받은쪽지 페이지 출력
    @GetMapping("/receive")
    public String getReceiveNote(){
        System.out.println("J_NoteController.getReceiveNote");
        return "/받은쪽지 mustache";
    }

    //받은쪽지 가져오기
    @GetMapping("/receive.do")
    @ResponseBody
    public ProjectPageDto doGetReceiveNote(int page, int pageBoardSize){
        System.out.println("J_NoteController.doGetReceiveNote");
        //로그인한 사람의 사원번호 suvlet에서 가져오기
        //int eno=1;
        int eno=(int)request.getSession().getAttribute("eno");
        System.out.println(eno);

        return j_noteService.doGetReceiveNote(eno, page, pageBoardSize);
    }//m end

    //쪽지 보내기
    @PostMapping("/post.do")
    @ResponseBody
    public boolean doPostNote(NoteDto noteDto){
        System.out.println("J_NoteController.doPostNote");
        System.out.println("noteDto = " + noteDto);
        //로그인한 사람의 사원번호 suvlet에서 가져오기
        //int eno=3;
        int eno=(int)request.getSession().getAttribute("eno");
        System.out.println(eno);
        noteDto.setPosteno(eno);

        return j_noteService.doPostNote(noteDto);
    }//m end

    //쪽지 보낼때 > 받는 사람의 사원번호 가져오기
    @GetMapping("/geteno.do")
    @ResponseBody
    public int getEnoToId(String sendId){
        System.out.println("J_NoteController.getEnoToId");
        System.out.println("sendId = " + sendId);

        return j_noteService.getEnoToId(sendId);
    }//m end

    //보낸쪽지 가져오기 페이지 출력
    @GetMapping("/getPost")
    public String getPostNote(){
        System.out.println("J_NoteController.getPostNote");

        return "/보낸쪽지 페이지 mustache";
    }//me nd

    //보낸쪽지 가져오기
    @GetMapping("/getPost.do")
    @ResponseBody
        public ProjectPageDto doGetPostNote(int page, int pageBoardSize){
        System.out.println("J_NoteController.doGetPostNote");
        //로그인한 사람의 사원번호 suvlet에서 가져오기
        //int eno=1;
        int eno=(int)request.getSession().getAttribute("eno");
        System.out.println(eno);

        return j_noteService.doGetPostNote(eno, page, pageBoardSize);
    }//m end

    //쪽지 상세보기 페이지 요청
    @GetMapping("/getDetail")
    public String getNoteDetail(){
        System.out.println("J_NoteController.getNoteDetail");
        return "/쪽지 상세보기 mustache";
    }//m end

    //쪽지 상세정보 요청
    @GetMapping("/getDetail.do")
    @ResponseBody
    public NoteDto doGetNoteDetail(int nno){
        System.out.println("J_NoteController.doGetNoteDetail");
        System.out.println("nno = " + nno);

        return j_noteService.doGetNoteDetail(nno);
    }//m end

}//c end