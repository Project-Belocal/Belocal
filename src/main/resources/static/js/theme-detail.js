window.addEventListener('load', ()=> {
    const btn = document.querySelector(".theme__title--header--button--share");
    const modalBackground = document.querySelector(".modal__background");

    btn.onclick = function() {
        if(modalBackground.classList.contains("hidden")
        )
        {
            modalBackground.classList.remove("hidden");
            console.log("modal클릭")
        } else {
            modalBackground.classList.add("hidden")
        }
    };




});