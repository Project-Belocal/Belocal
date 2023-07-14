window.addEventListener('load', function () {
    const dealList = document.querySelector('.my__deal-list');
    const modalBg = document.querySelector('.modal-bg');
    const modalClose = document.querySelector('.modal__close');
    const roleBtn = document.querySelector(".local__btn")
    const memberId = document.querySelector(".member__id");

    const role = document.querySelector(".role__wrap");
    const info = document.querySelector(".my__info");
    const nicknameElement = document.querySelector(".my__name span");
    let nickname = nicknameElement.textContent;



    let myTemp = `
                <div sec:authorize="hasRole('GUIDE')">
                <div class="my__register-btn ">
                    <a href="/my/theme-register">theme 등록하러가기</a>
                </div>
                <div class="my__register-btn ">
                    <a href="#">Post 등록하러가기</a>
                </div>
            </div>
    `
    let infoTemp = `
                    <div class="my__name">
                        <span>${nickname}</span>
                    </div>
                    <div class="my__local"
                         sec:authorize="hasRole('GUIDE')">
                        <img src="../images/logo-beLocal.svg" alt="">
                        <span>local</span>
                    </div>`

    
    roleBtn.addEventListener("click",function (e){
        e.preventDefault();

        fetch("my/api/mys/addGuideRole",{
            method:"POST",
            headers:{
                "Content-Type":"application/JSON"
            },
            body:JSON.stringify({
                id:memberId.value,
            })
        }).then(response=>response.json())
            .then(data=>{
                if (data===200){
                    role.innerHTML = myTemp;
                    info.innerHTML = infoTemp;
                }
            })
    })
    
    
    
    dealList.onclick = function (e) {
        let clickedButton = e.target.closest('button');
        if (!clickedButton)
            return;

        let isItem1 = clickedButton.classList.contains('item1');
        let validItem = isItem1

        if (!validItem) {
            return;
        }

        if (isItem1) {
            // 모달창에 요청사항 내용을 출력
            modalBg.classList.toggle('hidden');
        }
    };

    modalBg.onclick = function (e) {
        if (e.target === modalBg) {
            modalBg.classList.toggle('hidden');
        }
    };

    modalClose.onclick = function () {
        modalBg.classList.toggle('hidden');
    };


    let banner = document.querySelector(".banner");
    let bannerWrap = document.querySelector(".banner__wrap");
    let bannerItems = bannerWrap.querySelectorAll("li");

    let bannerWidth = bannerItems[0].offsetWidth; // 이미지의 폭
    let bannerHeight = bannerItems[0].offsetHeight; // 높이
    let length = bannerItems.length; // 이미지의 갯수
    let rollingId;

    // 정해진 초마다 함수 실행
    rollingId = setInterval(function() {
        rollingStart();
    }, 3000); // 다음 이미지로 롤링 애니메이션 할 시간차

    function rollingStart() {
        bannerWrap.style.width = bannerWidth  + "px";
        bannerWrap.style.height = bannerHeight + "px";

        // 배너의 좌측 위치를 옮겨 준다.
        bannerWrap.animate([{ left: 0 }, { left: -bannerWidth }], {
            duration: 2000, // 롤링 진행되는 시간
            fill: "forwards",
            easing: "ease-out"
        }).onfinish = function() {
            // 첫 번째 이미지를 마지막 끝에 복사(이동이 아니라 복사)해서 추가한다.
            bannerWrap.innerHTML += "<li>" + bannerWrap.querySelector("li:first-child").innerHTML + "</li>";
            // 뒤로 복사된 첫 번째 이미지는 필요 없으니 삭제한다.
            bannerWrap.querySelector("li:first-child").remove();
            // 다음 움직임을 위해서 배너 좌측의 위치값을 초기화한다.
            bannerWrap.style.left = 0;
            // 이 과정을 반복하면서 계속 롤링하는 배너를 만들 수 있다.
        };
    }

});
