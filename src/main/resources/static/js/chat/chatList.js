// //채팅 목록 조회
// function chatListLoad(url){
//   const chatList = document.querySelector(".message__item");
//
//   fetch(url)
//       .then(response=>response.json())
//       .then(list=>{
//           chatList.innerHTML = "";
//         let mid = document.querySelector("#input-member-id").value;
//
//         for (let chat of list){
//
//           let itemTemplate = `<div className="message__box">
//             <a className="message__chat-room"></a>
//             <div className="message__chat">
//               <div className="message__profile">
//                 <img src="../images/${mid.img}" alt="">
//               </div>
//               <div className="message__text">
//                 <div className="message__user-name">
//                   <span>유저이름임ㅋㅋ8910ㅋㅋㅋ</span>
//                 </div>
//                 <div className="message__content">
//                   <span>알수가없음 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋzzzzzzzzzzwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwㅇㅂㅈddddddddddddddddddddddddddddddddddddddddㅇㅂㅈㅇㅂㅈㅇㅂㅈㅇㅂㅈㅇㅂㅈㅇㅂㅈㅈㅂㅇㅂㅈㅇㅂㅈㅇㅂㅈㅇㅈㅂㅇㅈㅂㅂㅈㅇ</span>
//                 </div>
//                 <div className="message__date">
//                   <span>2022년 10월 1일</span>
//                 </div>
//               </div>
//               <div className="message__notification">
//                 <p>8</p>
//               </div>
//             </div>
//           </div>`;
//
//           chatList.insertAdjacentHTML("beforebegin",itemTemplate);
//         }
//       })
// }
//
// //알림 목록 조회
// function noticeListLoad(url){
//   const notice = document.querySelector(".message__notice");
//   fetch(url)
//       .then(response=>response.json())
//       .then(list=>{
//         notice.innerHTML = "";
//
//       })
// }


window.addEventListener("load", function() {
  // tab1과 tab2 버튼 요소를 가져옵니다.
  const tab1Button = document.querySelector('.message__tab');
  const tab2Button = document.querySelectorAll('.message__tab')[1];
  // message__box와 message__notice-wrap 요소를 가져옵니다.
  const messageBox = document.querySelector('.message__box');
  const noticeWrap = document.querySelector('.message__notice-wrap');


    chatListLoad("http://localhost:8080/api/chats")
  // 페이지 로드 시 처리
  if (messageBox) {
    document.querySelector('.message__item').classList.remove('hidden');
    document.querySelector('.message__empty').classList.add('hidden');
  } else {
    document.querySelector('.message__empty').classList.remove('hidden');
  }

  // tab1 버튼 클릭 시 처리
  tab1Button.addEventListener('click', function() {
    chatListLoad("http://localhost:8080/api/chats")
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