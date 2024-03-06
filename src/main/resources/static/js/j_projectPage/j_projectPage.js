printProjet();
let html=``;

//전체 프로젝트리스트 출력
function printProjet(){
    console.log("printProjet()");

    $.ajax({
        url : "/projectPage/list",
        method : "get",
        success : (r)=>{
            console.log(r);

            html+=`<!--검색-->
                     <!--검색기준-->
                     <select>
                         <option>프로젝트명</option>
                         <option>회사명</option>
                         <option>규모</option>
                         <option>필요인원</option>
                         <option>상태</option>
                         <option>시작일</option>
                         <option>종료일</option>
                     </select>
                     <input type="text" />
                     <button type="button">검색</button>
                     <!--프로젝트 전체 출력-->
                         <table>
                             <thead>
                                 <tr>
                                     <th>no.</th>
                                     <th>프로젝트명</th>
                                     <th>회사명</th>
                                     <th>규모</th>
                                     <th>필요인원</th>
                                     <th>상태</th>
                                     <th>시작일</th>
                                     <th>종료일</th>
                                 </tr>
                             </thead>
                             <tbody id="projectList">`;

            for(let i=0; i<r.length; i++){
                html+=`<tr>
                           <th>${r[i].pjno}</th>
                           <td onclick="printProjectDetail(${r[i].pjno})">${r[i].title}</td>
                           <td>${r[i].compannyname}</td>
                           <td>${r[i].price}</td>
                           <td>${r[i].rank1_count + r[i].rank2_count + r[i].rank3_count}</td>
                           <td>${r[i].state}</td>
                           <td>${r[i].start_date}</td>
                           <td>${r[i].end_date}</td>
                       </tr>`;
            }//for end

            html+=`</tbody>
               </table>`;

            document.querySelector("#projectMainView").innerHTML=html;
        }//success end
    })//ajax end
}//f end

//개별 프로젝트리스트 출력
function printProjectDetail(pjno){
    html=``;
    console.log("printProjectDetail()");

    $.ajax({
         url : "/projectPage/detail",
         method : "get",
         data : {"pjno" : pjno},
         success : (r)=>{
            console.log("결과출력");
            html+=`<p>pjno : ${r.pjno}</p>
                    <p>start_date : ${r.start_date}</p>
                    <p>end_date : ${r.end_date}</p>
                    <p>rank1_count : ${r.rank1_count}</p>
                    <p>rank2_count : ${r.rank2_count}</p>
                    <p>rank3_count : ${r.rank3_count}</p>
                    <p>title : ${r.title}</p>
                    <p>request : ${r.request}</p>
                    <p>note : ${r.note}</p>
                    <p>compannyname : ${r.compannyname}</p>
                    <p>state : ${r.state}</p>
                    <p>price : ${r.price}</p>`;
            document.querySelector("#projectMainView").innerHTML=html;

         }//success end
    })//ajax end
}//f end
