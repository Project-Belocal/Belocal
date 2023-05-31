window.addEventListener("load",function(){
    const section = document.querySelector(".room");
    const roomMenu = section.querySelector(".room__menu");
    const roomNav = section.querySelector(".room__nav");
    const roomundo = section.querySelector(".room__undo");

    roomMenu.onclick = function(){
        roomNav.classList.toggle("hidden");
    }

})