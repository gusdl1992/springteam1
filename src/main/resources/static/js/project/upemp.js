let pjno = new URL(location.href).searchParams.get("pjno")
let check = [];
let score = {};
console.log("안녕")
onWrite();

function onWrite(){
    console.log(pjno)
    let html = "사용 가능한 초급 인원 : <br/>";

    $.ajax({
        type: "get",
        url: "/project/view/re.do?pjno="+pjno,
        async : false,
        success: (r) => {
            check = [r[0].length,r[1].length,r[2].length]
            console.log(check)
            console.log(r)
            for(let j=0; j < r[0].length; j++){
                html += `<div> <input type ="checkbox" onclick='getCheckboxValue()' value ="${r[0][j].employeeDto.eno}">
                <span>${r[0][j].employeeDto.eno}번</span>
                <span>이름 : ${r[0][j].employeeDto.ename}번</span>
                <span>사진 : ${r[0][j].employeeDto.img}번</span>
                <span>고과점수 : ${r[0][j].score}</span>
                </input> </div>`
            }

            document.querySelector(".s_check1").innerHTML = html;
            html = "사용 가능한 중급 인원 : <br/>";
            for(let j=0; j < r[1].length; j++){
                html += `<div> <input type ="checkbox" onclick='getCheckboxValue()' value ="${r[1][j].employeeDto.eno}">
                <span>${r[1][j].employeeDto.eno}번</span>
                <span>이름 : ${r[1][j].employeeDto.ename}</span>
                <span>사진 : ${r[1][j].employeeDto.img} </span>
                <span>고과점수 : ${r[1][j].score}</span>
                </input> </div>`
            }
            document.querySelector(".s_check2").innerHTML = html;

            html = "사용 가능한 고급 인원 :<br/>";
            for(let j=0; j < r[2].length; j++){
                html += `<div> <input type ="checkbox" onclick='getCheckboxValue()' value ="${r[0][2].employeeDto.eno}">
                <span>${r[2][j].employeeDto.eno}번</span>
                <span>이름 : ${r[2][j].employeeDto.ename}</span>
                <span>사진 : ${r[2][j].employeeDto.img} </span>
                <span>고과점수 : ${r[2][j].score}</span>
                </input> </div>`
            }
            document.querySelector(".s_check3").innerHTML = html;
            console.log(score);
        }
    });
}

function s_doPost(){
    let enos = [];
    let checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    checkboxes.forEach(function(checkbox) {
        enos.push(checkbox.value);
    });
    let data = {
        enos: enos,
        pjno: pjno
    };

    $.ajax({
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data), // 수정된 부분
        url: '/project/view/reassign',
        success: function(data) {
            alert(data);
        },
        error:function(e) {
            alert("error: " + e);
        }
    });
}

function getCheckboxValue()  {
  // 선택된 목록 가져오기
    let enos = [];
    let result = '';
    let checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    checkboxes.forEach(function(checkbox) {
        enos.push(checkbox.value);
        result += checkbox.value+"번 ";
    });

  // 선택된 목록에서 value 찾기

  // 출력
  document.querySelector('.selected').innerHTML = result;
}