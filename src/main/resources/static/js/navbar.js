window.addEventListener("load", () => {
    const currentPage = window.location.href;
    const navBar = document.querySelector(".nav-bar");
    const buttons = navBar.querySelectorAll(".nav-bar__btn"); 

    const startTime = performance.now();
    for(let button of buttons) {
        // if(button.href != currentPage) {
        //     continue;
        // }

        // button.classList.add("active");
        button.classList.toggle("active", button.href === currentPage);
    }
    const endTime = performance.now();
    const executionTime = endTime - startTime;
    console.log("Execution time: " + executionTime + " milliseconds");
});
