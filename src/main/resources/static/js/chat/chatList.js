window.addEventListener("load", function() {
  // tab1과 tab2 버튼 요소를 가져옵니다.
  const tab1Button = document.querySelector('.message__tab');
  const tab2Button = document.querySelectorAll('.message__tab')[1];
  // message__box와 message__notice-wrap 요소를 가져옵니다.
  const messageBox = document.querySelector('.message__box');

  const notice = document.querySelector(".message__notice");
  const noticeWrap = document.querySelector('.message__notice-wrap');


  const memberId = document.querySelector(".member-id");
  let id = memberId.value;

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

  // tab2 알림버튼 클릭 시 처리
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


  notice.addEventListener("click",function (e){

    if (e.target.className.includes('reject-btn')){
      reject(e);
      let dom = e.target.closest(".message__notice-wrap")
      dom.classList.add("hidden")
    }
    if (e.target.className.includes('accept-btn')){
      accept(e);
      let dom = e.target.closest(".message__notice-wrap")
      dom.classList.add("hidden")
    }

  })

  function reject(e){
    let action = document.querySelector(".action-wrap");
    let chatRoomId = action.getAttribute("data-chat-room-id");
    let senderId = action.getAttribute("data-sender-id");
    e.preventDefault();

    fetch("/api/notices/request", {
      method: "POST",
      headers: {
        "Content-Type": "application/JSON"
      },
      body: JSON.stringify({
        status: "reject",
        roomId: chatRoomId,
        senderId
      })
    }).then(response => response.status)
        .then(data => {
          console.log(data);
        })
  }
  function accept(e){
    let action = document.querySelector(".action-wrap");
    let chatRoomId = action.getAttribute("data-chat-room-id");
    let senderId = action.getAttribute("data-sender-id");
    e.preventDefault();

    fetch("/api/notices/request",{
      method:"POST",
      headers:{
        "Content-Type":"application/JSON"
      },
      body:JSON.stringify({
        status:"accept",
        roomId:chatRoomId,
        senderId
      })
    }).then(response=>response.status)
        .then(data=>{
          console.log(data);
        })
  }


  // rejectBtn.onclick = function (e) {
  //   e.preventDefault();
  //
  //   fetch("/api/notices/request", {
  //     method: "POST",
  //     headers: {
  //       "Content-Type": "application/JSON"
  //     },
  //     body: JSON.stringify({
  //       status: "reject",
  //       roomId: chatRoomId,
  //       senderId
  //     })
  //   }).then(response => response.status)
  //       .then(data => {
  //         console.log(data);
  //       })
  // }
});

// window.addEventListener("load",function (){
//   const memberId = document.querySelector(".member-id");
//   let id = memberId.value;
//
//   fetch("api/chats/list",{
//     method:"POST",
//     headers:{
//       "Content-Type":"application/JSON"
//     },
//     body:JSON.stringify({
//       id
//     })
//   })
//       .then(response => response.json())
//       .then(data=>{
//         console.log(data)
//       })
// })