const tabs = document.querySelectorAll('.tab');
const tabContent = document.querySelectorAll('.tab-panel');
const tabContent2 = document.querySelectorAll('.tab-panel2');

tabs.forEach((tab) => {
    tab.addEventListener('click', () => {
        const target = document.querySelector(tab.dataset.target);


        tabContent2.forEach((tc) => {
            tc.classList.remove('is-active');
        });
        target.classList.add('is-active');

        tabContent.forEach((tc) => {
            tc.classList.remove('is-active');
        });
        target.classList.add('is-active');


        tabs.forEach((t) => {
            t.classList.remove('is-active');
        });
        tab.classList.add('is-active');
    });
});


//==== 페이지 스크롤(6개씩 불러오기) =============================================
// 1. 현재, 기본으로 6개를 불러오도록 되어있음(service, repository에서
// 2. 스크롤이 화면(.infinite)의 끝에 닿으면 6개를 더 불러오도록 해야 함
// 3. infinite의 세로길이 : 2710px 임
// 4. 등차수열 ' 1+(i-1)1 ' 로 불러와야 할 텐데...... 0~5(6개), 6~11(6개)
// 5. 스크롤이 바닥에 한번 닿을 때 마다 offset++ 되고 그 값을 controller에 전달해야 함
// ** new IntersectionObserver() 활용해 보

// const infinite = document.querySelector('.infinite');
// const list = document.querySelector('.list');
// const tw = document.querySelector('.tabs-wrapper');


let offset = 0;
let batchSize = 6;

window.addEventListener('scroll', function() {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop
    let windowHeight = document.documentElement.clientHeight;

    if(scrollTop + windowHeight >= documentHeight) {
        fetchData(offset, batchSize);
        offset += batchSize;
   }
});










