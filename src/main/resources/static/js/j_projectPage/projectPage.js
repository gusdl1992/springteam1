printProjet();

//전체 프로젝트리스트 출력
function printProjet(){
    console.log("printProjet()");

    $.ajax({
        url : "/projectPage/list",
        method : "get",
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
                           <td>${r[i].state}</td>
                           <td>${r[i].start_date}</td>
                           <td>${r[i].end_date}</td>
                       </tr>`;
            }//for end

            html+=``;

            document.querySelector("#projectList").innerHTML=html;
        }//success end
    })//ajax end
}//f end






//글 삭제
function deleteDetail(pjno){

}//f end

