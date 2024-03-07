console.log('mypageUpdate.js');

mypageUpdate();
function mypageUpdate(){
    console.log('mypageUpdate');
    $.ajax({
            url: '/mypage/info',
            method: 'get',
            success: (r) => {
            console.log(r);
            document.querySelector('.name').value = r.ename;
            document.querySelector('.pw').value = r.pw;
            document.querySelector('.email').value = r.email;
            document.querySelector('.phone').value = r.phone;
            document.querySelector('.address').value = r.address;
       }

    });
}



/*
    <div id="wrap">
        <div>
            <label>수정할 비밀번호 :</label> <input type="password"/> <br/>
            <label>수정할 이메일  : </label> <input type="email"/> <br/>
            <label>수정할 전화번호 : </label> <input type="test"/> <br/>
            <label>수정할 주소 : </label> <input type="text"/> <br/>
        </div>
        <button onclick="#" type="button">수정</button>
        <button onclick="#" type="button">취소</button>
    </div>



*/