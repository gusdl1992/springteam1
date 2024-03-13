let eno=new URL(location.href).searchParams.get('eno');
let index = 1;
let index1 = 1;
employeeView()
//사원 인적사항 출력
function employeeView(){
    $.ajax({
         url : '/employee/view.do',
         method : 'post',
         data : postFormData,
         contentType: false,
         processData : false,
         success : (r)=>{
             console.log(r);
             //4. 결과
             if(r){
                 alert('경력등록 성공');
                 let html='';
                 document.querySelector('.careerBox').innerHTML=html;
             }else {
                 alert('경력등록 실패');
             }
         }
    });//ajax 끝*/
}
// 경력 입력창 보여주는 함수
function OnCareerPlus(){
    let careerBox = document.querySelector('.careerBox');
    let html='';

    html=`
         <form class="careerForm${index} postForm">
            <div class="tr">
              <div class="td"><input type="text" id="companyname" name="companyname"></div>
              <div class="td"><input onchange="" id="start_date" name="start_date"type="date"></div>
              <div class="td"><input onchange=" " id="end_date" name="end_date" type="date"></div>
              <div class="td"><input type="text" id="note" name="note"></div>
              <div class="td"><input type="file" id="cimg" name="cimg"></div>
              <div class="td"><button onclick="" type="button">삭제</button></div>
            </div>
        </form>
        `
        index++;
    let button=document.querySelector('.cBtn');
    btn=`<button onclick="OnCareer()" type="button">경력 등록</button>`
    button.innerHTML=btn
    careerBox.innerHTML+=html;
}

// 경력 등록 함수
function OnCareer(){
    let careerFormList = document.querySelectorAll('.postForm');
    for( let i = 0 ; i< careerFormList.length ; i++  ){

        let postFormData = new FormData(  careerFormList[i] );

        $.ajax({
             url : '/careerPost',
             method : 'post',
             data : postFormData,
             contentType: false,
             processData : false,
             success : (r)=>{
                 console.log(r);
                 //4. 결과
                 if(r){
                     alert('경력등록 성공');
                     let html='';
                     document.querySelector('.careerBox').innerHTML=html;
                 }else {
                     alert('경력등록 실패');
                 }
             }
        });//ajax 끝*/
    }

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
                     html+=`
                            <tr>
                                  <th>${career.companyname}</th>
                                  <th>${career.start_date}</th>
                                  <th>${career.end_date}</th>
                                  <th>${career.note}</th>
                                  <th>${career.eimg}</th>
                              </tr>
                              `;
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
            let licenseBox = document.querySelector('.licenseBox');
            let html='';
                html=` <div class="tr">
                         <div class="td">
                            <select class="licenseCategory${index1}">

                            </select>
                           </div>
                           <div class="td"><input type="date"></div>

                        </tr>`
            licenseBox.innerHTML+=html;

            let licenseCategory = document.querySelector(".licenseCategory"+index1);
            let option='';
            r.forEach( license => {
                 option+=`<option value="${license.lno}">${license.lname}</option>`;
            });
            index1++;
            let button=document.querySelector('.lBtn');
                btn=`<button onclick="" type="button">자격증 등록</button>`
                button.innerHTML=btn
            licenseCategory.innerHTML += option;
        }
    })

}

