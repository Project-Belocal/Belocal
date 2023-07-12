window.addEventListener("load", () => {
    {
        const currentPage = window.location.href;
        const navBar = document.querySelector(".navbar");
        const buttons = navBar.querySelectorAll(".navbar__btn"); 
    
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
    }

    {
        const memberId = document.querySelector("#member-id").value;
        
        if(!memberId)
            return;
        
        const eventSource = new EventSource(`/subscribe/${memberId}`);

        eventSource.addEventListener("sse", function(e) {
            console.log(e.data);
        })
    }

});
