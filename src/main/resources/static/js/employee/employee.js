

let ePageObject={
    page : 1,           //현재페이지
    pageBoardSize : 5,  //페이지당 표시 할 개수
    sortKey : 0,            //정렬 기준
    key : null,   //현재검색 key
    keyword : '',       //현재검색 keyword
}
let info={
    key: 0 ,
    keyword : null
}
onEmployeeAllView()
// 전체 사원 호출하기
function onEmployeeAllView(){
    $.ajax({
        url:`/employeeList`,
        method:`get`,
        data : info,
        success: (re)=>{
        console.log(info)

            let k_eList = document.querySelector("#k_eList");
                let html='';
                re.forEach( employee => {
                     html+=`<tr>
                                  <th>${employee.eno}</th>
                                  <th><a href="employee/view?eno=${employee.eno}">${employee.ename}</a></th>
                                  <th>${employee.pname}</th>
                                  <th>${employee.phone}</th>
                                  <th>${employee.edate}</th>
                              </tr>`;
                });
                 k_eList.innerHTML = html;

            }
    });
}

function onPartPrint(){
     let key=document.querySelector('.key').value;
        let keyword=document.querySelector('.keyword').value;

        //2. 서버에 전송할 객체에 담아주고
        info.key=key;
        info.keyword=keyword;

        //3. 출력 함수 호출
        onEmployeeAllView();
        //
}
