//비밀번호 찾기
window.addEventListener("load",function (){
    const undo = document.querySelector(".find__inner")

    const inputId = document.querySelector(".input__id")
    const inputPhone = document.querySelector(".input__phone")
    const findPwBtn = document.querySelector(".find__pw-btn");

    const inputArea = document.querySelector(".find__area");
    const result = document.querySelector(".find__help");

    undo.onclick = function (){
        history.go(-1);
    }


    findPwBtn.onclick = function (){
        let userId = inputId.value;
        let toPhone = inputPhone.value;

        findPwCheck(userId,toPhone);
    }

    const findPwCheck = async(userId,toPhone) =>{
        let response = await fetch("/login/find-pw/check",{
            method:"POST",
            headers:{
                "Content-Type": "application/json"
            },
            body:JSON.stringify({
                userId,
                toPhone
            })
        })
        if (response.ok){
            inputArea.classList.add("hidden")
            result.classList.remove("hidden")
        }else {
            console.log("실패")
        }

    }

})