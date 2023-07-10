window.addEventListener("load", () => {
    const body = document.body;
    const wishlistBtn = document.querySelector(".theme__cotents--text--status").querySelector("i");
    const wishlistModal = document.querySelector(".wishlist__modal");
    const modalCloseBtn = wishlistModal.querySelector(".wishlist__modal__close");
    const modalHeader = wishlistModal.querySelector(".wishlist__modal__header");
    const modalBody = wishlistModal.querySelector(".wishlist__modal__body");
    const wishlistCreateBtn = wishlistModal.querySelector(".wishlist__modal__createBtn");
    const wishlistInputArea = wishlistModal.querySelector(".wishlist__modal__input-container");
    const wishlistInput = wishlistModal.querySelector(".wishlist__modal__input");
    const wishlistInputBtn = wishlistModal.querySelector(".wishlist__modal__inputBtn").querySelector("button");

    modalHeader.addEventListener("click", function(e) {
        e.stopPropagation();
    })
    // 테마 상세 페이지에서 하트 버튼을 눌렀을 때 
    wishlistBtn.onclick = function(e) {
        // 로그인 안 된 상태면 login 페이지로 이동
        if(wishlistBtn.classList.contains("not-logined"))
            return;
        
        e.preventDefault();

        // 로그인이 되어 있고 유저가 해당 테마를 위시리스트에 추가한 상태가 아니면 모달창 열림
        if(!wishlistBtn.classList.contains("heart-filled")) {
            body.style.overflow = "hidden"; // 모달 창이 열렸을 때 배경 scroll 안 되게
            body.style.backgroundColor = "rgba(0, 0, 0, 0.2)";
            wishlistModal.classList.remove("hidden");
        } else {
            let {memberId, travelThemeId} = wishlistBtn.dataset;
            
            fetch(`/api/wishlists/${travelThemeId}/members/${memberId}`, {
                method: "DELETE" 
            })
            .then(response => response.text())
            .then(value => parseInt(value))
            .then(result => {
                if(result === 1) {
                    fetch(`/api/wishlists/count?tid=${travelThemeId}`, {
                        method: "GET"
                    })
                    .then(response => response.json())
                    .then(count => {
                        wishlistBtn.parentElement.nextElementSibling.innerText = count;
                        wishlistBtn.classList.remove("heart-filled");
                    }) 
                }
            })
        }
    }

    // 모달 창의 x 버튼을 눌렀을 때 
    modalCloseBtn.onclick = function(e) {
        e.preventDefault();

        body.style.backgroundColor = "transparent";
        body.style.overflow = "auto";
        wishlistModal.classList.add("hidden");

        if(wishlistCreateBtn.classList.contains("hidden")) {
            wishlistCreateBtn.classList.remove("hidden");
            wishlistInputArea.classList.add("hidden");
        }
    }

    // modal이 열렸을 때 modal 이외의 영역을 눌렀을 때 

    // 새로운 위시리스트 만들기 영역을 눌렀을 때 
    wishlistCreateBtn.onclick = function(e) {
        e.stopPropagation();
        
        wishlistInputArea.classList.remove("hidden");
        this.classList.add("hidden");
    }

    // 새로운 위시리스트에 관한 정보를 입력한 후 저장을 눌렀을 때
    wishlistInputBtn.onclick = function(e) {
        const title = wishlistInput.value;
        if(title === "") {
            // !!!!타이틀이 비어 있는 경우 조건 처리 구현해야 됨!!!!!! 
            return;
        }

        wishlistInput.value = "";

        const slider = document.querySelector(".theme__image-preview");
        const thumbnailPath = slider.firstElementChild.querySelector("img").src;
        const {memberId, travelThemeId} = wishlistBtn.dataset;

        const wishlistGroup = {
            memberId,
            title,
            thumbnailPath
        };

        fetch("/api/wishlistGroups", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(wishlistGroup)
        })
        .then(response => response.json())
        .then(wishlistGroupId => {

            const wishlist = {
                travelThemeId,
                wishlistGroupId
            };

            fetch("/api/wishlists", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(wishlist)
            })
            .then(response => response.json())
            .then(result => {
                if(!result) 
                    return;

                fetch(`/api/wishlists/count?tid=${travelThemeId}`, {
                    method: "GET"
                })
                .then(response => response.json())
                .then(count => {
                    wishlistBtn.classList.add("heart-filled");
                    wishlistBtn.parentElement.nextElementSibling.innerText = count;

                    modalCloseBtn.click();

                    let template = `
                        <div class="wishlist__modal__exist-list">
                            <img src="${thumbnailPath}">
                            <span>${title}</span>
                        </div>
                    `;
 
                    wishlistInputArea.classList.add("hidden");
                    wishlistCreateBtn.insertAdjacentHTML("afterend", template);
                    wishlistCreateBtn.classList.remove("hidden");
                })
                
            })
        })
    }

    modalBody.onclick = function(e) {

        if(!e.target.closest(".wishlist__modal__exist-list"))
            return;

        let targetWishlistGroup;

        if(!e.target.classList.contains("wishlist__modal__exist-list"))
            targetWishlistGroup = e.target.parentElement;
        else
            targetWishlistGroup = e.target.dataset;
        // console.log(wishlistBtn.dataset);
        let {wishlistGroupId} = targetWishlistGroup.dataset;
        let {travelThemeId} = wishlistBtn.dataset;
        
        const wishlist = {
            travelThemeId,
            wishlistGroupId
        };

        fetch("/api/wishlists", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(wishlist)
        })
        .then(response => response.json())
        .then(result => {
            if(!result) 
                return;

            fetch(`/api/wishlists/count?tid=${travelThemeId}`, {
                method: "GET"
            })
            .then(response => response.json())
            .then(count => {
                wishlistBtn.classList.add("heart-filled");
                wishlistBtn.parentElement.nextElementSibling.innerText = count;

                modalCloseBtn.click();

                wishlistInputArea.classList.add("hidden");
                wishlistCreateBtn.classList.remove("hidden");
            })
            
        })
    }
     
})