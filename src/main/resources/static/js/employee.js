//부서명 출력
function onSelectPrint(){// 부서명/자격증명 받아오는 함수
    $.ajax({ // 부서명
        url: `/어디로 갈까`,
        method: `get`,
        success: (r)=>{
        let boardTableBody = document.querySelector(".pnoCategory");
        let html='';
        r.list (받아와 for문 돌려)
        }
    });
    $.ajax({// 자격증명
            url: `/어디로 갈까`,
            method: `get`,
            success: (r)=>{
            let boardTableBody = document.querySelector(".licenseCategory");
            let html='';
            }
        })
}

function onSignup(){// 사원등록 함수
    //==============사원 form=====================
    let employeeForm = document.querySelector('.employeeForm');
    console.log(employeeForm)
    let employeeFormData= new FormData(signupForm);
    console.log(employeeFormData); // new FormData

    $.ajax({
           url : '어디로 갈까',
           method : 'post',
           data : employeeFormData,
           contentType: false,
           processData : false,
           success : function result( result ){
               console.log(result);
               //4. 결과
               if(result){
                   alert('회원가입 성공');
                   location.href='/member/login';
               }else {
                   alert('회원가입 실패');
               }
           }
   });//ajax 끝

   //=============== 경력 form=============================
   let ecareerForm = document.querySelector('.ecareerForm');
       console.log(ecareerForm)

   let ecareerFormData= new FormData(ecareerForm);
   console.log(ecareerFormData); // new FormData

   $.ajax({
          url : '어디로 갈까',
          method : 'post',
          data : ecareerFormData,
          contentType: false,
          processData : false,
          success : function result( result ){
              console.log(result);
              //4. 결과
              if(result){
                  alert('회원가입 성공');
                  location.href='/member/login';
              }else {
                  alert('회원가입 실패');
              }
          }
  });//ajax 끝

   //=================자격증 form==============================
   let licenseForm = document.querySelector('.licenseForm');
       console.log(licenseForm)

   let licenseFormData= new FormData(licenseForm);
   console.log(licenseFormData); // new FormData

   $.ajax({
          url : '어디로 갈까',
          method : 'post',
          data : licenseFormData,
          contentType: false,
          processData : false,
          success : function result( result ){
              console.log(result);
              //4. 결과
              if(result){
                  alert('회원가입 성공');
                  location.href='/member/login';
              }else {
                  alert('회원가입 실패');
              }
          }
  })//ajax 끝
}