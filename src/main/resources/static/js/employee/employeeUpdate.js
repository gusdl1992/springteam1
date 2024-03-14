let eno=new URL(location.href).searchParams.get('eno');
onSelectPrint()
employeeView()
//부서명 출력
function onSelectPrint(){// 부서명/자격증명 받아오는 함수
    $.ajax({ // 부서명 전체 출력
        url: `/partList`,
        method: `get`,
        async : false,
        success: (r)=>{
        console.log(r)
            let pnoCategory = document.querySelector(".pnoCategory");
            let html='';
            r.forEach( part => {
                 html+=`<option value="${part.pno}">${part.pname}</option>`;
            });
             pnoCategory.innerHTML = html;
         }
    });
}
//출력===========================================
//사원 인적사항 출력
function employeeView(){
    $.ajax({
         url : '/employee/view.do',
         method : 'get',
         data : {'eno':eno},
         success : (r)=>{
             console.log(r);
//             <img src="/img/${r2.uuidFile}">${r} 님
             document.querySelector('#img').innerHTML=`<img src="/img/eimg/${r.img}">`
             document.querySelector('#id').value=r.id;
             document.querySelector('#pw').value=r.pw;
             document.querySelector('#pno').value=r.pno;
             document.querySelector('#ename').value=r.ename;
             document.querySelector('#sex').value=r.sex;
             document.querySelector('#start_date').innerHTML=r.edate;
             document.querySelector('#phone').value=r.phone;
             document.querySelector('#email').value=r.email
             document.querySelector('#address').value=r.address
             document.querySelector('#eeducation').value=r.eeducation
         }
    });//ajax 끝*/
}

function onUpdate(){
    let employeeForm = document.querySelector('.employeeForm');
        console.log(employeeForm)
        let employeeFormData= new FormData(employeeForm);
        console.log(employeeFormData); // new FormData
        employeeFormData.append('eno', eno);
    $.ajax({
           url : '/employee/update.do',
           method : 'put',
           data : employeeFormData,
          contentType: false,
           processData : false,
           success :  (r)=>{
               console.log(r);
               //4. 결과
               if(r){
                   alert('수정 성공');
                   location.href=`/employee/view?eno=${eno}`;
               }else {
                   alert('수정 실패');
               }
           }
   });//ajax 끝
}