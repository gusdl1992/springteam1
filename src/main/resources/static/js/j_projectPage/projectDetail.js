let pjno=new URL(location.href).searchParams.get('pjno');
printProjectDetail();
//개별 프로젝트리스트 출력
function printProjectDetail(){
    console.log("printProjectDetail()");

    $.ajax({
         url : "/projectPage/detail.do",
         method : "get",
         async: false,
         data : {"pjno" : pjno},
         success : (r)=>{
            console.log("결과출력");
            console.log(r);
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
            document.querySelector(".detaile_state").innerHTML=r.state==0 ? "진행전" : (r.state==1 ? "진행중" : "진행완료");
            document.querySelector(".detaile_price").innerHTML=r.price;
            document.querySelector(".buttons").innerHTML += `<button type="button" onclick="goToRec( ${r.state} ,${pjno})">프로젝트 인원 등록</button>
            <button type="button" onclick="goToRe( ${r.state} ,${pjno})">프로젝트 인원 수정</button>`
         }//success end
    })//ajax end
        $.ajax({
            url: "/project/view/list?pjno="+pjno,
            method:"get",
            success : (r) =>{
                console.log(r.length)
                let html = "프로젝트에 참여중인 인원: ";
                console.log(r[1])
                console.log(r[0])
                for(let i = 0; i < r.length; i++){
                    html += `${r[i]} `
                }
                console.log(html)
                document.querySelector(".logs").innerHTML= html;
            }
        })
}//f end

//수정페이지로 이동
function changeToUpdate(){
location.href= `/projectPage/update?pjno=${pjno}`;
}//f end

//삭제
function deleteDetail(){
    console.log("deleteDetail()");
    if(confirm("삭제하시겠습니까?")){
        $.ajax({
            url : "/projectPage/delete",
            method : "Delete",
            data : {"pjno" : pjno},
            success : (r) => {
                console.log(r);
                if(r){
                    alert("삭제 성공");
                    location.href="/projectPage/"
                }
                else{
                    alert("삭제 실패");
                }
            }//success end
        })//ajax end
    }//if end
    else{
        alert("삭제가 취소되었습니다.");
    }
}//f end

function checkstate(state){
    if(state <= 1){
        return true;
    }
    else{
    return false;
    }
}


function goToRec(state, pjno){
    if(checkstate(state)){
        location.href="/project/view/rec?pjno="+pjno
    }
    else{
        alert("이미 종료된 프로젝트입니다.")
    }
}

function goToRe(state, pjno){
    if(checkstate(state)){
        location.href="/project/view/re?pjno="+pjno
    }
    else{
        alert("이미 종료된 프로젝트입니다.")
    }
}