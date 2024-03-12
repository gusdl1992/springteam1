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



}


function onSignup(){// 사원등록 함수
    //==============사원 form=====================
    let employeeForm = document.querySelector('.employeeForm');
    console.log(employeeForm)
    let employeeFormData= new FormData(employeeForm);
    console.log(employeeFormData); // new FormData

    $.ajax({
           url : '/signup',
           method : 'post',
           data : employeeFormData,
          contentType: false,
           processData : false,
           success :  (r)=>{
               console.log(r);
               //4. 결과
               if(r){
                   alert('사원정보 등록');
                   location.href='/employee/employeeView'+r;
               }else {
                   alert('사원정보 실패');
               }
           }
   });//ajax 끝

}
// 경력 입력칸 추가 함수
function onPlusCareer(){
console.log('onPlusCareer()')
    let ecareer = document.querySelector('.ecareer');
    let html='';
        html+=`<tr>
                        <td><input type="text" class="companyname" name="companyname"></td>
                        <td><input onchange="" class="start_date" name="start_date"type="date"></td>
                        <td><input onchange=" " class="end_date" name="end_date" type="date"></td>
                        <td><input type="text" class="note" name="note"></td>
                        <td><button onclick="onPlusCareer()">추가</button></td>
                        <td><button onclick="">삭제</button></td>
                    </tr>`
    ecareer.innerHTML += html;
}
// 나중에 버튼으로 넣을 예정

/* 자격증 카테고리 불러오는 코드
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
*/

/*

/*
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