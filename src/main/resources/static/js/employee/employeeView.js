

// 경력 입력창 보여주는 함수
function OnCareerPlus(){
    let careerBox = document.querySelector('.careerBox');
    let html='';

    html=` <tr>
              <td><input type="text" id="companyname" name="companyname"></td>
              <td><input onchange="" id="start_date" name="start_date"type="date"></td>
              <td><input onchange=" " id="end_date" name="end_date" type="date"></td>
              <td><input type="text" id="note" name="note"></td>
              <td><input type="file" id="cimg" name="cimg"></td>
              <button onclick="OnCareer()" type="button">경력 등록</button>
          </tr>`
    careerBox.innerHTML=html;
}
// 경력 등록 함수
function OnCareer(){
    let careerForm = document.querySelector('.careerForm');
           console.log(careerForm)

       let careerFormData= new FormData(careerForm);
       console.log(careerFormData); // new FormData

       $.ajax({
                 url : '/careerPost',
                 method : 'post',
                 data : careerFormData,
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
         });//ajax 끝*/
}

//경력 내역 출력
function CareerPrint(){
    $.ajax({
        url: `/`,
        method: `get`,
        data:  {eno:eno} ,
        success: (r)=>{
        console.log(r)

            let careerBox = document.querySelector(".careerBox");
                let html='';
                re.forEach( career => {
                     html+=`<tr>
                                  <th>${career.companyname}</th>
                                  <th>${career.start_date}</th>
                                  <th>${career.end_date}</th>
                                  <th>${career.note}</th>
                                  <th>${career.eimg}</th>
                              </tr>`;
                });
                 careerBox.innerHTML = html;



         }
    });
}
// 자격증 선택
function OnLicensePlus(){
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
    let licenseBox = document.querySelector('.licenseBox');
    let html='';
    html=` <tr>
             <td>
                <select>
                <option value="${license.lno}">${license.lname}</option>
                </select>
               </td>
               <td><input type="date"></td>
            </tr>`
    licenseBox.innerHTML+=html;
}
//=============== 경력 form=============================
  /* let ecareerForm = document.querySelector('.ecareerForm');
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
  });//ajax 끝*/

