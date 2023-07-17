
//===================  카테고리 아이콘 : 클릭시 theme box 가져오는 부분 =========================
const ctgBtns = document.querySelector(".category__items-box")
const themeList = document.querySelector(".themeList-section");

ctgBtns.onclick = (e) => {
            e.preventDefault()

            let id = e.target.getAttribute("data-id");

            let offset = 0;

            let url = `http://localhost:8080/api/search-result?ctg=${id}&offset=${offset}`;

            console.log("id: " , id)
            console.log(url)

            fetch(url)
                .then(response => response.json())
                .then(list => {
                    for (let theme of list)
                    {
                        // console.log(list)
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


                        themeList.insertAdjacentHTML("afterbegin", travelTheme);
                    }
                })
            }





const searchInput = document.getElementById('search-input');

// ------------ 검색창 클릭하면 글자 사라지게하는 것 -------------------------
searchInput.addEventListener('click', function() {
    this.value = '';
});


let offset = 6;

//===================  검색창에 Enter : 클릭시 theme box 가져오는 부분 =========================
window.addEventListener('scroll', function() {
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop
    let windowHeight = document.documentElement.clientHeight;
    // let themeList = document.querySelector(".theme-list");



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