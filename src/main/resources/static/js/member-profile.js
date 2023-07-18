const tabs = document.querySelectorAll('.tab');
const tabContent = document.querySelectorAll('.tab-panel');
const tabContent2 = document.querySelectorAll('.tab-panel2');

tabs.forEach((tab) => {
    tab.addEventListener('click', () => {
        const target = document.querySelector(tab.dataset.target);


        tabContent2.forEach((tc) => {
            tc.classList.remove('is-active');
        });
        target.classList.add('is-active');

        tabContent.forEach((tc) => {
            tc.classList.remove('is-active');
        });
        target.classList.add('is-active');


        tabs.forEach((t) => {
            t.classList.remove('is-active');
        });
        tab.classList.add('is-active');
    });
});




let offset = 6;

window.addEventListener('scroll', function() {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop
    let windowHeight = document.documentElement.clientHeight;
    let themeList = document.querySelector(".theme-list");

    let url = `/api/travel-themes?offset=${offset}`;


    console.log("documentHeight: ", documentHeight);
    console.log("scrollTop: ", scrollTop);
    console.log("windowHeight: ", windowHeight);

    if(windowHeight + scrollTop == documentHeight) {
        console.log("offset: ", offset);


        fetch(url)
            .then(response => response.json())
            .then(list => {
                for (let theme of list) {
                    console.log("theme",theme);
                    let travelTheme =
                        `<section class="theme">
                            <div class="theme-box-area">
                                    <a class="theme-box-pic-area" href="/theme/theme-detail?id=${theme.id}">
                                        <img src="/images/index-city.jpg" alt="">
                                    </a>
                                <div class="profile-outter-box">
                                    <div class="profile-pic-id-outter">
                                        <div class="profile-pic">
    
                                            <img src="/images/profile-pic.jpg" alt="">
                                        </div>
                                        <div class="profile-id-text">
                                            innerjoin123
                                        </div>
                                    </div>
                                    <a class="profile-text-area-outter" href="/theme/theme-detail?id=${theme.id}">
                                            <div class="profile-text-title-area">
                                                <h2>${theme.title}</h2>
                                            </div>
                                            <div class="profile-text-contents-area">
                                                <p>

                                                </p>
                                            </div>
                                            
                                            
                                      <div class="profile-status-area">
                                            ${theme.isReserved === 0
                                            ?
                                            `
                                                <div>
                                                    <span class="material-symbols-outlined res-ok">
                                                        event_available
                                                    </span>
                                                </div>
                                                <div class="reserveText">예약 가능</div>
                                            `
                                            :
                                            `
                                                <div>
                                                    <span class="material-symbols-outlined res-not">
                                                        event_busy
                                                    </span>
                                                </div>
                                                <div class="reserveText">예약 불가</div>
                                            `
                                            }
                                        </div>
                                            
                                            
                                    </a>
                                </div>
                            </div>
                        </section>`
                    themeList.insertAdjacentHTML("beforeend", travelTheme);
                    offset ++;
                }

            })

        }
});






