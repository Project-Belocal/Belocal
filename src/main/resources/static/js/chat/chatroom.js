window.addEventListener("load",function(){
    const section = document.querySelector(".room");
    const Menu = section.querySelector(".room__menu");
    const Nav = section.querySelector(".room__nav");
    const Undo = section.querySelector(".room__undo");
    const reportBtn = section.querySelector(".room__report-btn");
    const modalBg = section.querySelector(".room__modal-bg");
    const reportModal = section.querySelector(".room__report");
    const reportClose = section.querySelector(".room__report-close");

    Undo.onclick = function(){
        history.go(-1);
    }


    Menu.onclick = function(){
        modalBg.classList.toggle("hidden");
        Nav.classList.toggle("hidden");

    }

    reportBtn.onclick = function(){
        reportModal.classList.toggle("hidden");
        Nav.classList.toggle("hidden");
    }

    reportClose.onclick = function(){
        reportModal.classList.toggle("hidden");
        modalBg.classList.toggle("hidden");
    }

    modalBg.onclick = function(e){
        if (e.target === modalBg) {
            modalBg.classList.add("hidden")
            Nav.classList.add("hidden");
            reportModal.classList.add("hidden");
        }
    }
})
