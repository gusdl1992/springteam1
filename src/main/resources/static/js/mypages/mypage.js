console.log('mypage.js 호출');

onMypageView()
function onMypageView(){

    let dataView = document.querySelector('#p_dataView');
    console.log(dataView);

    let html = '';
    $.ajax({
        url: '/mypage/info',
        method: 'get',
        async : false ,
        success: (r) => {
            console.log(r);
            let 부서명 = '';
            let 성별 ='';
            if(r.sex){성별 = '여성'}else{ 성별 = '남성'};
            if(r.pno == 1){부서명 = '인사과' };
            if(r.pno == 2){부서명 = '영업' };
            if(r.pno == 3){부서명 = '프로그래머' };

            html += `
                사원번호 : ${r.eno} <br/>
                아이디   : ${r.id} <br/>
                이름     : ${r.ename} <br/>
                이메일   : ${r.email} <br/>
                전화번호  : ${r.phone} <br/>
                주소     : ${r.address} <br/>
                성별     : ${성별} <br/>
                학력     : ${r.eeducation} <br/>
                입사일   : ${r.edate} <br/>
                업무 부서 : ${부서명} <br/>
                이미지   : <img src="/img/eimg/${r.img}" alt="프로필사진">
            `;
                dataView.innerHTML = html;
        }
    });
     // 부서 번호 1 : 인사과 2 : 영업 3: 프로그래머
}




