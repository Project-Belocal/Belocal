    //   window.addEventListener("load", () => {
    //     const section = document.querySelector("#message");
    //     const tabs = section.querySelectorAll(".message__tab");
    //     const item = section.querySelector(".message__item");
    //     const box = item.querySelector(".message__box");
    //     const notice = section.querySelector(".message__notice");
    //     const noticeWrap = notice.querySelector(".message__notice-wrap")
    //     const empty = section.querySelector(".message__empty");
        
  
    //     tabs.forEach((tab, index) => {
    //       tab.onclick = () => {
    //         if (tab.classList.contains('focus')) {
    //           return;
    //         }
      
    //         tabs.forEach((otherTab) => {
    //           otherTab.classList.remove('focus');
    //         });
            
    //         tab.classList.add('focus');


    //         if (tabs[0].classList.contains("focus")) {
    //           if (!box) {
    //             empty.classList.remove("hidden");
    //           }else{
    //             item.classList.remove("hidden");
    //             notice.classList.add("hidden");
    //           }
    //         }
    
    //         if (tabs[1].classList.contains("focus")) {
    //           console.log(noticeWrap);
    //           if (!noticeWrap) {
    //             empty.classList.remove("hidden");
    //           }else{
    //             item.classList.add("hidden");
    //             notice.classList.remove("hidden");
    //           }
    //         }
    //       };
    //     });
    // });

    


      window.addEventListener("load", () => {
        const section = document.querySelector("#message");
        const tabs = section.querySelectorAll(".message__tab");
        const item = section.querySelector(".message__item");
        const box = item.querySelector(".message__box");
        const notice = section.querySelector(".message__notice");
        const noticeWrap = notice.querySelector(".message__notice-wrap")
        const empty = section.querySelector(".message__empty");
        
  
        tabs.forEach((tab, index) => {
          tab.onclick = () => {
            if (tab.classList.contains('focus')) {
              return;
            }
      
            tabs.forEach((otherTab) => {
              otherTab.classList.remove('focus');
            });
            
            tab.classList.add('focus');



          };
        });
    });














    // window.addEventListener("load", () => {
    //   const section = document.querySelector("#message");
    //   const tabs = section.querySelectorAll(".message__tab");
    //   const item = section.querySelector(".message__item");
    //   const box = item.querySelector(".message__box");
    //   const notice = section.querySelector(".message__notice");
    //   const noticeWrap = notice.querySelector(".message__notice-wrap");
    //   const empty = section.querySelector(".message__empty");
    
    //   const handleTabClick = (tab, index) => {
    //     if (tab.classList.contains('focus')) {
    //       return;
    //     }
    
    //     tabs.forEach((otherTab) => {
    //       otherTab.classList.remove('focus');
    //     });
    
    //     tab.classList.add('focus');
    
    //     if (tabs[0].classList.contains("focus")) {
    //       if (!box) {
    //         empty.classList.remove("hidden");
    //       } else {
    //         item.classList.remove("hidden");
    //         notice.classList.add("hidden");
    //       }
    //     }
    
    //     if (tabs[1].classList.contains("focus")) {
    //       if (!noticeWrap) {
    //         empty.classList.remove("hidden");
    //       } else {
    //         item.classList.add("hidden");
    //         notice.classList.remove("hidden");
    //       }
    //     }
    //   };
    
    //   tabs.forEach((tab, index) => {
    //     tab.onclick = () => {
    //       handleTabClick(tab, index);
    //     };
    //   });
    // });