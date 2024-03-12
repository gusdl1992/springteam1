onEmployeeAllView()
// 전체 사원 호출하기
function onEmployeeAllView(){

    $.ajax({
        url: `/employee`,
        method: `get`,
        success: (r)=>{
        console.log(r)
            $.ajax({
                url:`/employeeList`,
                method:`get`,
                success: (re)=>{
                    let k_eList = document.querySelector("#k_eList");
                        let html='';
                        re.forEach( employee => {
                             html+=`<tr>
                                        <a href=`employee/view.do${employee.eno}`>
                                          <th>${employee.eno}</th>
                                          <th>${employee.ename}</th>
                                          <th>${employee.pname}</th>
                                          <th>${employee.phone}</th>
                                          <th>${employee.edate}</th>
                                          </a>
                                      </tr>`;
                        });
                         k_eList.innerHTML = html;

                    }
            });

         }
    });
}