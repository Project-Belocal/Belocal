
//아이디 찾기
window.addEventListener("load",function (){
    const undo = document.querySelector(".find__inner button")

    const findId = document.querySelector(".input__id");
    const findIdBtn = document.querySelector(".find__id-btn");
    const inputArea = document.querySelector(".find__area");
    const resultInfo = document.querySelector(".find__user-info");
    const resultId = document.querySelector(".find__info-txt");


    undo.onclick = function (){
        history.go(-1);
    }


    findIdBtn.onclick = function () {
        let phoneNum = findId.value;

        findIdCheck(phoneNum);
    }

    const findIdCheck = async(phoneNum) =>{
        let response = await fetch("/login/find-id/check", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    phoneNum
                })
            })
        if (response.ok){
            const data = await response.json();
            let userId = data.userId;
            resultInfo.classList.remove("hidden");
            inputArea.classList.add("hidden");
            resultId.innerHTML = userId;
        }else {
            console.log("실패")
        }
    }
})
