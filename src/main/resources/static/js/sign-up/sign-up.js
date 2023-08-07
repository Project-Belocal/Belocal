//뒤로가기 버튼
window.addEventListener("load", function () {
    const undo = document.querySelector(".sign-up__undo")
    //뒤로가기
    undo.onclick = function () {
        history.go(-1);
    }

    //회원아이디
    const id = document.getElementById("input-text__content-id")
    const idWrapper = id.parentElement
    const idSpan = idWrapper.nextElementSibling;

    //id 중복 검사
    id.addEventListener("keyup", function () {
        let regEx = /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/;


        let userId = id.value;
        fetch("api/register/id/validation", {
            method: "POST",
            headers: {
                "Content-Type": "application/JSON"
            },
            body: JSON.stringify({
                userId
            })
        })
            .then(response => response.status)
            .then(data => {
                if (!regEx.test(id.value)) {
                    idWrapper.classList.add("input-text__content-wrapper--error")
                    idWrapper.classList.remove("input-text__content-wrapper--correct")
                    idSpan.classList.add("input__message--appear")
                    idSpan.innerHTML = "올바른 형식으로 입력해 주세요."
                    return;
                }

                if (data === 200) {
                    idWrapper.classList.remove("input-text__content-wrapper--error")
                    idWrapper.classList.add("input-text__content-wrapper--correct")
                    idSpan.classList.add("input__message--appear")
                    idSpan.innerHTML = "사용 가능한 아이디입니다."
                } else {
                    idWrapper.classList.add("input-text__content-wrapper--error")
                    idWrapper.classList.remove("input-text__content-wrapper--correct")
                    idSpan.classList.add("input__message--appear")
                    idSpan.innerHTML = "이미 존재하는 아이디입니다."
                }
            })
    })

    //회원 닉네임
    const nickname = document.getElementById("input-text__content-nickname")
    const nicknameWrapper = nickname.parentElement
    const nicknameSpan = nicknameWrapper.nextElementSibling;

    //닉네임 중복 검사
    nickname.addEventListener("keyup", function () {
        let regEx = /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/


        let userNickname = nickname.value;
        fetch("api/register/nickname/validation", {
            method: "POST",
            headers: {
                "Content-Type": "application/JSON"
            },
            body: JSON.stringify({
                userNickname
            })
        })
            .then(response => response.status)
            .then(data => {
                if (!regEx.test(nickname.value)) {
                    nicknameWrapper.classList.add("input-text__content-wrapper--error")
                    nicknameWrapper.classList.remove("input-text__content-wrapper--correct")
                    nicknameSpan.classList.add("input__message--appear")
                    nicknameSpan.innerHTML = "올바른 형식으로 입력해 주세요."
                    return;
                }

                if (data === 200) {
                    nicknameWrapper.classList.remove("input-text__content-wrapper--error")
                    nicknameWrapper.classList.add("input-text__content-wrapper--correct")
                    nicknameSpan.classList.add("input__message--appear")
                    nicknameSpan.innerHTML = "사용 가능한 아이디입니다."
                } else {
                    nicknameWrapper.classList.add("input-text__content-wrapper--error")
                    nicknameWrapper.classList.remove("input-text__content-wrapper--correct")
                    nicknameSpan.classList.add("input__message--appear")
                    nicknameSpan.innerHTML = "이미 존재하는 아이디입니다."
                }
            })
    })

    //비밀번호
    const pw = document.getElementById("input-text__content-pw")
    const pwWrapper = pw.parentElement
    const pwSpan = pwWrapper.nextElementSibling;

    //비밀번호 재확인
    const recheck = document.getElementById("input-text__content-recheck")
    const recheckWrapper = recheck.parentElement
    const recheckSpan = recheckWrapper.nextElementSibling

    //비밀번호 검사 및 재확인
    pw.addEventListener("keyup", function () {
        const regEx = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&+=!])(?!.*\s).{8,16}$/;

        let passWord = pw.value
        if (!regEx.test(passWord)) {
            pwWrapper.classList.add("input-text__content-wrapper--error")
            pwWrapper.classList.remove("input-text__content-wrapper--correct")
            pwSpan.classList.add("input__message--appear")
            pwSpan.innerHTML = "영문, 숫자, 특수문자를 조합11자 이상의 형식으로 입력해 주세요."
        } else {
            pwWrapper.classList.remove("input-text__content-wrapper--error")
            pwWrapper.classList.add("input-text__content-wrapper--correct")
            pwSpan.classList.add("input__message--appear")
            pwSpan.innerHTML = "사용 가능한 비밀번호입니다."
        }
    })
    recheck.addEventListener("keyup", function () {
        let recheckPw = recheck.value

        console.log(recheckSpan)

        if (recheckPw === pw.value) {
            recheckWrapper.classList.remove("input-text__content-wrapper--error")
            recheckWrapper.classList.add("input-text__content-wrapper--correct")
            recheckSpan.classList.add("input__message--appear")
            recheckSpan.innerHTML = "비밀번호가 일치합니다."
        } else {
            recheckWrapper.classList.add("input-text__content-wrapper--error")
            recheckWrapper.classList.remove("input-text__content-wrapper--correct")
            recheckSpan.classList.add("input__message--appear")
            recheckSpan.innerHTML = "비밀번호가 일치하지 않습니다."
        }
    })

    //휴대폰 인증
    const phoneNum = document.querySelector(".phoneNum") //휴대폰번호 입력
    const authNum = document.querySelector(".auth-num") //인증번호 입력

    const sendAuthNum = document.querySelector(".auth-btn")    //인증받기 버튼
    const authCheckBtn = document.querySelector(".auth-check-btn") //인증 확인 버튼
    const timer = document.querySelector(".verification-timer") //타이머


    phoneNum.addEventListener("input", function () {
        const inputValue = phoneNum.value;
        phoneNum.value = inputValue.replace(/[^\d]/g, '');
        let regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/
    })

    sendAuthNum.onclick = function () {
        // 유효시간 설정
        let sec = 180;

        startTimer(sec, time)

        let tel = phoneNum.value;
        sendSms(tel)
    }
    const sendSms = async (phoneNumber) => {
        const response = await fetch("/sms/send", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                phoneNumber
            })
        })
        if (response.ok) {
            console.log("통과")
        } else {
            console.log("에러")
        }
    }

    function startTimer(count, time) {
        let minutes, seconds;
        timer = setInterval(function () {
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

})


// //번호인증 전송 & 번호 중복 확인
// window.addEventListener("load",function (){
//     const authNum = document.querySelector(".sign-up__input-Auth");
//
//     const verification = document.querySelector(".sign-up__verification");
//     const time = document.querySelector(".verification-timer");
//     const verificationBtn = document.querySelector(".sign-up__verification-btn");
//
//     const error = document.querySelector(".phoneError");
//     const phoneNum = document.querySelector(".sign-up__tel");
//     const sendBtn = document.querySelector(".sign-up__tel-btn");
//
//     const signBtn = document.querySelector(".sign-up__btn");
//     let sendNum;
//
//
//
//     //번호를 다 채워야 버튼 눌리게
//     phoneNum.addEventListener("input",function (){
//         const inputValue = this.value;
//         const sanitizedValue = inputValue.replace(/[^\d]/g, '');
//         this.value = sanitizedValue;
//
//
//         if (phoneNum.value.length === 11) {
//             sendBtn.classList.remove("off");
//             sendBtn.removeAttribute("disabled");
//             error.style.visibility = "hidden"
//         } else {
//             sendBtn.classList.add("off");
//         }
//
//         if (inputValue !== sanitizedValue) {
//             error.style.visibility = "visible"
//         }
//
//     })
//
//
//
//
//     let timer = null;
//     let isRunning = false;
//
//     function startTimer(count, time) {
//         let minutes, seconds;
//         timer = setInterval(function() {
//             minutes = parseInt(count / 60, 10);
//             seconds = parseInt(count % 60, 10);
//             minutes = minutes < 10 ? "0" + minutes : minutes;
//             seconds = seconds < 10 ? "0" + seconds : seconds;
//
//             time.textContent = minutes + ":" + seconds;
//
//             if (--count < 0) {
//                 clearInterval(timer);
//                 time.textContent = "시간초과";
//             }
//         }, 1000);
//         isRunning = true;
//     }
//
//
//     sendBtn.onclick = function (){
//         verification.style.visibility = "visible"
//         // 유효시간 설정
//         let sec = 180;
//
//         startTimer(sec,time)
//
//         let tel = phoneNum.value;
//         sendSms(tel);
//     }
//
//     verificationBtn.onclick=function (){
//         let auth = authNum.value;
//         let tel = phoneNum.value;
//
//         sendVerification(auth,tel);
//     }
//
//
//
//     //문자인증
//     const sendVerification = async (verificationNum,toPhone) =>{
//         clearInterval(timer); // 타이머 멈추기
//         const response = await fetch("/sms/verification",{
//             method:"POST",
//             headers:{
//                 "Content-Type":"application/json"
//             },
//             body:JSON.stringify({
//                 verificationNum : verificationNum,
//                 toPhone : toPhone
//             })
//         })
//         if (response.ok){
//             verificationBtn.classList.add("off")
//             verificationBtn.hasAttribute("disable")
//             clearInterval(timer); // 타이머 멈추기
//             time.textContent = "인증완료";
//             signBtn.classList.remove("off");
//             signBtn.classList.add("action");
//             signBtn.removeAttribute("disabled")
//         }else {
//             time.textContent = "인증실패";
//             console.log("Error",response.status,response.statusText)
//         }
//     }
//
//
//
// })
//
//
