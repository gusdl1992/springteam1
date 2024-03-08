onSelectPrint()
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

    $.ajax({// 자격증명
            url: `/license`,
            method: `get`,
            success: (r)=>{
            console.log(r)
            let licenseCategory = document.querySelector(".licenseCategory");
            let html='';
            r.forEach( license => {
                         html+=`<option value="${license.lno}">${license.lname}</option>`;
                    });
                     licenseCategory.innerHTML = html;
                 }
        })


}


function onSignup(){// 사원등록 함수
    //==============사원 form=====================
    let employeeForm = document.querySelector('.employeeForm');
    console.log(employeeForm)
    let employeeFormData= new FormData(employeeForm);
    console.log(employeeFormData); // new FormData

    $.ajax({
           url : '/esignup',
           method : 'post',
           data : employeeFormData,
          contentType: false,
           processData : false,
           success :  (r)=>{
               console.log(r);
               //4. 결과
               if(r){
                   alert('사원정보 성공');
               }else {
                   alert('사원정보 실패');
               }
           }
   });//ajax 끝
/*
   //=============== 경력 form=============================
   let ecareerForm = document.querySelector('.ecareerForm');
       console.log(ecareerForm)

   let ecareerFormData= new FormData(ecareerForm);
   console.log(ecareerFormData); // new FormData

   $.ajax({
          url : '/csignup',
          method : 'post',
          data : ecareerFormData,
          contentType: false,
          processData : false,
          success : (r)=>{
              console.log(r);
              //4. 결과
              if(r){
                  alert('경력등록 성공');
              }else {
                  alert('경력등록 실패');
              }
          }
  });//ajax 끝

   //=================자격증 form==============================
   let licenseForm = document.querySelector('.licenseForm');
       console.log(licenseForm)

   let licenseFormData= new FormData(licenseForm);
   console.log(licenseFormData); // new FormData

   $.ajax({
          url : '/lsignup',
          method : 'post',
          data : licenseFormData,
          success :(r)=>{
              console.log(r);
              //4. 결과
              if(r){
              // 자격증까지 성공하면 사원 목록 페이지?
                  alert('자격증등록 성공');
                  location.href='/member/login';
              }else {
                  alert('자격증등록 실패');
              }
          }
  })//ajax 끝*/
}