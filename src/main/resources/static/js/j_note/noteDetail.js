console.log("noteDetail-js");
let nno=new URL(location.href).searchParams.get('nno');

printNoteDetail();

//쪽지 정보 출력
function printNoteDetail(){
    let r=getNoteInfo();
    console.log(r);

    document.querySelector(".postid").innerHTML=r.postid;
    document.querySelector(".ndate").innerHTML=r.ndate;
    document.querySelector(".ncontent").innerHTML=r.ncontent;
}//f end

//쪽지 정보 가져오기
function getNoteInfo(){
    let result={};
    console.log("printNoteDetail-f");
    $.ajax({
        url : "/note/getDetail.do",
        method : "get",
        data : {"nno" : nno},
        async : false,
        success : (r)=>{
        result=r;
        console.log(result);
        }//s end
    })//ajax end
    return result;
}//f end

//답장 onclick시 답장하는 input 요소 출력
function printReplyInput(check){
console.log("printReplyInput-f");

    if(check==1){
        document.querySelector(".replyInput").innerHTML=`<input type="text" class="replyArea" placeholder="답장내용" />
                                                    <button type="button" onclick="sendReply()">보내기</button>
                                                    <button type="button" onclick="printReplyInput(0)">취소</button>`;
    }
    else{
        document.querySelector(".replyInput").innerHTML=``;
    }
}//f end

//답장 보내기
function sendReply(){
    let info=getNoteInfo();
    let sendeno=info.posteno;
    let ncontent="Re)"+document.querySelector(".replyArea").value;
    $.ajax({
        url : "/note/post.do",
        method : "Post",
        data : {"sendeno" : sendeno,
                "ncontent" : ncontent,
                "reply" : nno},
        success : (r)=>{
            console.log(r);
            if(r){
                alert("답장 전송 성공");
                location.href="/note/getPost";
            }
            else{
                alert("답장 전송 실패");
            }
        }//s end
    })//ajax end
}//f end