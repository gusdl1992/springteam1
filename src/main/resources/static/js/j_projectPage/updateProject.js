let pjno=new URL(location.href).searchParams.get('pjno');
inputData();

//수정페이지 input에 기존데이터 입력
function inputData(){
    console.log("updateDetail()");

    $.ajax({
        url : "/projectPage/detail.do",
        method : "Get",
        data : {"pjno" : pjno},
        success : (r) => {
            console.log(r);
            document.querySelector(".update_pjno").value=r.pjno;
            document.querySelector(".update_start_date").value=r.start_date;
            document.querySelector(".update_end_date").value=r.end_date;
            document.querySelector(".update_rank1_count").value=r.rank1_count;
            document.querySelector(".update_rank2_count").value=r.rank2_count;
            document.querySelector(".update_rank3_count").value=r.rank3_count;
            document.querySelector(".update_title").value=r.title;
            document.querySelector(".update_request").value=r.request;
            document.querySelector(".update_note").value=r.note;
            document.querySelector(".update_compannyname").value=r.compannyname;
            document.querySelector(".update_state").value=r.state;
            document.querySelector(".update_price").value=r.price;
        }//success end
    })//ajax end
}//f end

//확인 클릭 시 프로젝트 정보 수정
function updateDetail(){
    console.log("js-updateDetail()");
    let projectData=document.querySelector(".projectData");
    let projectArray=new FormData(projectData);
    $.ajax({
         url : "/projectPage/update.do",
         method : "Put",
         data :projectArray,
        contentType : false,
        processData : false,
         success : (r) => {
            console.log("updateDetail-r="+r);
            if(r){
                alert("수정 성공");
                location.href=`/projectPage/detail?pjno=${pjno}`;
            }
            else{
                alert("수정 실패");
            }
         }//success end
    })//ajax end
}//f end

//취소 클릭 시 세부리스트페이지로 이동
function backDetailpage(){
console.log("backDetailpage()");
    location.href= `/projectPage/detail?pjno=${pjno}`;
}//f end