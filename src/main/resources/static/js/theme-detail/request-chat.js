window.addEventListener("load", () => {
    const chatRequestBtn = document.querySelector(".theme__detail__chat-request");

    chatRequestBtn.addEventListener("click", function(e) {
        if(chatRequestBtn.classList.contains("not-logined"))
            return;

        if(chatRequestBtn.classList.contains("accepted"))
            return;

        if(chatRequestBtn.querySelector("a").classList.contains("isMine"))
            return;
            
        e.preventDefault();

        if(chatRequestBtn.classList.contains("already-sent"))
            return;

        (async () => {
            const {travelThemeId, travelerId, guideId} = chatRequestBtn.dataset;
            const chatRoom = {
                travelThemeId,
                travelerId,
                guideId
            };

            let result = null;
            {
                let response = await fetch("/api/chatRooms", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(chatRoom)
                })
                
                result = await response.json();
            }

            if(result) {
                let chatRoomId = result.id;
                let type = 0;

                const notice = {
                    chatRoomId,
                    senderId: travelerId,
                    receiverId: guideId,
                    type
                };

                let response = await fetch("/api/notices", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(notice)
                })

                result = await response.json();
            }

            if(result) {
                chatRequestBtn.classList.add("already-sent");

                let textArea = chatRequestBtn.querySelector("a");
                textArea.innerHTML = "요청 완료";
            }

        }) ();

    })
})