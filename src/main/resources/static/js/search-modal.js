
//------------- modal 창 ------------------------------------------
const openButton = document.getElementById("open");
const modal = document.querySelector(".search-modal");
const overlay = modal.querySelector(".search-modal-overlay");
const closeBtn = modal.querySelector("#exit");
const main=document.querySelector("main");


openButton.onclick = (e) => {
        modal.classList.remove("hidden");
        main.classList.add("hidden");
}

closeBtn.onclick = (e) => {
    modal.classList.add("hidden");
    main.classList.remove("hidden");
}





const searchInput = document.getElementById('search-input');


// ------------ 검색창 클릭하면 글자 사라지게하는 것 -------------------------
searchInput.addEventListener('click', function() {
    this.value = '';
});



// --------- search-modal에 실시간 검색어 조회(enter 전) -------------------------
const themeList = document.querySelector(".theme-list--search-modal");

searchInput.addEventListener('keyup', function() {

    let offset = 0;
    // let batchSize = 6;
    let query = searchInput.value;
    console.log("query:",query);

    let url = `/api/travel-themes?q=${query}&offset=${offset}`;



    fetch(url)
            .then(response => response.json())
            .then(list => {
                console.log("list",list);
                themeList.innerHTML='';

                for (let theme of list)
                {
                    console.log("theme: ", theme);
                    let travelTheme =
                        `<section class="theme"">
                            <div class="theme-box-area">
                                    <a class="theme-box-pic-area" href="/theme/theme-detail?id=${theme.id}">
                                        <img src="/images/index-city.jpg" alt="">
                                    </a>
                                <div class="profile-outter-box">
                                    <a class="profile-pic-id-outter" href="/member-profile?i=${theme.memberId}">
                                        <div class="profile-pic">

                                            <img src="/images/profile-pic.jpg" alt="">

                                        </div>
                                        <div class="profile-id-text">
                                           ${theme.nickname}
                                        </div>
                                    </a>
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
                                                    ?  `
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
                }
            })

        // window.addEventListener('scroll', function() {
        //     let documentHeight = document.documentElement.scrollHeight;
        //     let scrollTop = document.documentElement.scrollTop
        //     let windowHeight = document.documentElement.clientHeight;
        //     let themeList = document.querySelector(".theme-list");
        //
        //     if(windowHeight + scrollTop == documentHeight) {
        //         // fetchData(offset);
        //         offset += batchSize;
        //         console.log("offset: ", offset);
        //     }//---if
        //
        // })//--- scroll


});





let offset = 6;
//========== search-modal : 스크롤 다운 시 6개 가져오는 부분 =========================
window.addEventListener('scroll', function() {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop
    let windowHeight = document.documentElement.clientHeight;
    let searchInput = document.getElementById('search-input');

    // let themeList = document.querySelector(".theme-list");
    // let offset = 0;
    // let batchSize = 6;


    console.log("documentHeight: ", documentHeight);
    console.log("scrollTop: ", scrollTop);
    console.log("windowHeight: ", windowHeight);

    if(windowHeight + scrollTop == documentHeight) {
        // fetchData(offset);
        // offset += batchSize;
        console.log("offset: ", offset);

        let query = searchInput.value;
        let url = `/api/travel-themes?q=${query}&offset=${offset}`;

        fetch(url)
            .then(response => response.json())
            .then(list => {
                for (let theme of list) {
                    console.log("theme",theme);
                    let travelTheme =
                        `<section class="theme"">
                            <div class="theme-box-area">
                                    <a class="theme-box-pic-area" href="api/theme/theme-detail?id=${theme.id}">
                                        <img src="/images/index-city.jpg" alt="">
                                    </a>
                                <div class="profile-outter-box">
                                    <a class="profile-pic-id-outter" href="api/member-profile?i=${theme.memberId}">
                                        <div class="profile-pic">

                                          
                                        </div>
                                        <div class="profile-id-text">
                                           ${theme.nickname}
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
