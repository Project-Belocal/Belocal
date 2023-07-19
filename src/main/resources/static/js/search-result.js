
//===================  카테고리 아이콘 : 클릭시 theme box 가져오는 부분 =========================
const ctgBtns = document.querySelector(".category__items-box")
const themeList = document.querySelector(".themeList-section");

ctgBtns.onclick = (e) => {
    e.preventDefault()
    themeList.innerHTML = "";



            let id = e.target.getAttribute("data-id");
            let offset = 0;
            let url = `/travel-themes?c=${id}&offset=${offset}`;

            console.log("id: " , id);
            console.log(url);

            fetch(url)
                .then(response => response.json())
                .then(list => {
                    for (let theme of list)
                    {
                        // console.log(list)
                        let travelTheme =
                            `<section class="theme"">
                            <div class="theme-box-area">
                                    <a class="theme-box-pic-area" href="/theme/theme-detail?id=${theme.id}">
                                        <img src="${theme.path}" alt="">
                                    </a>
                                <div class="profile-outter-box">
                                    <a class="profile-pic-id-outter" href="/member-profile?i=${theme.memberId}">
                                        <div class="profile-pic">

                                            <img src="https://storage.googleapis.com/belocal-bucket/${theme.uuid}" alt="" >

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
                                                ${theme.description}

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





let searchInput = document.getElementById('search-input');

// ------------ 검색창 클릭하면 글자 사라지게하는 것 -------------------------
searchInput.addEventListener('click', function() {
    this.value = '';
});

// let offset = 6;
let offset = 6;

//===================  검색창에 Enter : 클릭시 theme box 가져오는 부분 =========================
window.addEventListener('scroll', function() {
    let searchInput = document.getElementById('search-input');
    let documentHeight = document.documentElement.scrollHeight;
    let scrollTop = document.documentElement.scrollTop
    let windowHeight = document.documentElement.clientHeight;
    // let themeList = document.querySelector(".theme-list");

    // let themeList = document.querySelector(".themeList-section");

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
                                    <a class="theme-box-pic-area" href="/theme/theme-detail?id=${theme.id}">
                                        <img src="${theme.path}" alt="">
                                    </a>
                                <div class="profile-outter-box">
                                    <a class="profile-pic-id-outter" href="/member-profile?i=${theme.memberId}">
                                        <div class="profile-pic">
                                            <img src="https://storage.googleapis.com/belocal-bucket/${theme.uuid}" alt="" >

                                          
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
                                                ${theme.description}

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