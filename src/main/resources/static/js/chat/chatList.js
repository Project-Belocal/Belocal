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


  let temp = `      <div class="message__notice-wrap">
                        <span>엄준식12세 님이 요청을 수락했습니다.</span>
                        <span>2023-05-12 17:30</span>
                    </div>`

  const eventSource = new EventSource("/sse");
  eventSource.onmessage = function (e){
  const p = document.createElement("p");
  p.innerHTML = e.data;

  document.querySelector(".data").insertAdjacentHTML("afterend", temp);
}

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