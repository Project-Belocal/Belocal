window.addEventListener("load",function(){
    const dealList = document.querySelector(".my__deal-list");
    const dealItem = document.querySelector(".my__deal-item");
    const modalBg = document.querySelector(".modal-bg");
    const modalClose = document.querySelector(".modal__close");

    dealList.onclick =function(e){
        let isItem1 = dealItem.classList.contains("item1");
        let isItem2 = dealItem.classList.contains("item2");
        let isItem3 = dealItem.classList.contains("item3");
        let validItem = isItem1 || isItem2 || isItem3 ;

        if (!validItem)
            return;
        if (validItem)
            modalBg.classList.toggle("hidden")
    }


    modalClose.onclick = function(){
        modalBg.classList.toggle("hidden");
    }

})