document.addEventListener("DOMContentLoaded", function() {
    const loginError = document.querySelector(".login__error");
    const undo = document.querySelector(".login__undo>button");
    const queryString = window.location.search;

    undo.onclick = function (){
        history.go(-1);
    }

    function fadeOut(element) {
        let op = 1;
        const timer = setInterval(function() {
            if (op <= 0.1) {
                clearInterval(timer);
                element.classList.add("hidden");
            }

            element.style.opacity = op;
            element.style.filter = "alpha(opacity=" + op * 100 + ")";
            op -= op * 0.1;
        }, 100);
    }

    function checkQueryString() {
        if (queryString.includes("error")) {
            loginError.classList.remove("hidden");
            setTimeout(function() {
                fadeOut(loginError);
            }, 2000);
        }
    }
    checkQueryString();

});