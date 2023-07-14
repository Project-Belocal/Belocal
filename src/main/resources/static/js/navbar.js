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
            let data = JSON.parse(e.data);

            const currentPage = window.location.href;
            const chatBtn = document.querySelector(".chat-btn");
            const newNotificationIcon = chatBtn.querySelector(".new-notification");

            if(newNotificationIcon.classList.contains("hidden"))
                newNotificationIcon.classList.toggle("hidden", currentPage === chatBtn.href);

            console.log(currentPage);
            console.log(chatBtn.href);
            (async () => {
                // 브라우저 알림
                const showNotification = () => {

                    const notification = new Notification('코드 봐줘', {
                        body: data
                    });

                    setTimeout(() => {
                        notification.close();
                    }, 10 * 1000);

                    notification.addEventListener('click', () => {
                        window.open(`http://localhost:8080/`, '_blank');
                    });
                }

                // 브라우저 알림 허용 권한
                let granted = false;

                if (Notification.permission === 'granted') {
                    granted = true;
                } else if (Notification.permission !== 'denied') {
                    let permission = await Notification.requestPermission();
                    granted = permission === 'granted';
                }

                // 알림 보여주기
                if (granted) {
                    showNotification();
                }
            })();

        })
    }

});
