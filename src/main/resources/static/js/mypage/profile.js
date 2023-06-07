window.addEventListener('load', function () {
    const dealList = document.querySelector('.my__deal-list');
    const modalBg = document.querySelector('.modal-bg');
    const modalClose = document.querySelector('.modal__close');

    dealList.onclick = function (e) {
        let clickedButton = e.target.closest('button');
        if (!clickedButton) {
            return;
        }

        let isItem1 = clickedButton.classList.contains('item1');
        let isItem2 = clickedButton.classList.contains('item2');
        let isItem3 = clickedButton.classList.contains('item3');
        let validItem = isItem1 || isItem2 || isItem3;

        if (!validItem) {
            return;
        }

        if (isItem1) {
            modalBg.classList.toggle('hidden');
            console.log("item1 : 요청사항 내용을 출력합니다.");
            // 모달창에 요청사항 내용을 출력
        } else if (isItem2) {
            modalBg.classList.toggle('hidden');
            console.log('item2 : 예약사항 내용을 출력합니다.');
            // 모달창에 예약사항 내용을 출력
        } else if (isItem3) {
            modalBg.classList.toggle('hidden');
            console.log('item3 : 완료사항 내용을 출력합니다.');
            // 모달창에 완료사항 내용을 출력
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
});
