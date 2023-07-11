window.addEventListener("load", () => {
    const chatRequestBtn = document.querySelector(".theme__detail__chat-request");

    chatRequestBtn.addEventListener("click", function(e) {
        if(chatRequestBtn.classList.contains("not-logined"))
            return;

        e.preventDefault();
        
        const {travelThemeId, travelerId, guideId} = chatRequestBtn.dataset;
        const chatRoom = {
            travelThemeId,
            travelerId,
            guideId
        };

        fetch("/api/chats/")

    })
})