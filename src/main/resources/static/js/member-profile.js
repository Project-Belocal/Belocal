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
                                    <a class="theme-box-pic-area" href="api/theme/theme-detail?id=${theme.id}">
                                        <img src="/images/index-city.jpg" alt="">
                                    </a>
                                <div class="profile-outter-box">
                                    <a class="profile-pic-id-outter" href="api/member-profile?i=${theme.memberId}">
                                        <div class="profile-pic">
    
                                            <img src="/images/profile-pic.jpg" alt="">
                                        </div>
                                        <div class="profile-id-text">
                                            innerjoin123
                                        </div>
                                    </a>
                                    <a class="profile-text-area-outter" href="api/theme/theme-detail?id=${theme.id}">
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


// document.addEventListener('DOMContentLoaded', function() {
    // 클릭 이벤트를 profile-outter-box 요소에 추가
    // let clickzone = document.querySelector('.profile-outter-box')
    //     clickzone.addEventListener('click', function() {
    //         // 현재 클릭한 요소의 th:value 값을 가져옴
    //         const memberId = clickzone.getAttribute('th:value');
    //         console.log("memberId:", memberId);
    //         // 새로운 페이지로 이동
    //         window.location.href = `http://localhost:8080/member-profile?i=${memberId}`;
    //
    //
    // });
// });



// document.addEventListener('DOMContentLoaded', function() {
//     // 클릭 이벤트를 profile-outter-box 요소들에 추가
//     const profileBoxes = document.querySelectorAll('.profile-outter-box');
//     profileBoxes.forEach(function(profileBox) {
//         profileBox.addEventListener('click', function() {
//             // 현재 클릭한 요소의 th:value 값을 가져옴
//             const memberId = this.getAttribute('th:value');
//             console.log("memberId:", memberId);
//
//             // 새로운 페이지로 이동
//             window.location.href = `http://localhost:8080/member-profile?i=${memberId}`;
//
//         });
//     });
// });








