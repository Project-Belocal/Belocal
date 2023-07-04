window.addEventListener("load",function(){
    {
        const section = document.querySelector(".room");
        const Menu = section.querySelector(".room__menu");
        const Nav = section.querySelector(".room__nav");
        const Undo = section.querySelector(".room__undo");
        const reportBtn = section.querySelector(".room__report-btn");
        const modalBg = section.querySelector(".room__modal-bg");
        const reportModal = section.querySelector(".room__report");
        const reportClose = section.querySelector(".room__report-close");


        //뒤로가기
        Undo.onclick = function () {
            history.go(-1);
        }

        // 모달창 영역 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        Menu.onclick = function () {
            modalBg.classList.toggle("hidden");
            Nav.classList.toggle("hidden");
        }

        reportBtn.onclick = function () {
            reportModal.classList.toggle("hidden");
            Nav.classList.toggle("hidden");
        }

        reportClose.onclick = function () {
            reportModal.classList.toggle("hidden");
            modalBg.classList.toggle("hidden");
        }

        modalBg.onclick = function (e) {
            if (e.target === modalBg) {
                modalBg.classList.add("hidden")
                Nav.classList.add("hidden");
                reportModal.classList.add("hidden");
            }
        }
    }
    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
})

// const usernameForm = document.querySelector('#usernameForm');
const messageInput = document.querySelector('#message');
const messageArea = document.querySelector('#messageArea');
const messageForm = document.querySelector('#messageForm');
const memberId = document.querySelector(".member-id");

// const connectingElement = document.querySelector('.connecting');

const url = new URL(location.href);
const chatRoomId = url.searchParams.get('id');
let stompClient = null;

let socket = new SockJS('/ws-stomp');
stompClient = Stomp.over(socket);
stompClient.connect({}, onConnected);

function onConnected() {
    // sub 할 url => /sub/chat/room/roomId 로 구독한다
    stompClient.subscribe('/sub/chat/room/' + chatRoomId, onMessageReceived);
}


// 메시지 전송때는 JSON 형식을 메시지를 전달한다.
function sendMessage(event) {
    let messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        let chatMessage = {
            chatRoomId: chatRoomId,
            memberId:memberId.value,
            message: messageInput.value
        };

        stompClient.send("/pub/chat/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

// 메시지를 받을 때도 마찬가지로 JSON 타입으로 받으며,
// 넘어온 JSON 형식의 메시지를 parse 해서 사용한다.
function onMessageReceived(payload) {
    // console.log("payload 들어오냐? :"+payload);
    let chat = JSON.parse(payload.body);
    let id = chat.memberId;
    memberId.value;

    let temp = ``

    messageArea.innerHTML = temp;
    // let messageElement = document.createElement('li');
    //
    // messageElement.classList.add('chat-message');
    // let textElement = document.createElement('p');
    // let messageText = document.createTextNode(chat.message);
    //
    // textElement.appendChild(messageText);
    //
    // messageElement.appendChild(textElement);
    //
    // messageArea.appendChild(messageElement);
    // messageArea.scrollTop = messageArea.scrollHeight;
}
    messageForm.addEventListener('submit', sendMessage, true)
