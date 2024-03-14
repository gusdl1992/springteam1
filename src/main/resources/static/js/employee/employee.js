onEmployeeAllView()
// 전체 사원 호출하기
function onEmployeeAllView(){
    $.ajax({
        url:`/employeeList`,
        method:`get`,
        success: (re)=>{
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

/*
function (){

}*/
