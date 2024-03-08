console.log('myProject.js');
myProjectList()
// 나의 진행중인 프로젝트 리스트 출력
function myProjectList(){
    console.log('myProjectList()')
    let html =``;

    $.ajax({
        url : "/mypage/project/list" ,
        method : "get" ,
        async : false ,
        success : (r) => {
            console.log(r);
            html += `
                <div><span class="viewTitle">사원 명 :</span><span class="id">${r.id}</span></div>
                <div><span class="viewTitle">프로젝트 번호 :</span><span class="pjno">${r.pjno}</span></div>
                <div><span class="viewTitle">시작 날짜 :</span><span class="start_date">${r.start_date}</span></div>
                <div><span class="viewTitle">종료 날짜 :</span><span class="end_date">${r.end_date}</span></div>
                <div><span class="viewTitle">프로젝트 명 :</span><span class="title">${r.title}</span></div>
                <div><span class="viewTitle">회사 명 :</span><span class="compannyname">${r.compannyname}</span></div>
                <div><span class="viewTitle">프로젝트 현재 상황 :</span><span class="state">${r.state == 0 ? "진행중" : "관리자문의"}</span></div>
            `
                document.querySelector("#p_myProjectdate").innerHTML=html;
        }

    }); // ajax 종료
} // 함수 종료
