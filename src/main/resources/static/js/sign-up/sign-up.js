window.addEventListener("load",function(){
    const undo = document.querySelector(".sign-up__undo")

    undo.onclick = function (){
        history.go(-1);
    }
})

window.addEventListener("load", function (){
    const birthYearEl = document.querySelector(".year");
    const birthMonthEl = document.querySelector(".month");
    const birthDayEl = document.querySelector(".day");
    let isYearOptionExisted = false;
    let isMonthOptionExisted = false;
    let isDayOptionExisted = false;


    birthYearEl.addEventListener('focus', function () {
        if(!isYearOptionExisted) {
            isYearOptionExisted = true;
            for(let i = 1940; i <= 2023; i++) {
                const YearOption = document.createElement('option');
                YearOption.setAttribute('value', i);
                YearOption.innerText = i;
                this.appendChild(YearOption);
            }
        }
    });

    birthMonthEl.addEventListener('focus', function () {
        if (!isMonthOptionExisted) {
            isMonthOptionExisted = true;
            for (let i = 1; i <= 12; i++) {
                const monthOption = document.createElement('option');
                const formattedValue = i.toString().padStart(2, '0');
                monthOption.setAttribute('value', formattedValue);
                monthOption.innerText = formattedValue;
                this.appendChild(monthOption);
            }
        }
    });

    birthDayEl.addEventListener('focus', function () {
        if (!isDayOptionExisted) {
            isDayOptionExisted = true;
            for (let i = 1; i <= 31; i++) {
                const dayOption = document.createElement('option');
                const formattedValue = i.toString().padStart(2, '0');
                dayOption.setAttribute('value', formattedValue);
                dayOption.innerText = formattedValue;
                this.appendChild(dayOption);
            }
        }
    });

// select 요소들을 가져옵니다.
    const yearSelect = document.querySelector('.box.year');
    const monthSelect = document.querySelector('.box.month');
    const daySelect = document.querySelector('.box.day');

// birth 입력란을 가져옵니다.
    const birthInput = document.querySelector('.birth');

// select 요소의 변경 이벤트 리스너를 추가합니다.
    yearSelect.addEventListener('change', updateBirth);
    monthSelect.addEventListener('change', updateBirth);
    daySelect.addEventListener('change', updateBirth);

// birth 값을 업데이트하는 함수를 정의합니다.
    function updateBirth() {
        // 선택한 연도, 월, 일 값을 가져옵니다.
        const year = yearSelect.value;
        const month = monthSelect.value;
        const day = daySelect.value;

        // 선택한 값들을 birth 입력란에 넣습니다.
        birthInput.value = year + '-' + month + '-' + day;
    }
})


window.addEventListener("load",function (){
    const userId = document.querySelector(".sign-up__id");


})