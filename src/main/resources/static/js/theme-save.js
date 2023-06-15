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

themeTitleArea.onchange = function() {
    const themeTitleMessage = themeTitle.querySelector(".theme__title__header").querySelector("div");

    if(themeTitleArea != "" && !themeTitleMessage.classList.contains("hidden"))
        themeTitleMessage.classList.toggle("hidden");
}

themeDescriptionArea.onchange = function() {
    const themeDescriptionMessage = themeDescription.querySelector(".theme__description__header").querySelector("div");
    
    if(themeDescriptionArea != "" && !themeDescriptionMessage.classList.contains("hidden"))
        themeDescriptionMessage.classList.toggle("hidden");
}

themeBookableDateInputList[0].onchange = function() {
    const isCompleteDate = (themeBookableDateInputList[0].value != "" && themeBookableDateInputList[1].value != "") ? true : false;
    const themeDateMessage =  themeBookableDate.querySelector(".theme__bookable-date__header").querySelector("div");
    
    if(isCompleteDate && !themeDateMessage.classList.contains("hidden"))
        themeDateMessage.classList.toggle("hidden");
}

themeBookableDateInputList[1].onchange = function() {
    const isCompleteDate = (themeBookableDateInputList[0].value != "" && themeBookableDateInputList[1].value != "") ? true : false;
    const themeDateMessage =  themeBookableDate.querySelector(".theme__bookable-date__header").querySelector("div");
    
    if(isCompleteDate && !themeDateMessage.classList.contains("hidden"))
        themeDateMessage.classList.toggle("hidden");
}

themePreferredTimeInputList[0].onchange = function() {
    const isCompleteTime = (themePreferredTimeInputList[0].value != "" && themePreferredTimeInputList[1].value != "") ? true : false;
    const themeTimeMessage =  themePreferredTime.querySelector(".theme__preferred-time__header").querySelector("div");
    
    if(isCompleteTime && !themeTimeMessage.classList.contains("hidden"))
        themeTimeMessage.classList.toggle("hidden");
}

themePreferredTimeInputList[1].onchange = function() {
    const isCompleteDate = (themePreferredTimeInputList[0].value != "" && themePreferredTimeInputList[1].value != "") ? true : false;
    const themeTimeMessage =  themePreferredTime.querySelector(".theme__preferred-time__header").querySelector("div");
    
    if(isCompleteDate && !themeTimeMessage.classList.contains("hidden"))
        themeTimeMessage.classList.toggle("hidden");
}

themeSaveBtn.onclick = function() {
    const isComplete = checkCompleteTheme();

    if(isComplete) {
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

        let places = [];
        let placesImages = [];

        const uploadedPlaceList = document.querySelector(".place__container").querySelectorAll(".place__item");
        
        for(let i = 0; i < uploadedPlaceList.length; i++) {
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

            for(let j = 0; j < uploadedPlaceImageList.length; j++) {
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
        
        // const data = {
        //     "theme" : theme,
        //     "places" : places,
        //     "placesImages" : placesImages
        // };

        fetch("/my-page/upload-theme", {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify(theme)
          })
          .then(response => response.json())
          .then(themeResponse => {
            places.forEach((place) => {
                console.log(place);
                place.travelThemeId = themeResponse;

                fetch("/my-page/upload-place", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(place)
                })
                .then(response => console.log(response.json()))
                .then(placeResponse => {
                    placesImages.forEach((placeImages) => {
                        placeImages.forEach((placeImage) => {
                            placeImage.placeId = placeResponse;

                            fetch("my-page/upload-img", {
                                method: "POST",
                                header: {
                                    "Content-Type": "application/json"
                                },
                                body: JSON.stringify(placeImage)
                            })
                        })
                    })
                })
            })

          })
    }
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