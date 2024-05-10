window.addEventListener("load",function(){

        const section = document.querySelector(".room");
        const Menu = section.querySelector(".room__menu");
        const Nav = section.querySelector(".room__nav");
        const Undo = section.querySelector(".room__undo");
        const reportBtn = section.querySelector(".room__report-btn");
        const modalBg = section.querySelector(".room__modal-bg");
        const reportModal = section.querySelector(".room__report");
        const reportClose = section.querySelector(".room__report-close");

        const exitBtn = document.querySelector(".exit");

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


    exitBtn.onclick = function (){
        console.log("흠")
        fetch("/api/chatRooms/exit",{
            method:"POST",
            headers:{
                "Content-Type":"application/JSON"
            },
            body:JSON.stringify({
                chatRoomId:chatRoomId
            })
        }).then(response=>response.json()).then(data=>{
            console.log(data)
        })
    }



    messageArea.scrollTop = messageArea.scrollHeight;

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

stompClient.heartbeat.incoming = 0;
stompClient.heartbeat.outgoing = 0;




function onConnected() {
    // sub 할 url => /sub/chat/room/roomId 로 구독한다
    stompClient.subscribe('/sub/chat/room/' + chatRoomId, onMessageReceived);

    //소켓에 연결한다면? -> 채팅메세지의 id가 내꺼가 아닌 데이터들의 checked를 1로 변경
    fetch("/api/chats/check", {
        method: "POST",
        headers: {
            "Content-Type": "application/JSON"
        },
        body: JSON.stringify({
            roomId: chatRoomId,
            id: memberId.value
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log(data)
        })

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
        messageInput.focus();
    }
    event.preventDefault();
}

// 메시지를 받을 때도 마찬가지로 JSON 타입으로 받으며,
// 넘어온 JSON 형식의 메시지를 parse 해서 사용한다.
function onMessageReceived(payload) {
    // console.log("payload 들어오냐? :"+payload);
    let payloadData = JSON.parse(payload.body);
    let chat = payloadData.chat;
    let uuid = payloadData.uuid;
    let id = chat.memberId;

    let chatItem = document.createElement("div");
    let imgItme = document.createElement("button");

    let timeSpan = document.createElement("span");
    let messageParagraph = document.createElement("p");
    let contentDiv = document.createElement("div");
    let profileImg = document.createElement("img");


    if (parseInt(memberId.value) === id) {
        chatItem.classList.add("room__item", "my-msg");
    } else {
        chatItem.classList.add("room__item");
        chatItem.appendChild(imgItme);
        imgItme.classList.add("room__user-img");
        imgItme.appendChild(profileImg);
        profileImg.src = "https://storage.googleapis.com/belocal-bucket/"+uuid
    }


    timeSpan.classList.add("room__time");
    timeSpan.textContent = chat.regDate;

    messageParagraph.classList.add("room__msg");
    messageParagraph.textContent = chat.message;

    contentDiv.classList.add("room__content");

    if (parseInt(memberId.value) === id) {
        contentDiv.appendChild(timeSpan);
        contentDiv.appendChild(messageParagraph);
    } else {
        contentDiv.appendChild(messageParagraph);
        contentDiv.appendChild(timeSpan);
    }



    chatItem.appendChild(contentDiv);

    messageArea.appendChild(chatItem);

    //메세지 입력시 스크롤 위치 맨아래로
    messageArea.scrollTop = messageArea.scrollHeight;
}
    messageForm.addEventListener('submit', sendMessage, true)


window.addEventListener("keypress",function (e){
    const enterBtn = document.querySelector(".room__input-btn");
    if (e.code==="Enter"){
        e.preventDefault()
        enterBtn.click();
        return;
    }
})