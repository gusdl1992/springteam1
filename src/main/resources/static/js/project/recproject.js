
let pjno = new URL(location.href).searchParams.get("pjno")
let check = [];


onWrite();
function onWrite(){
    console.log(pjno)
    let html = "사용 가능한 초급 인원 : <br/>";

    $.ajax({
        type: "get",
        url: "/project/view/rec.do?pjno="+pjno,
        success: (r) => {
            check = [r[0].length,r[1].length,r[2].length]
            console.log(check)
            console.log(r)
            for(let j=0; j < r[0].length; j++){
                html += `<input type ="checkbox" onclick='getCheckboxValue()' value ="${r[0][j].eno}">${r[0][j].eno}번 </input>`
            }

            document.querySelector(".s_check1").innerHTML = html;
            html = "사용 가능한 중급 인원 : <br/>";
            for(let j=0; j < r[1].length; j++){
                html += `<input type ="checkbox" onclick='getCheckboxValue()' value ="${r[1][j].eno}">${r[1][j].eno}번 </input>`
            }
            document.querySelector(".s_check2").innerHTML = html;

            html = "사용 가능한 고급 인원 :<br/>";
            for(let j=0; j < r[2].length; j++){
                html += `<input type ="checkbox" onclick='getCheckboxValue()' value ="${r[2][j].eno}">${r[2][j].eno}번 </input>`
            }
            document.querySelector(".s_check3").innerHTML = html;

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
        url: '/project/view/assign',
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