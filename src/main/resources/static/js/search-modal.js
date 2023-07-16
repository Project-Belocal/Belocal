
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

    let url = `http://localhost:8080/api?q=${query}&offset=${offset}`;



    fetch(url)
            .then(response => response.json())
            .then(list => {
                console.log("list",list);
                themeList.innerHTML='';

                for (let theme of list)
                {
                    console.log("list:", list)
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
                                           ${theme.nickname}
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
                                                    ${theme.isReserved}
                                                </div>
                                            </div>
                                    </div>
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
        let url = `http://localhost:8080/api?q=${query}&offset=${offset}`;


        // fetch(`api/memberprofile?offset=${offset}`)
        fetch(url)
            .then(response => response.json())
            .then(list => {
                for (let theme of list) {
                    console.log("theme",theme);
                    let travelTheme =
                        `<section class="theme">
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
                    offset ++;

                }
            })
    }
});
