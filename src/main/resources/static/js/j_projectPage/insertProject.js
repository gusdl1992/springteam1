//프로젝트 등록
function insertProject(){
console.log(document.querySelector(".update_start_date").value);
    $.ajax({
        url : "/projectPage/insert.do",
        method : "Post",
        data : {"start_date" : document.querySelector(".update_start_date").value,
            "end_date" : document.querySelector(".update_end_date").value,
            "rank1_count" : document.querySelector(".update_rank1_count").value,
            "rank2_count" : document.querySelector(".update_rank2_count").value,
            "rank3_count" : document.querySelector(".update_rank3_count").value,
            "title" : document.querySelector(".update_title").value,
            "request" : document.querySelector(".update_request").value,
            "note" : document.querySelector(".update_note").value,
            "compannyname" : document.querySelector(".update_compannyname").value,
            "state" : document.querySelector(".update_state").value,
            "price" : document.querySelector(".update_price").value,
            },
        success : (r)=>{
            console.log(r);
            if(r){
                alert("등록 성공");
                location.href=`/projectPage/detail?pjno=${r}`;
            }
            else{
                alert("등록 실패");
            }
        }//success end
    })//ajax end
}//f end

//프로젝트 등록 취소
function backList(){

}