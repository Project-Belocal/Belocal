window.addEventListener("load",function (){
    const memberId = document.querySelector(".member__id");

    //닉네임 체크
    const inputNickname = document.querySelector(".nickname");
    const nickError = document.querySelector(".nickError");

    //현재 비밀번호
    const inputPw = document.querySelector(".current-pw");
    const pwError = document.querySelector(".pwError");

    //새 비밀번호
    const newPw = document.querySelector(".new-Pw");
    const newPwCheck = document.querySelector(".new-Pw-check")
    const newError = document.querySelector(".newPwError");
    const reconfirmError = document.querySelector(".pwCheckError")

    //휴대폰 번호
    const phoneNum = document.querySelector(".edit__tel");
    const sendBtn = document.querySelector(".edit__tel-btn");
    const phoneError = document.querySelector(".phoneError");
    const verification = document.querySelector(".edit__verification");
    const verificationBtn = document.querySelector(".edit__verification-btn");
    const time = document.querySelector(".verification-timer");
    const authNum = document.querySelector(".edit__input-Auth");

    //이미지 변경버튼
    const editImg = document.querySelector(".edit-btn");
    const fileInput = document.querySelector(".file-input");
    const profileImg = document.querySelector(".edit__profile-img");


    let nicknameCheck = false;
    let currentPwCheck = false;
    let id = memberId.value;


    //닉네임 중복 체크
    inputNickname.addEventListener("keyup",function (){

        let nickname = inputNickname.value;

        fetch("api/mys/duplicateNickname",{
            method:"POST",
            headers:{
                "Content-Type": "application/json"
            },
            body:JSON.stringify({
                nickname
            })
        })
        .then(function (response){
            if (response.status === 200)
                return response.json();
             else
                throw new Error("Request failed with status"+response.status)
        }).then(function (data){
            if (data === 1){
                nicknameCheck = false;
                nickError.textContent = "이미 존재하는 닉네임입니다.";
                nickError.style.color = "red";
                nickError.classList.remove("hidden")
            }else {
                nicknameCheck = true;
                nickError.textContent = "사용 가능한 닉네임입니다.";
                nickError.style.color = "#0D6EFD";
                nickError.classList.remove("hidden");
            }
        }).catch(function (error){
            console.error("Error:", error);
        })
    })

    //현재 비밀번호 일치 확인
    inputPw.addEventListener("keyup",function (){


        let pw = inputPw.value;

        fetch("api/mys/currentPw",{
            method:"POST",
            headers:{
                "Content-Type": "application/json"
            },
            body:JSON.stringify({
                memberId : id,
                pw : pw
            })
        }).then(function (response){
            if (response.status===200)
                return response.json();
            else
                throw new Error("Request failed with status"+response.status)
        }).then(function (data){
            if (data===1){
                currentPwCheck = true;
                pwError.textContent = "비밀번호가 일치합니다."
                pwError.style.color ="#0D6EFD"
                pwError.classList.remove("hidden")
                newPw.removeAttribute("disabled")
                newPwCheck.removeAttribute("disabled")
            }else {
                currentPwCheck = false;
                pwError.textContent = "비밀번호가 일치하지 않습니다.."
                pwError.style.color ="red"
                pwError.classList.remove("hidden")
                newPw.hasAttribute("disabled")
                newPwCheck.hasAttribute("disabled")
            }
        })
    })

    //비밀번호 정규식
    newPw.addEventListener("keyup",function (){
        const regEx = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@#$%^&+=!])(?!.*\s).{8,16}$/;

        if (!regEx.test(newPw.value)){
            newError.textContent = "영문, 숫자, 특수문자를 조합8~16자를 입력해주세요.";
            newError.style.color = "red";
            newError.classList.remove("hidden");
        }else {
            newError.innerHTML = "사용가능한 비밀번호 입니다."
            newError.style.color ="#0D6EFD";
            newError.classList.remove("hidden");
        }
    })

    //비밀번호 일치 확인
    newPwCheck.addEventListener("keyup",function (){
        if (newPw.value != newPwCheck.value){
            reconfirmError.textContent = "비밀번호가 일치하지 않습니다.";
            reconfirmError.style.color = "red";
            reconfirmError.classList.remove("hidden");
        }else {
            reconfirmError.textContent ="비밀번호가 일치합니다."
            reconfirmError.style.color ="#0D6EFD";
            reconfirmError.classList.remove("hidden");
        }
    })

    //번호인증 전송 & 번호 중복 확인
    phoneNum.addEventListener("input",function (){
        const inputValue = this.value;
        const sanitizedValue = inputValue.replace(/[^\d]/g, '');
        this.value = sanitizedValue;

        if (phoneNum.value.length === 11) {
            sendBtn.classList.remove("off");
            sendBtn.removeAttribute("disabled");
            phoneError.style.visibility = "hidden"
        } else {
            sendBtn.classList.add("off");
        }

        if (inputValue !== sanitizedValue) {
            phoneError.style.visibility = "visible"
        }

    })

    //타이머
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
        console.log(tel)

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
        }else {
            time.textContent = "인증실패";
            console.log("Error",response.status,response.statusText)
        }
    }



    //프로필 파일 업로드
    editImg.addEventListener("click", function() {
        fileInput.click();
    });

    let regex = new RegExp("(.*?)\.(jpg|png)$");
    let maxSize = 1048576; //1MB
    function fileCheck(fileName,fileSize){
        if (fileSize>=maxSize)
            return false;

        if (!regex.test(fileName)){
            return false;
        }

        return true;
    }

    fileInput.addEventListener("change",function (e){
        let formData = new FormData();
        let upFile = document.querySelector('input[name="uploadFile"]');
        let fileList = upFile.files;
        let fileObj = fileList[0];

        if (!fileCheck(fileObj.name,fileObj.size)){
            return false;
        }

        let imgFile = fileObj;
        let imgReader = new FileReader();

        imgReader.onload = function (event){
            profileImg.src = event.target.result;
            console.log(event.target.result)
        }

        imgReader.readAsDataURL(imgFile)

        //저장버튼이 눌려야 작동되어야 하는 부분
        // formData.append("uploadFile",fileObj);
        //
        // fetch("api/mys/profileUpload",{
        //     method:"POST",
        //     body:formData
        // })
        //     .then(response=>response.json())
        //     .then(data => {
        //         console.log(data)
        //     })
    })

})