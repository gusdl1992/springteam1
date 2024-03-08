//프로젝트 등록
function insertProject(){
    console.log("insertProject()");
    let insertProjectData=document.querySelector(".insertProjectData");
    let insertArray=new FormData(insertProjectData);
    console.log(insertArray);
    $.ajax({
        url : "/projectPage/insert.do",
        method : "Post",
        data : insertArray,
        contentType : false,
        processData : false,
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