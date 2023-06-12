
//------------- modal 창 ------------------------------------------
const openButton = document.getElementById("open");
const modal = document.querySelector(".search-modal");
const overlay = modal.querySelector(".search-modal-overlay");
const closeBtn = modal.querySelector("button");

const openModal = () => {
    modal.classList.remove("hidden");
}
const closeModal = () => {
    modal.classList.add("hidden");
}

overlay.addEventListener("click", closeModal);
closeBtn.addEventListener("click", closeModal);
openButton.addEventListener("click", openModal);


//------------ 시간 표시(연습해봄) -------------------------
function updateTime() {
    var now = new Date();
    var hours = now.getHours();
    var minutes = now.getMinutes();
    var seconds = now.getSeconds();

    var timeString = hours + ":" + minutes + ":" + seconds;
    document.getElementById("clock").textContent = timeString;
}

setInterval(updateTime, 1000);