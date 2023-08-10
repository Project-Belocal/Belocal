const themeSaveBtn = document.querySelector(".theme__save-btn");

const themeTitle = document.querySelector(".theme__title");
const themeDescription = document.querySelector(".theme__description");
const themeBookableDate = document.querySelector(".theme__bookable-date");
const themePreferredTime = document.querySelector(".theme__preferred-time");
const themeBookableDateList = themeBookableDate.querySelectorAll("input");
const themePreferredTimeList = themePreferredTime.querySelectorAll("input");

const themeTitleArea = themeTitle.querySelector("textarea");
const themeDescriptionArea = themeDescription.querySelector("textarea");
const themeBookableDateInputList = themeBookableDate.querySelectorAll("input");
const themePreferredTimeInputList = themePreferredTime.querySelectorAll("input");

themeTitleArea.onchange = function () {
    const themeTitleMessage = themeTitle.querySelector(".theme__title__header").querySelector("div");

    if (themeTitleArea != "" && !themeTitleMessage.classList.contains("hidden"))
        themeTitleMessage.classList.toggle("hidden");
}

themeDescriptionArea.onchange = function () {
    const themeDescriptionMessage = themeDescription.querySelector(".theme__description__header").querySelector("div");

    if (themeDescriptionArea != "" && !themeDescriptionMessage.classList.contains("hidden"))
        themeDescriptionMessage.classList.toggle("hidden");
}

themeBookableDateInputList[0].onchange = function () {
    const isCompleteDate = (themeBookableDateInputList[0].value != "" && themeBookableDateInputList[1].value != "") ? true : false;
    const themeDateMessage = themeBookableDate.querySelector(".theme__bookable-date__header").querySelector("div");

    if (isCompleteDate && !themeDateMessage.classList.contains("hidden"))
        themeDateMessage.classList.toggle("hidden");
}

themeBookableDateInputList[1].onchange = function () {
    const isCompleteDate = (themeBookableDateInputList[0].value != "" && themeBookableDateInputList[1].value != "") ? true : false;
    const themeDateMessage = themeBookableDate.querySelector(".theme__bookable-date__header").querySelector("div");

    if (isCompleteDate && !themeDateMessage.classList.contains("hidden"))
        themeDateMessage.classList.toggle("hidden");
}

themePreferredTimeInputList[0].onchange = function () {
    const isCompleteTime = (themePreferredTimeInputList[0].value != "" && themePreferredTimeInputList[1].value != "") ? true : false;
    const themeTimeMessage = themePreferredTime.querySelector(".theme__preferred-time__header").querySelector("div");

    if (isCompleteTime && !themeTimeMessage.classList.contains("hidden"))
        themeTimeMessage.classList.toggle("hidden");
}

themePreferredTimeInputList[1].onchange = function () {
    const isCompleteDate = (themePreferredTimeInputList[0].value != "" && themePreferredTimeInputList[1].value != "") ? true : false;
    const themeTimeMessage = themePreferredTime.querySelector(".theme__preferred-time__header").querySelector("div");

    if (isCompleteDate && !themeTimeMessage.classList.contains("hidden"))
        themeTimeMessage.classList.toggle("hidden");
}

function createTheme() {

    const themeInfo = document.querySelector(".theme__info");
    const themeTitleData = themeInfo.querySelector(".theme__title").querySelector("textarea");
    const themeDescriptionData = themeInfo.querySelector(".theme__description").querySelector("textarea");
    const themeBookableDateDataList = themeInfo.querySelector(".theme__bookable-date").querySelectorAll("input");
    const themePreferredTimeDataList = themeInfo.querySelector(".theme__preferred-time").querySelectorAll("input");

    const theme = {
        "memberId": null,
        "title": themeTitleData.value,
        "description": themeDescriptionData.value,
        "bookableDateStart": themeBookableDateDataList[0].value,
        "bookableDateEnd": themeBookableDateDataList[1].value,
        "contactPreferredTimeStart": themePreferredTimeDataList[0].value,
        "contactPreferredTimeEnd": themePreferredTimeDataList[1].value
    }

    return theme;
} 

