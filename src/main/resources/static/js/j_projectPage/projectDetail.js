let pjno=new URL(location.href).searchParams.get('pjno');
printProjectDetail();
//개별 프로젝트리스트 출력
function printProjectDetail(){
    console.log("printProjectDetail()");

    $.ajax({
         url : "/projectPage/detail.do",
         method : "get",
         data : {"pjno" : pjno},
         success : (r)=>{
            console.log("결과출력");
            document.querySelector(".detaile_pjno").innerHTML=r.pjno;
            document.querySelector(".detaile_start_date").innerHTML=r.start_date;
            document.querySelector(".detaile_end_date").innerHTML=r.end_date;
            document.querySelector(".detaile_rank1_count").innerHTML=r.rank1_count;
            document.querySelector(".detaile_rank2_count").innerHTML=r.rank2_count;
            document.querySelector(".detaile_rank3_count").innerHTML=r.rank3_count;
            document.querySelector(".detaile_title").innerHTML=r.title;
            document.querySelector(".detaile_request").innerHTML=r.request;
            document.querySelector(".detaile_note").innerHTML=r.note;
            document.querySelector(".detaile_compannyname").innerHTML=r.compannyname;
            document.querySelector(".detaile_state").innerHTML=r.state;
            document.querySelector(".detaile_price").innerHTML=r.price;
         }//success end
    })//ajax end
}//f end

function changeToUpdate(){
location.href= `/projectPage/update?pjno=${pjno}`;
}//f end
