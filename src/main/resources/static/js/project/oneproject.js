console.log("안녕")

console.log(new URL(location.href))
    // 2. 경로상의 변수들
console.log(new URL(location.href).searchParams)
    // 3. 변수들에서 특정 매개변수 호출
console.log(new URL(location.href).searchParams.get("pjno"))

let pjno = new URL(location.href).searchParams.get("pjno")

onView();
//1.게시물 개별 조회
function onView(){
    console.log("onview")
    $.ajax({
        url: "/project/view.do?pjno="+pjno,
        method : "get",
        success : (r) => {
            console.log(r)
            document.querySelector(".pjno").innerHTML = "제목 : "+r.pjno
            document.querySelector(".start_date").innerHTML = "내용 : "+r.start_date
            document.querySelector(".end_date").innerHTML = "카테고리 :"+r.end_date
            document.querySelector(".rank1_count").innerHTML = "작성자 : "+r.rank1_count
            document.querySelector(".rank2_count").innerHTML = "작성일 : "+r.rank2_count
            document.querySelector(".rank3_count").innerHTML = "조회수 : "+r.rank3_count
            document.querySelector(".title").innerHTML = "첨부파일 : "+r.title
            document.querySelector(".request").innerHTML = "첨부파일 : "+r.request
            document.querySelector(".note").innerHTML = "첨부파일 : "+r.note
            document.querySelector(".compannyname").innerHTML = "첨부파일 : "+r.compannyname
            document.querySelector(".state").innerHTML = "첨부파일 : "+r.state
            document.querySelector(".price").innerHTML = "첨부파일 : "+r.price
            document.querySelector(".buttons").innerHTML = `<button type="button" onclick=location.href="/project/view/rec?pjno=${pjno}">프로젝트 인원 등록</button>`
        }
    })
}

