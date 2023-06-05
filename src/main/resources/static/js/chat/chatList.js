      // window.addEventListener("load", () => {
      //   const section = document.querySelector("#message");
      //   const tabs = section.querySelectorAll(".message__tab");
      //   const items = section.querySelectorAll(".message__item");
      //   const notices = section.querySelectorAll(".message__notice");
      //   const empty = section.querySelector(".message__empty");
      
      //   tabs.forEach((tab, index) => {
      //     tab.addEventListener("click", () => {
      //       // 모든 탭의 포커스 제거
      //       tabs.forEach(tab => tab.classList.remove("focus"));
            
      //       // 클릭한 탭에 포커스 추가
      //       tab.classList.add("focus");
      
      //       // 모든 아이템 및 알림 숨김
      //       console.log(items.classList.add("hidden"));
      //       items.forEach(item =>  console.log(item));
      //       items.forEach(item => item.classList.add("hidden"));
      //       notices.forEach(notice => notice.classList.add("hidden"));
      //       empty.classList.add("hidden");
      
      //       // 클릭한 탭에 해당하는 아이템 또는 알림 보여주기
      //       if (index === 0) {
      //         // if (items.querySelector(".message__box")) {
      //         //   empty.classList.remove("hidden");
      //         // }
      //         items.forEach(item => item.classList.remove("hidden"));
      //       } else if (index === 1) {
      //         notices.forEach(notice => notice.classList.remove("hidden"));
      //       }
      //     });
      //   });
      // });




      // window.addEventListener("load",function(){
      //   // tab1과 tab2 버튼 요소를 가져옵니다.
      //   const tab1Button = document.querySelector('.message__tab');
      //   const tab2Button = document.querySelectorAll('.message__tab')[1];

      //   // message__box와 message__notice-wrap 요소를 가져옵니다.
      //   const messageBox = document.querySelector('.message__box');
      //   const noticeWrap = document.querySelector('.message__notice-wrap');

      //   // tab1 버튼 클릭 시 처리
      //   tab1Button.addEventListener('click', function() {
      //     // message__box가 존재할 경우 message__item을 보여주고 message__empty를 숨깁니다.
      //     if (messageBox) {
      //       document.querySelector('.message__item').classList.remove('hidden');
      //       document.querySelector('.message__empty').classList.add('hidden');
      //     } else {
      //       // message__box가 존재하지 않을 경우 message__empty를 보여줍니다.
      //       document.querySelector('.message__empty').classList.remove('hidden');
      //     }

      //     // message__notice-wrap이 존재할 경우 message__notice를 숨깁니다.
      //     if (noticeWrap) {
      //       document.querySelector('.message__notice').classList.add('hidden');
      //     }
      //   });

      //   // tab2 버튼 클릭 시 처리
      //   tab2Button.addEventListener('click', function() {
      //     // message__notice-wrap이 존재할 경우 message__notice를 보여주고 message__empty를 숨깁니다.
      //     if (noticeWrap) {
      //       document.querySelector('.message__notice').classList.remove('hidden');
      //       document.querySelector('.message__empty').classList.add('hidden');
      //     } else {
      //       // message__notice-wrap이 존재하지 않을 경우 message__empty를 보여줍니다.
      //       document.querySelector('.message__empty').classList.remove('hidden');
      //     }

      //     // message__box가 존재할 경우 message__item을 숨깁니다.
      //     if (messageBox) {
      //       document.querySelector('.message__item').classList.add('hidden');
      //     }
      //   });
      // })


//       window.addEventListener("DOMContentLoaded",function(){
//         // tab1과 tab2 버튼 요소를 가져옵니다.
// const tab1Button = document.querySelector('.message__tab');
// const tab2Button = document.querySelectorAll('.message__tab')[1];

// // message__box와 message__notice-wrap 요소를 가져옵니다.
// const messageBox = document.querySelector('.message__box');
// const noticeWrap = document.querySelector('.message__notice-wrap');

// // tab1 버튼 클릭 시 처리
// tab1Button.addEventListener('click', function() {
//   // message__box가 존재할 경우 message__item을 보여주고 message__empty를 숨깁니다.
//   if (messageBox) {
//     document.querySelector('.message__item').classList.remove('hidden');
//     document.querySelector('.message__empty').classList.add('hidden');
//   } else {
//     // message__box가 존재하지 않을 경우 message__empty를 보여줍니다.
//     document.querySelector('.message__empty').classList.remove('hidden');
//   }

//   // message__notice-wrap이 존재할 경우 message__notice를 숨깁니다.
//   if (noticeWrap) {
//     document.querySelector('.message__notice').classList.add('hidden');
//   }

//   // tab1에 focus를 설정합니다.
//   tab1Button.classList.add('focus');
//   tab2Button.classList.remove('focus');
// });

// // tab2 버튼 클릭 시 처리
// tab2Button.addEventListener('click', function() {
//   // message__notice-wrap이 존재할 경우 message__notice를 보여주고 message__empty를 숨깁니다.
//   if (noticeWrap) {
//     document.querySelector('.message__notice').classList.remove('hidden');
//     document.querySelector('.message__empty').classList.add('hidden');
//   } else {
//     // message__notice-wrap이 존재하지 않을 경우 message__empty를 보여줍니다.
//     document.querySelector('.message__empty').classList.remove('hidden');
//   }

//   // message__box가 존재할 경우 message__item을 숨깁니다.
//   if (messageBox) {
//     document.querySelector('.message__item').classList.add('hidden');
//   }

//   // tab2에 focus를 설정합니다.
//   tab2Button.classList.add('focus');
//   tab1Button.classList.remove('focus');
// });
//       })
window.addEventListener("load", function() {
  // tab1과 tab2 버튼 요소를 가져옵니다.
  const tab1Button = document.querySelector('.message__tab');
  const tab2Button = document.querySelectorAll('.message__tab')[1];
  // message__box와 message__notice-wrap 요소를 가져옵니다.
  const messageBox = document.querySelector('.message__box');
  const noticeWrap = document.querySelector('.message__notice-wrap');


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