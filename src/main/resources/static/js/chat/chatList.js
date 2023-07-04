window.addEventListener("load", function() {
  // tab1과 tab2 버튼 요소를 가져옵니다.
  const tab1Button = document.querySelector('.message__tab');
  const tab2Button = document.querySelectorAll('.message__tab')[1];
  // message__box와 message__notice-wrap 요소를 가져옵니다.
  const messageBox = document.querySelector('.message__box');
  const noticeWrap = document.querySelector('.message__notice-wrap');


  // chatListLoad("http://localhost:8080/api/chats")
  // 페이지 로드 시 처리
  if (messageBox) {
    document.querySelector('.message__item').classList.remove('hidden');
    document.querySelector('.message__empty').classList.add('hidden');
  } else {
    document.querySelector('.message__empty').classList.remove('hidden');
  }

  // tab1 버튼 클릭 시 처리
  tab1Button.addEventListener('click', function() {
    if (messageBox) {
      document.querySelector('.message__item').classList.remove('hidden');
      document.querySelector('.message__empty').classList.add('hidden');
    } else {
      document.querySelector('.message__empty').classList.remove('hidden');
    }

    if (noticeWrap) {
      document.querySelector('.message__notice').classList.add('hidden');
    }

    tab1Button.classList.add('focus');
    tab2Button.classList.remove('focus');
  });

  // tab2 버튼 클릭 시 처리
  tab2Button.addEventListener('click', function() {
    if (noticeWrap) {
      document.querySelector('.message__notice').classList.remove('hidden');
      document.querySelector('.message__empty').classList.add('hidden');
    } else {
      document.querySelector('.message__empty').classList.remove('hidden');
    }

    if (messageBox) {
      document.querySelector('.message__item').classList.add('hidden');
    }

    tab2Button.classList.add('focus');
    tab1Button.classList.remove('focus');
  });


});