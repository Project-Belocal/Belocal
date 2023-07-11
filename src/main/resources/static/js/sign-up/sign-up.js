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
window.addEventListener("load", function () {
    const userId = document.querySelector(".sign-up__id");
    const idError = document.querySelector(".idError");
    let idCheck = false;

    userId.addEventListener("keyup", function () {
        let regEx = /^[a-z]+[a-z0-9]{5,15}$/g;

        if (!regEx.test(userId.value)) {
            idCheck = false;
            idError.innerHTML = "사용할 수 없는 아이디입니다.";
            idError.style.color = "red";
            idError.classList.remove("hidden");
        } else {
            fetch("sign-up/checkId", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: "type=user&id=" + encodeURIComponent(userId.value)
            })
                .then(response => response.text())
                .then(data => {
                    if (data == 1) {
                        idCheck = false;
                        idError.innerHTML = "이미 존재하는 아이디입니다.";
                        idError.style.color = "red";
                        idError.classList.remove("hidden");
                    } else {
                        idCheck = true;
                        idError.innerHTML = "사용가능한 아이디입니다.";
                        idError.style.color = "#0D6EFD";
                        idError.classList.remove("hidden");
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                });
        }
    });
});
//비밀번호 확인
window.addEventListener("load",function (){
    const pw = document.querySelector(".sign-up__pw");
    const pwError = document.querySelector(".pwError");
    const reConfrim = document.querySelector(".sign-up__reconfirm");
    const reconfirmError = document.querySelector(".reconfirmError");

    pw.addEventListener("keyup",function (){
        const regEx = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&+=!])(?!.*\s).{8,16}$/;


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
    const nickname = document.querySelector(".sign-up__nick-name");
    const nickError = document.querySelector(".nickError");
    let nickCheck = false;

    nickname.addEventListener("keyup",function (){
        let regEx = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;

        if (!regEx.test(nickname.value)){
            nickCheck = false;
            nickError.innerHTML = "사용할 수 없는 닉네임입니다."
            nickError.style.color ="red";
            nickError.classList.remove("hidden");
        }else {
            let xhr = new XMLHttpRequest();
            xhr.open("POST","sign-up/checkNickname", true);
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
            let params = "type=nickname&nickname=" + encodeURIComponent(nickname.value);
            xhr.send(params);
        }
    })
})

//번호인증 전송 & 번호 중복 확인
window.addEventListener("load",function (){
    const authNum = document.querySelector(".sign-up__input-Auth");

    const verification = document.querySelector(".sign-up__verification");
    const time = document.querySelector(".verification-timer");
    const verificationBtn = document.querySelector(".sign-up__verification-btn");

    const error = document.querySelector(".phoneError");
    const phoneNum = document.querySelector(".sign-up__tel");
    const sendBtn = document.querySelector(".sign-up__tel-btn");

    const signBtn = document.querySelector(".sign-up__btn");
    let sendNum;



    //번호를 다 채워야 버튼 눌리게
    phoneNum.addEventListener("input",function (){
        const inputValue = this.value;
        const sanitizedValue = inputValue.replace(/[^\d]/g, '');
        this.value = sanitizedValue;


        if (phoneNum.value.length === 11) {
            sendBtn.classList.remove("off");
            sendBtn.removeAttribute("disabled");
            error.style.visibility = "hidden"
        } else {
            sendBtn.classList.add("off");
        }

        if (inputValue !== sanitizedValue) {
            error.style.visibility = "visible"
        }

    })




    let timer = null;
    let isRunning = false;

    function startTimer(count, time) {
        let minutes, seconds;
        timer = setInterval(function() {
            minutes = parseInt(count / 60, 10);
            seconds = parseInt(count % 60, 10);
            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            time.textContent = minutes + ":" + seconds;

            if (--count < 0) {
                clearInterval(timer);
                time.textContent = "시간초과";
            }
        }, 1000);
        isRunning = true;
    }


    sendBtn.onclick = function (){
        verification.style.visibility = "visible"
        // 유효시간 설정
        let sec = 180;

        startTimer(sec,time)

        let tel = phoneNum.value;
        sendSms(tel);
    }

    verificationBtn.onclick=function (){
        let auth = authNum.value;
        let tel = phoneNum.value;

        sendVerification(auth,tel);
    }



    //문자 전송
    const sendSms = async (toPhone)=>{
        const response = await fetch("/sms/send",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                toPhone : toPhone
            })
        })
        console.log(response)
        if (response.ok){
            console.log("통과")
        }else {
            error.style.visibility = "visible"
            error.html ="인증번호 전송에 실패했습니다."
            console.log("Error",response.status,response.statusText);
        }

    }

    //문자인증
    const sendVerification = async (verificationNum,toPhone) =>{
        clearInterval(timer); // 타이머 멈추기
        const response = await fetch("/sms/verification",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                verificationNum : verificationNum,
                toPhone : toPhone
            })
        })
        if (response.ok){
            verificationBtn.classList.add("off")
            verificationBtn.hasAttribute("disable")
            clearInterval(timer); // 타이머 멈추기
            time.textContent = "인증완료";
            signBtn.classList.remove("off");
            signBtn.classList.add("action");
            signBtn.removeAttribute("disabled")
        }else {
            time.textContent = "인증실패";
            console.log("Error",response.status,response.statusText)
        }
    }



})


