const ctgBtns = document.querySelector(".category__items-box")
// const ctgBtns = document.querySelector(".category__item")
// const themeList = document.querySelector(".theme-list");
const themeList = document.querySelector(".themeList-section");

ctgBtns.onclick = (e) => {
            e.preventDefault()


            let id = e.target.getAttribute("data-id");
            // let id = 2;

            let url = `http://localhost:8080/api/search-result?ctg=${id}`;

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

searchInput.addEventListener('click', function() {
    this.value = '';
});
