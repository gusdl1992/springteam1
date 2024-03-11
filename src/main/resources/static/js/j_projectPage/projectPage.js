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
console.log("projectPage-js");

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

            r.list.forEach((result)=>{
                html+=`<tr>
                           <th>${result.pjno}</th>
                           <td><a href="/projectPage/detail?pjno=${result.pjno}">${result.title}</a></td>
                           <td>${result.compannyname}</td>
                           <td>${result.price}</td>
                           <td>${result.rank1_count + result.rank2_count + result.rank3_count}</td>
                           <td>${result.state==0 ? "진행전" : (result.state==1 ? "진행중" : "진행완료")}</td>
                           <td>${result.start_date}</td>
                           <td>${result.end_date}</td>
                       </tr>`;
            })//for end
            document.querySelector("#projectList").innerHTML=html;

            //===== 페이지네이션 =====
            html=`<li class="page-item"><a class="page-link" onclick="printProjet(${page-1<1 ? page : page-1})">Previous</a></li>`;
            for(let i=1; i<=r.totalPage; i++){
                html+=`<li class="page-item"><a class="page-link" onclick="printProjet(${i})">${i}</a></li>`;
            }
            html+=`<li class="page-item"><a class="page-link" onclick="printProjet(${page+1>r.totalPage ? r.totalPage : page+1})">Next</a></li>`;

            document.querySelector(".pagination").innerHTML=html;
        }//success end
    })//ajax end
}//f end

//검색 기준 선택
function searchProject(){
    console.log("searchProject-js");

    let searchCategory= document.querySelector(".searchCategory").value;
    let searchInput=document.querySelector(".searchInput");
    switch (searchCategory){
        case "1" :
            searchInput.innerHTML=`<input class="searchValue" type="text" />`;
            pageObject.key="title";
            break;
        case "2" :
            searchInput.innerHTML=`<input class="searchValue" type="text" />`;
            pageObject.key="compannyname";
            break;
        case "3" :
            searchInput.innerHTML=`<input class="searchValue" type="text" />`;
            pageObject.key="price";
            break;
        case "4" :
            searchInput.innerHTML=`<select class="searchValue">
                                        <option value="0"> 진행전 </option>
                                        <option value="1"> 진행중 </option>
                                        <option value="2"> 진행완료 </option>
                                    </select>`;
            pageObject.key="state";
            break;
        case "5" :
            searchInput.innerHTML=`<input class="searchValue" type="date" />`;
            pageObject.key="start_date";
            break;
        case "6" :
            searchInput.innerHTML=`<input class="searchValue" type="date" />`;
            pageObject.key="end_date";
            break;
        default :
            searchInput.innerHTML=`<input class="searchValue" type="text" />`;
    }//switch end
}//f end

//검색
function searchBtn(){

}//f end

//정렬기준
function sortProject(){

}//f end

