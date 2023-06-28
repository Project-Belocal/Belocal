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
let batchSize = 6;

window.addEventListener('scroll', function() {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop
    let windowHeight = document.documentElement.clientHeight;
    let themeList = document.querySelector(".theme-list");

    let url = `http://localhost:8080/api/memberprofile?offset=${offset}`;


    console.log("documentHeight: ", documentHeight);
    console.log("scrollTop: ", scrollTop);
    console.log("windowHeight: ", windowHeight);


    if(windowHeight + scrollTop == documentHeight) {
        // fetchData(offset);
        offset += batchSize;

        console.log("offset: ", offset);


        // fetch(`api/memberprofile?offset=${offset}`)
        fetch(url)
            .then(response => response.json())
            .then(list => {
                // console.log("list",list);
                for (let theme of list) {
                    let travelTheme =
                        `<section class="theme"">
                            <div class="theme-box-area">
                                    <div class="theme-box-pic-area">
                                        <img src="/images/index-city.jpg" alt="">
                                    </div>
                                <div class="profile-outter-box">
                                    <div class="profile-pic-id-outter">
                                        <div class="profile-pic">
    
                                            <img src="/images/profile-pic.jpg" alt="">
                                        </div>
                                        <div class="profile-id-text">
                                            innerjoin123
                                        </div>
                                    </div>
                                    <div class="profile-text-area-outter">
                                            <div class="profile-text-title-area">
                                                <p>${theme.title}</p>
                                            </div>
                                            <div class="profile-text-contents-area">
                                                <p>

                                                </p>
                                            </div>
                                            <div class="profile-status-area">
                                                <span class="material-symbols-outlined">
                                                    event_available
                                                </span>
                                                <div class="status-text">
                                                    예약가능
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </section>`

                    themeList.insertAdjacentHTML("beforeend", travelTheme);
                }
            })
        }
    });







