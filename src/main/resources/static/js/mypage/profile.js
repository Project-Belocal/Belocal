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


});
// DOM 변수
const container = document.querySelector('.slide__container');
const list = document.querySelector('.slide__list');
const slide = document.querySelectorAll('.slide');
const nextBtn = document.querySelector('.btn__next');
const prevBtn = document.querySelector('.btn__prev');

// slide 구현 변수
const transitionDuration = '0.8s';
const transitionTimingFunction = 'ease';
let currentIndex = 0;
let newIndex = slide.length + 2;
let newWidth = 100 / newIndex;
let intervalId; // 자동 슬라이딩을 위한 interval ID

// 실행, 함수호출
setLayout();
startAutoSlide();

// 복제부를 배치하고 초기값을 설정한다.
function setLayout() {
    // 복제부 영역을 더한 총 길이를 설정
    list.style.width = `${100 * newIndex}%`;
    // 복제부 추가
    let firstClone = slide[0].cloneNode(true);
    let lastClone = slide[slide.length - 1].cloneNode(true);
    list.appendChild(firstClone);
    list.prepend(lastClone);

    const newSlide = document.querySelectorAll('.slide');
    for (value of newSlide) {
        value.style.width = `${newWidth}%`;
    }

    currentIndex = 1;
    list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
}

// 자동 슬라이딩 시작
function startAutoSlide() {
    intervalId = setInterval(() => {
        nextSlide();
    }, 3000); // 3초마다 다음 슬라이드로 이동
}

// 자동 슬라이딩 멈춤
function stopAutoSlide() {
    clearInterval(intervalId);
}

// 다음 슬라이드로 이동
function nextSlide() {
    currentIndex++;
    list.style.transition = `transform ${transitionDuration} ${transitionTimingFunction}`;
    list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
    if (currentIndex >= newIndex - 1) {
        // 마지막 슬라이드에 도달하면
        setTimeout(() => {
            list.style.transition = 'none';
            currentIndex = 1; // 첫 번째 슬라이드로 이동
            list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
        }, parseFloat(transitionDuration) * 1000);
    }
}

// 이전 슬라이드로 이동
function prevSlide() {
    currentIndex--;
    list.style.transition = `transform ${transitionDuration} ${transitionTimingFunction}`;
    list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
    if (currentIndex <= 0) {
        // 첫 번째 슬라이드 이전으로 이동하면
        setTimeout(() => {
            list.style.transition = 'none';
            currentIndex = newIndex - 2; // 마지막 슬라이드로 이동
            list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
        }, parseFloat(transitionDuration) * 1000);
    }
}

// 이전 버튼 클릭 이벤트 처리
prevBtn.addEventListener('click', () => {
    stopAutoSlide(); // 자동 슬라이딩 멈춤
    prevSlide();
    startAutoSlide(); // 자동 슬라이딩 재시작
});

// 다음 버튼 클릭 이벤트 처리
nextBtn.addEventListener('click', () => {
    stopAutoSlide(); // 자동 슬라이딩 멈춤
    nextSlide();
    startAutoSlide(); // 자동 슬라이딩 재시작
});


// // DOM 변수
// const contanier = document.querySelector('.slide__contanier');
// const list = document.querySelector('.slide__list');
// const slide = document.querySelectorAll('.slide');
// const nextBtn = document.querySelector('.btn__next');
// const prevBtn = document.querySelector('.btn__prev');
//
// // slide 구현 변수
// const transitionTime = 200;
// let currentIndex = 0;
// let newIndex = slide.length + 2;
// let newWidth = 100 / newIndex;
//
// // 실행, 함수호출
// setLayout();
//
// // 복제부를 배치하고 초기값을 설정한다.
// function setLayout() {
//     // 복제부 영역을 더한 총 길이를 설정
//     list.style.width = `${100 * newIndex}%`;
//     // 복제부 추가
//     //  Node.cloneNode() 메서드는 이 메서드를 호출한 Node의 복제된 Node를 반환한다
//     //  text node까지 복제
//     let fristclone = slide[0].cloneNode(true);
//     let lastclone = slide[slide.length - 1].cloneNode(true);
//
//     // list 마지막에 복제한 1을 붙이고
//     // list 처음에 복제한 4을 붙인다.
//     list.appendChild(fristclone);
//     list.prepend(lastclone);
//
//     // 복제부를 포함한 모든 슬라이드를 배열에 담고 너비값을 준다.
//     const newSlide = document.querySelectorAll('.slide');
//
//     //100%를 슬라이드 수 만큼 나눈 값
//     for (value of newSlide) {
//         value.style.width = `${newWidth}%`;
//     }
//
//     //복제되어 index 0 이 "4"가 된 상태라 1을 보여주기 위해
//     // 슬라이드 하나만큼 이동함
//     currentIndex = 1;
//     list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
// }
//
// // 이벤트 리스너 등록
// // next btn
// nextBtn.addEventListener('click', () => {
//     // 트렌지션 등록
//     list.style.transition = `${transitionTime}ms`;
//
//     //클릭할때마다 인덱스가 증가
//     currentIndex++;
//     //   console.log(currentIndex);
//
//     // slide 갯수보다 많이 클릭했을 때 체크
//     if (currentIndex == slide.length + 1) {
//         // 복제본으로 간다.
//         list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
//
//         //복제본1로 간다(애니메이션 노출) -> index 1로 리셋 -> 200ms경과
//         // 트랜지션 숨김 -> 1로 이동 (reset() 실행문)
//
//         currentIndex = 1;
//         // 복제본으로 가고 다시 처음으로 가는 초기화 함수.
//         reset();
//     } else {
//         list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
//     }
// });
//
// // prev btn
// prevBtn.addEventListener('click', () => {
//     // 트랜지션 등록
//     list.style.transition = `${transitionTime}ms`;
//     // 인덱스 감소
//     currentIndex--;
//     // 인덱스가 0이하일 떄 (복제한 4에 있을 때)
//     if (currentIndex <= 0) {
//         list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
//
//         //복제본4로 간다(애니메이션 노출) -> index4 로 리셋 -> 200ms경과
//         // 트랜지션 숨김 -> 4로 이동 (reset() 실행문)
//
//         currentIndex = 4;
//         reset();
//     } else {
//         list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
//     }
// });
// function reset() {
//     setTimeout(() => {
//         list.style.transition = `none`;
//         list.style.transform = `translateX(-${newWidth * currentIndex}%)`;
//     }, transitionTime);
// }