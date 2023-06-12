//뒤로가기 버튼
window.addEventListener("load",function(){
    const undo = document.querySelector(".sign-up__undo")

    undo.onclick = function (){
        history.go(-1);
    }
})

//생년월일 데이터 전달
window.addEventListener("load", function (){
    const birthYearEl = document.querySelector(".year");
    const birthMonthEl = document.querySelector(".month");
    const birthDayEl = document.querySelector(".day");
    let isYearOptionExisted = false;
    let isMonthOptionExisted = false;
    let isDayOptionExisted = false;


    birthYearEl.addEventListener('focus', function () {
        if(!isYearOptionExisted) {
            isYearOptionExisted = true;
            for(let i = 1940; i <= 2023; i++) {
                const YearOption = document.createElement('option');
                YearOption.setAttribute('value', i);
                YearOption.innerText = i;
                this.appendChild(YearOption);
            }
        }
    });

    birthMonthEl.addEventListener('focus', function () {
        if (!isMonthOptionExisted) {
            isMonthOptionExisted = true;
            for (let i = 1; i <= 12; i++) {
                const monthOption = document.createElement('option');
                const formattedValue = i.toString().padStart(2, '0');
                monthOption.setAttribute('value', formattedValue);
                monthOption.innerText = formattedValue;
                this.appendChild(monthOption);
            }
        }
    });

    birthDayEl.addEventListener('focus', function () {
        if (!isDayOptionExisted) {
            isDayOptionExisted = true;
            for (let i = 1; i <= 31; i++) {
                const dayOption = document.createElement('option');
                const formattedValue = i.toString().padStart(2, '0');
                dayOption.setAttribute('value', formattedValue);
                dayOption.innerText = formattedValue;
                this.appendChild(dayOption);
            }
        }
    });

// select 요소들을 가져옵니다.
    const yearSelect = document.querySelector('.box.year');
    const monthSelect = document.querySelector('.box.month');
    const daySelect = document.querySelector('.box.day');

// birth 입력란을 가져옵니다.
    const birthInput = document.querySelector('.birth');

// select 요소의 변경 이벤트 리스너를 추가합니다.
    yearSelect.addEventListener('change', updateBirth);
    monthSelect.addEventListener('change', updateBirth);
    daySelect.addEventListener('change', updateBirth);

// birth 값을 업데이트하는 함수를 정의합니다.
    function updateBirth() {
        // 선택한 연도, 월, 일 값을 가져옵니다.
        const year = yearSelect.value;
        const month = monthSelect.value;
        const day = daySelect.value;

        // 선택한 값들을 birth 입력란에 넣습니다.
        birthInput.value = year + '-' + month + '-' + day;
    }
})

//아이디 중복 확인
window.addEventListener("load",function (){
    const userId = document.querySelector(".sign-up__id");
    const idError = document.querySelector(".idError");
    let idCheck = false;

    userId.addEventListener("keyup",function (){
        let regEx = /^[a-z]+[a-z0-9]{5,15}$/g;

        if (!regEx.test(userId.value)){
            idCheck = false;
            idError.innerHTML = "사용할 수 없는 아이디입니다.";
            idError.style.color = "red";
            idError.classList.remove("hidden");
        }else {
            let xhr = new XMLHttpRequest();
            xhr.open("POST","sign-up/checkId", true);
            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xhr.onreadystatechange = function (){
                if (xhr.readyState ===4 && xhr.status ===200){
                    let data = xhr.responseText;
                    if (data==1){
                        idCheck = false;
                        idError.innerHTML = "이미 존재하는 아이디입니다.";
                        idError.style.color = "red";
                        idError.classList.remove("hidden");
                    }else {
                        idCheck = true;
                        idError.innerHTML = "사용가능한 아이디입니다.";
                        idError.style.color ="#0D6EFD";
                        idError.classList.remove("hidden");
                    }
                }
            };
            let params = "type=user&id=" + encodeURIComponent(userId.value);
            xhr.send(params);
        }
    })


})

//비밀번호 확인
window.addEventListener("load",function (){
    const pw = document.querySelector(".sign-up__pw");
    const pwError = document.querySelector(".pwError");
    const reConfrim = document.querySelector(".sign-up__reconfirm");
    const reconfirmError = document.querySelector(".reconfirmError");

    pw.addEventListener("keyup",function (){
        let regEx = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/;


        if (!regEx.test(pw.value)){
            pwError.innerHTML = "영문, 숫자, 특수문자를 조합8~16자를 입력해주세요.";
            pwError.style.color = "red";
            pwError.classList.remove("hidden");
        }else {
            pwError.innerHTML = "사용가능한 비밀번호 입니다."
            pwError.style.color ="#0D6EFD";
            pwError.classList.remove("hidden");
        }
    });

    reConfrim.addEventListener("keyup",function (){
        if (pw.value != reConfrim.value) {
            reconfirmError.innerHTML = "비밀번호가 일치하지 않습니다.";
            reconfirmError.style.color = "red";
            reconfirmError.classList.remove("hidden");
        }else {
            reconfirmError.innerHTML ="비밀번호가 일치합니다."
            reconfirmError.style.color ="#0D6EFD";
            reconfirmError.classList.remove("hidden");
        }
    })

})

//닉네임 중복 확인
window.addEventListener("load",function (){
    const nickName = document.querySelector(".sign-up__nick-name");
    const nickError = document.querySelector(".nickError");
    let nickCheck = false;

    nickName.addEventListener("keyup",function (){
        let regEx = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;

        if (!regEx.test(nickName.value)){
            nickCheck = false;
            nickError.innerHTML = "사용할 수 없는 닉네임입니다."
            nickError.style.color ="red";
            nickError.classList.remove("hidden");
        }else {
            let xhr = new XMLHttpRequest();
            xhr.open("POST","sign-up/checkNickName", true);
            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xhr.onreadystatechange = function (){
                if (xhr.readyState===4 && xhr.status===200){
                    let data = xhr.responseText;
                    if (data==1){
                        nickCheck = false;
                        nickError.innerHTML ="이미 존재하는 닉네임입니다."
                        nickError.style.color ="red";
                        nickError.classList.remove("hidden");
                    }else {
                        nickCheck = true;
                        nickError.innerHTML ="사용가능한 닉네임입니다."
                        nickError.style.color ="#0D6EFD";
                        nickError.classList.remove("hidden");
                    }
                }
            }
            let params = "type=nickname&nickName=" + encodeURIComponent(nickName.value);
            xhr.send(params);
        }
    })
})