function createPlacesAndImages() {
    let places = [];
    let placesImages = [];

    const uploadedPlaceList = document.querySelector(".place__container").querySelectorAll(".place__item");

    for (let i = 0; i < uploadedPlaceList.length; i++) {
        let placeCategoryId = uploadedPlaceList[i].querySelector(".place__category").querySelector("input").value;
        let placeLocationId = uploadedPlaceList[i].querySelector(".place__location").querySelector("input").value;
        let placeDescription = uploadedPlaceList[i].querySelector(".place__description").innerText;
        let placeOrder = i;

        places[i] = {
            "travelThemeId": null,
            "categoryId": placeCategoryId,
            "locationId": placeLocationId,
            "description": placeDescription,
            "order": placeOrder
        }

        let uploadedPlaceImageList = uploadedPlaceList[i].querySelector(".place__images").querySelectorAll("img");
        let placeImages = [];

        for (let j = 0; j < uploadedPlaceImageList.length; j++) {
            let imagePath = uploadedPlaceImageList[j].src;
            let imageOrder = j;

            placeImages[j] = {
                "placeId": null,
                "path": imagePath,
                "order": imageOrder
            }
        }

        placesImages[i] = placeImages;
    }

    return {"places": places, "placesImages": placesImages};
}

themeSaveBtn.onclick = function (e) {
    e.preventDefault();

    const isComplete = checkCompleteTheme();
    if (!isComplete) return;

    const travelTheme = createTheme();
    const placesAndImages = createPlacesAndImages();
    const places = placesAndImages["places"];
    const placesImages = placesAndImages["placesImages"];

    const uploadObject = {
        "travelTheme": travelTheme,
        "places": places,
        "placesImages": placesImages
    };
    
    const uploadData = JSON.stringify(uploadObject);

    document.querySelector("#uploadDataInput").value = uploadData;
    document.querySelector("#travelThemeForm").submit();
    // const newThemeId = uploadTheme(theme);
    
    // if (!newThemeId) {
    //     displayErrorMessage();
    //     return;
    // }

    // // 각 장소에 테마 id 넣기
    // places.forEach((place) => {
    //     place.travelThemeId = newThemeId;
    // });

    // const placesIds = await uploadPlaces(places);

    // // 각 이미지에 place의 id 입력
    // for(let i = 0; i < placesImages.length; i++) {
    //     placesImages[i].forEach((placeImage) => {
    //         placeImage.placeId = placesIds[i];
    //     })
    // }

    // const data = {
    //     "placesImages": placesImages,
    //     "travelThemeId": newThemeId
    // }

    // fetch("/my/upload-img", {
    //     method: "POST",
    //     headers: {
    //       "Content-Type": "application/json"
    //     },
    //     body: JSON.stringify(data)
    // })
}


// /**
// * @returns {number | null} themeId
// */
async function uploadTheme(travelTheme) {
    try {
        const response = await fetch("/my/upload-theme", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(travelTheme),
        });
        const data = await response.json();
        return data;

    } catch (error) {
        console.log(error);
        return null;
    }
}


// /**
// * @returns {number | null} placeId
// */
async function uploadPlace(place) {
    try {
        const response = await fetch("/my/upload-place", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(place),
        });

        const data = await response.json();
        return data;
    } catch (error) {
        console.log(error);
        return null;
        // 문제 발생 여지
        // placeIds 배열에 null 들어가면 처리해야함
    }
}

async function uploadPlaces(places) {
    const placeIds = [];

    for (const place of places) {
        const id = await uploadPlace(place);
        placeIds.push(id);
    }

    return placeIds;
}


/** 에러를 사용자에게 보여준다 */
function displayErrorMessage() {
    alert("업로드 중에 오류가 발생했습니다. 다시 시도해주세요.");
}
    

function checkCompleteTheme() {

    const isEmptyThemeTitle = themeTitle.querySelector("textarea").value == "" ? true : false;
    const isEmptyThemeDescription = themeDescription.querySelector("textarea").value == "" ? true : false;
    const isIncompleteDate = (themeBookableDateList[0].value == "" || themeBookableDateList[1].value == "") ? true : false;
    const isIncompleteTime = (themePreferredTimeList[0].value == "" || themePreferredTimeList[1].value == "") ? true : false;

    if(isEmptyThemeTitle) {
        const themeTitleMessage = themeTitle.querySelector(".theme__title__header").querySelector("div");
        themeTitleMessage.classList.remove("hidden");
        return;
    }

    if(isEmptyThemeDescription) {
        const themeDescriptionMessage = themeDescription.querySelector(".theme__description__header").querySelector("div");
        themeDescriptionMessage.classList.remove("hidden");
        return;
    }

    if(isIncompleteDate) {
        const themeBookableDateMessage = themeBookableDate.querySelector(".theme__bookable-date__header").querySelector("div");
        themeBookableDateMessage.classList.remove("hidden");
        return;
    }

    if(isIncompleteTime) {
        const themePreferredTimeMessage = themePreferredTime.querySelector(".theme__preferred-time__header").querySelector("div");
        themePreferredTimeMessage.classList.remove("hidden");
        return;
    }

    return true;
}