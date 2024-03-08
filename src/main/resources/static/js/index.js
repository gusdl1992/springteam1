

// 1. 로그인 여부 확인 요청 [ JS 열릴때마다 체크 ]
$.ajax({
    url : '/employee/login/check' ,
    method : 'get' ,
    async : false,
    success : (r)=>{
        console.log(r);
        // 1. 어디에
        let login_menu = document.querySelector('#login_menu');
        // 2. 무엇을
        let html = '';
        if(r != ''){ // 로그인 했을떄
        $.ajax({
            url : '/employee/login/checkname' ,
            method : 'get' ,
            data:{eno : r},
            async : false ,
            success : (r2) =>{
                html = `
                    <li class="nav-item">
                        <a class="nav-link" onclick = "logout()">로그아웃</a>
                    </li>
                    <li class="nav-item">
                        <img src="/img/${r2.uuidFile}"/> ${r2}님
                    </li>
                `;
            }
        });
                }else{  // 로그인 안했을떄
                    html = `
                        <li class="nav-item">
                            <a class="nav-link" href="#">로그인</a>
                        </li>
                    `;
                }
                // 3. 대입
                login_menu.innerHTML = html;
            } // s end
        }); // ajax end