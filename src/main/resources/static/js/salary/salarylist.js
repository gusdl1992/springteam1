
console.log("안녕")
onWrite()
function onWrite(){
    $.ajax({
        url:"/salary/list.do",
        method:"get",
        success: (r) => {
            let html ="받을 인물이 없습니다.";
            console.log(r)
            r.forEach((i) => {
                 html += `<input type="checkbox" class="eno${i.employeeDto.eno}" value ="{eno:${i.employeeDto.eno},price:${i.price}}"> <a href = "#"> ${i.employeeDto.ename} 사원 </a> 월급 ${i.price}만원<button type="button" onclick="doDel(${i.employeeDto.eno})">제거</button>  <br/>`  //추후 상세보기 링크로 만들 예정
            })



            document.querySelector(".test").innerHTML = html;
        }
    })
}

function doPost(){
    let checkboxes1 = document.querySelectorAll('input:checked');
    console.log(checkboxes1)
    let result = []
    checkboxes1.forEach(function(checkbox) {
        console.log(checkbox);
        result.push(checkbox.value)
    });
    console.log(result);
    $.ajax({
        type: "POST",
        url: "/salary/insert.do",
        contentType: "application/json",
        data: JSON.stringify(result),
        success: function(response) {
            // 성공적으로 응답을 받았을 때 실행할 코드
            console.log("서버 응답: " + response);
        },
        error: function(xhr, status, error) {
            // 오류가 발생했을 때 실행할 코드
            console.error("Ajax 오류: " + status, error);
        }
    });
}

function doDel(i){
    console.log(i);
}