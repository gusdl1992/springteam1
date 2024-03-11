//============ 페이지 정보 관련 객체 = 여러개의 변수를 묶음 =============
let pageObject={
    page : 1,           //현재페이지
    pageBoardSize : 5,  //페이지당 표시 할 개수
    sortKey : 0,            //정렬 기준
    key : 'b.btitle',   //현재검색 key
    keyword : ''        //현재검색 keyword
}
//================================================================

printProjet(1);

//전체 프로젝트리스트 출력
function printProjet(page){
    console.log("printProjet()");
    pageObject.page=page;

    $.ajax({
        url : "/projectPage/list",
        method : "get",
        data : pageObject,
        success : (r)=>{
            console.log(r);
            let html=``;

            for(let i=0; i<r.length; i++){
                html+=`<tr>
                           <th>${r[i].pjno}</th>
                           <td><a href="/projectPage/detail?pjno=${r[i].pjno}">${r[i].title}</a></td>
                           <td>${r[i].compannyname}</td>
                           <td>${r[i].price}</td>
                           <td>${r[i].rank1_count + r[i].rank2_count + r[i].rank3_count}</td>
                           <td>${r[i].state==0 ? "진행전" : (r[i].state==1 ? "진행중" : "진행완료")}</td>
                           <td>${r[i].start_date}</td>
                           <td>${r[i].end_date}</td>
                       </tr>`;
            }//for end
            document.querySelector("#projectList").innerHTML=html;

            //===== 페이지네이션 =====
            html=`<li class="page-item"><a class="page-link" href="printProjet(${page-1<1 ? page : page-1})">Previous</a></li>`;
            for(let i=r.startPage; i<=r.end Page ; i++){
                html+=`<li class="page-item"><a class="page-link" href="printProjet(${page})">${page}</a></li>`;
            }
            html+=`<li class="page-item"><a class="page-link" href="printProjet(${page+1>r.totalPage})">Next</a></li>`;

            document.querySeletor(".pagination").innerHTML=html;
        }//success end
    })//ajax end
}//f end






//글 삭제
function deleteDetail(pjno){

}//f end

