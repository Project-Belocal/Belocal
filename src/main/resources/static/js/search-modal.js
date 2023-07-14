
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
const themeList = document.querySelector(".search__dynamic--theme");
// const themeList = document.querySelector(".themeList-section");

searchInput.addEventListener('keyup', function() {
    // const searchTerm = searchInput.value.toLowerCase();

    // Array.from(themeList).forEach(function(section) {
        console.log("themeList:", themeList);
        console.log("작동");


        let query = searchInput.value;
        let url = `http://localhost:8080/api?q=${query}`;


        fetch(url)
            .then(response => response.json())
            .then(list => {
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

    // });
});

