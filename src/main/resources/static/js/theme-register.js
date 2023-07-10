let inputFileLists = [];

window.addEventListener("load", () => {
    const body = document.querySelector("body");

    const modal = document.querySelector(".modal");

    // Get the button that opens the modal
    const regBtn = document.querySelector(".place__registration-btn");
    // Get the <span> element that closes the modal
    const closeBtn = document.querySelector(".modal__close");

    const deleteBtn = document.querySelector(".modal__delete-btn");

    const modalSaveBtn = document.querySelector(".modal__save-btn");

    const nextBtn = document.querySelector(".registration__next-btn").querySelector("button");
    const prevBtn = document.querySelector(".header__prev-button");

    const categorySelector = modal.querySelector(".modal__place-category").querySelector("select");
    const locationSelector = modal.querySelector(".modal__place-location").querySelector("select");
    let categorySelectedText, locationSelectedText;
    const descriptionArea = modal.querySelector(".modal__place-description").querySelector("textarea");

    categorySelector.onchange = function () {
        const categoryMessage = modal.querySelector(".modal__place-category__header").querySelector("div");
        if (categorySelector.value != "" && !categoryMessage.classList.contains("hidden"))
            categoryMessage.classList.toggle("hidden");

        categorySelectedText = categorySelector.options[categorySelector.selectedIndex].text;
    }

    locationSelector.onchange = function () {
        const locationMessage = modal.querySelector(".modal__place-location__header").querySelector("div");
        if (locationSelector.value != "" && !locationMessage.classList.contains("hidden"))
            locationMessage.classList.toggle("hidden");

        locationSelectedText = locationSelector.options[locationSelector.selectedIndex].text;
    }

    descriptionArea.onchange = function () {
        const descriptionMessage = modal.querySelector(".modal__place-description__header").querySelector("div");
        if (descriptionArea.value != "" && !descriptionMessage.classList.contains("hidden"))
            descriptionMessage.classList.toggle("hidden");
    }

    function toggleRegistrationPages() {
        if (placeContainer.childElementCount == 0) {
            document.querySelector(".place__message").classList.remove("hidden");
            return;
        }

        const themeImagePreview = document.querySelector(".theme__image-preview");
        const slides = themeImagePreview.querySelectorAll("li");

        slides.forEach((slide) => {
            themeImagePreview.removeChild(slide);
        })

        const placesImageList = placeContainer.querySelectorAll(".place__images");

        placesImageList.forEach((placeImageContainer) => {
            const placeImages = placeImageContainer.querySelectorAll("img");

            placeImages.forEach((img) => {
                const themeImage = createPreviewElement(img.src);
                themeImagePreview.appendChild(themeImage);
            })
        })

        initThemeImageSlider();
        // 두 번째 페이지 슬라이더 형성 어떻게 할 건지 고민
        // 첫 번째 페이지에서 삭제 시에 두 번째 페이지에는 반영이 안 되는 상태

        const firstPage = document.querySelector(".theme__registration-1st");
        const secondPage = document.querySelector(".theme__registration-2nd");
        const headerPrevBtn = document.querySelector(".header__prev-button");

        firstPage.classList.toggle("hidden");
        secondPage.classList.toggle("hidden");
        headerPrevBtn.classList.toggle("hidden");
    }

    prevBtn.onclick = toggleRegistrationPages;
    nextBtn.onclick = toggleRegistrationPages;

    deleteBtn.onclick = function (e) {
        e.preventDefault();

        const slider = document.querySelector(".modal__image-preview");
        const slides = slider.querySelectorAll("li");
        const uploadBtn = document.querySelector(".modal__image-upload");

        slides.forEach((slide) => {
            slider.removeChild(slide);
        });

        deleteBtn.classList.toggle("hidden");
        uploadBtn.classList.toggle("hidden");

        const realUploadBtn = document.querySelector(".modal__upload-btn");
        realUploadBtn.value = "";
    }

    // When the user clicks on the button, open the modal
    regBtn.onclick = function (e) {
        e.preventDefault();
        modal.style.display = "block";
        body.style.overflow = "hidden";
    }

    closeBtn.onclick = function (e) {
        e.preventDefault();
        modal.style.display = "none";
        body.style.overflow = "auto";
    }

    modalSaveBtn.onclick = function (e) {
        const modalImageInput = document.querySelector(".modal__upload-btn");
        const slider = document.querySelector(".modal__image-preview");
        const modalPlaceCategory = document.querySelector(".modal__place-category");
        const modalPlaceLocation = document.querySelector(".modal__place-location");
        const modalPlaceDescription = document.querySelector(".modal__place-description");

        const isEmptySlider = slider.childElementCount == 0 ? true : false;
        if (isEmptySlider) {
            modal.querySelector(".modal__place-img__header").querySelector("div").classList.remove("hidden");
            return;
        }

        const isEmptyCategory = modalPlaceCategory.querySelector("select").value == "" ? true : false;
        if (isEmptyCategory) {
            modalPlaceCategory.querySelector(".modal__place-category__header").querySelector("div").classList.remove("hidden");
            return;
        }

        const isEmptyLocation = modalPlaceLocation.querySelector("select").value == "" ? true : false;
        if (isEmptyLocation) {
            modalPlaceLocation.querySelector(".modal__place-location__header").querySelector("div").classList.remove("hidden");
            return;
        }

        const isEmptyDescription = modalPlaceDescription.querySelector("textarea").value == "" ? true : false;
        if (isEmptyDescription) {
            modalPlaceDescription.querySelector(".modal__place-description__header").querySelector("div").classList.remove("hidden");
            return;
        }
        // input type=file에 저장된 image들(files)을 inputFileLists 배열에 저장
        inputFileLists.push(Array.from(modalImageInput.files));
        // for(let i = 0; i < modalImageInput.files.length; i++) {
        //     inputFileLists[inputFileLists.length-1].push(modalImageInput.files[i]);
        // }

        // console.log(inputFileLists);
        // 저장 버튼을 누르면 모달 밖의 place__container라는 ul 영역에 
        // li 형식으로 장소에 관련된 내용을 넣어서 저장한 뒤 내부 내용은 삭제하고 모달창을 닫기

        // 1. li로 modal에 입력한 사항을 저장하고 place__container에 append
        const placeContainer = document.querySelector(".place__container");
        const li = document.createElement("li");
        li.classList.add("place__item");

        const placeImages = document.createElement("div");
        placeImages.classList.add("place__images");
        const modalImages = slider.querySelectorAll("img");
        modalImages.forEach((img) => {
            placeImages.appendChild(img);
        });

        const placeInfo = document.createElement("div");
        placeInfo.classList.add("place__info");

        const placeCategory = document.createElement("div");
        placeCategory.classList.add("place__category");
        placeCategory.innerText = categorySelectedText;
        const placeCategoryInput = document.createElement("input");
        placeCategoryInput.type = "hidden";
        placeCategoryInput.value = modalPlaceCategory.querySelector("select").value;
        placeCategory.appendChild(placeCategoryInput);

        const placeLocation = document.createElement("div");
        placeLocation.classList.add("place__location");
        placeLocation.innerText = locationSelectedText;
        const placeLocationInput = document.createElement("input");
        placeLocationInput.type = "hidden";
        placeLocationInput.value = modalPlaceLocation.querySelector("select").value;
        placeLocation.appendChild(placeLocationInput);

        const placeDescription = document.createElement("div");
        placeDescription.classList.add("place__description");
        placeDescription.innerHTML = modalPlaceDescription.querySelector("textarea").value;

        const placeDeleteBtn = document.createElement("div");
        placeDeleteBtn.classList.add("place__delete-btn");
        const deleteImg = document.createElement("img");
        deleteImg.src = "/images/icon/delete.svg";
        deleteImg.classList.add("place__delete-btn__img");
        placeDeleteBtn.appendChild(deleteImg);

        placeInfo.appendChild(placeLocation);
        placeInfo.appendChild(placeDescription);
        placeInfo.appendChild(placeCategory);

        li.appendChild(placeImages);
        li.appendChild(placeInfo);
        li.appendChild(placeDeleteBtn);

        placeContainer.appendChild(li);

        // 2. modal 내부 내용을 삭제
        deleteBtn.click();

        modalPlaceCategory.querySelector("select").value = "";
        modalPlaceLocation.querySelector("select").value = "";
        modalPlaceDescription.querySelector("textarea").value = "";

        const placeMessage = document.querySelector(".place__message");
        if (!placeMessage.classList.contains("hidden"))
            placeMessage.classList.toggle("hidden");
        // 3. modal 창을 닫기
        closeBtn.click();
    }

    const placeContainer = document.querySelector(".place__container");

    placeContainer.onclick = function (e) {
        if (e.target.classList.contains("place__delete-btn__img")) {
            const targetNode = e.target.parentNode.parentNode;
            const placeItemList = placeContainer.querySelectorAll(".place__item");

            for(let i = 0; i < placeItemList.length; i++) {
                if(placeItemList[i] === targetNode)
                    inputFileLists.splice(i, 1);
            }
                
            placeContainer.removeChild(e.target.parentNode.parentNode);
        }
    }

    placeContainer.onchange = function () {
        if (placeContainer.childElementCount > 0)
            document.querySelector(".place__message").classList.toggle("hidden");
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (e) {
        if (e.target === modal) {
            modal.style.display = "none";
            body.style.overflow = "auto";
        }
    }
    //TODO 1. ul 내부에 자식요소가 존재할 때만 삭제 버튼이 보이게 설정 
    //TODO 2. ul 내부에 자식요소가 존재해 이미지 슬라이더가 보이는 경우 사진 업로드 버튼 사라지게 설정
    //TODO 3. 저장 버튼을 누르면 테마 등록하기 페이지에 병합 
    //TODO 4. 
    const realUploadBtn = document.querySelector(".modal__upload-btn");
    const uploadBtn = document.querySelector(".modal__image-upload");

    uploadBtn.addEventListener("click", () => realUploadBtn.click());
    realUploadBtn.addEventListener("change", getImageFiles);

    function createPreviewElement(src) {
        const li = document.createElement("li");
        const img = document.createElement("img");

        img.src = src;
        li.classList.add("slide");
        li.classList.add("active");
        li.appendChild(img);

        return li;
    }

    function initThemeImageSlider() {
        const themeSlider = document.querySelector('.theme__image-preview');
        const themeSlides = Array.from(themeSlider.querySelectorAll('li'));

        let isDragging = false;
        let startPos = 0;
        let currentTranslate = 0;
        let prevTranslate = 0;
        let currentIndex = 0;

        themeSlides.forEach((slide) => {
            slide.addEventListener('touchstart', touchStart);
            slide.addEventListener('touchmove', touchMove);
            slide.addEventListener('touchend', touchEnd);
        });

        function touchStart(event) {
            const touch = event.touches[0];
            startPos = touch.clientX;
            isDragging = true;
            themeSlider.classList.add('grabbing');
        }

        function touchMove(event) {
            if (!isDragging) return;
            const touch = event.touches[0];
            const currentPos = touch.clientX;
            const diff = currentPos - startPos;
            currentTranslate = prevTranslate + diff;
        }

        function touchEnd() {
            isDragging = false;
            const movedBy = currentTranslate - prevTranslate;

            if (movedBy < -100 && currentIndex < themeSlides.length - 1) {
                currentIndex++;
            } else if (movedBy > 100 && currentIndex > 0) {
                currentIndex--;
            }

            setPositionByIndex();
            themeSlider.classList.remove('grabbing');
        }

        function setPositionByIndex() {
            currentTranslate = currentIndex * -100;
            prevTranslate = currentTranslate;
            setSliderPosition();
        }

        function setSliderPosition() {
            themeSlider.style.transform = `translateX(${currentTranslate}%)`;
        }
    }

    function initModalImageSlider() {
        const modalSlider = document.querySelector('.modal__image-preview');
        const modalSlides = Array.from(modalSlider.querySelectorAll('li'));
        const uploadBtn = document.querySelector(".modal__image-upload");

        let isDragging = false;
        let startPos = 0;
        let currentTranslate = 0;
        let prevTranslate = 0;
        let currentIndex = 0;

        modalSlides.forEach((slide) => {
            slide.addEventListener('touchstart', touchStart);
            slide.addEventListener('touchmove', touchMove);
            slide.addEventListener('touchend', touchEnd);
        });

        uploadBtn.classList.toggle("hidden");

        function touchStart(event) {
            const touch = event.touches[0];
            startPos = touch.clientX;
            isDragging = true;
            modalSlider.classList.add('grabbing');
        }

        function touchMove(event) {
            if (!isDragging) return;
            const touch = event.touches[0];
            const currentPos = touch.clientX;
            const diff = currentPos - startPos;
            currentTranslate = prevTranslate + diff;
        }

        function touchEnd() {
            isDragging = false;
            const movedBy = currentTranslate - prevTranslate;

            if (movedBy < -100 && currentIndex < modalSlides.length - 1) {
                currentIndex++;
            } else if (movedBy > 100 && currentIndex > 0) {
                currentIndex--;
            }

            setPositionByIndex();
            modalSlider.classList.remove('grabbing');
        }

        function setPositionByIndex() {
            currentTranslate = currentIndex * -100;
            prevTranslate = currentTranslate;
            setSliderPosition();
        }

        function setSliderPosition() {
            modalSlider.style.transform = `translateX(${currentTranslate}%)`;
        }
    }

    function getImageFiles(e) {
        const files = e.currentTarget.files;
    
        const imageMessage = modal.querySelector(".modal__place-img__header").querySelector("div");
        const isMessageHidden = imageMessage.classList.contains("hidden");

        if (!isMessageHidden)
            imageMessage.classList.toggle("hidden");
        // 서버에 업로드 요청

        // 업로드 응답이오면 파일의 주소가 옴

        // img.src = 주소
        const modalImagePreviewContainer = document.querySelector(".modal__image-preview__container");
        const modalImagePreview = modalImagePreviewContainer.querySelector(".modal__image-preview");
        const deleteBtn = modalImagePreviewContainer.querySelector(".modal__delete-btn");

        // const themeImagePreview = document.querySelector(".theme__image-preview"); 

        // 업로드 파일 개수 검사
        if ([...files].length >= 6) {
            alert('이미지는 최대 5개 까지 업로드가 가능합니다.');
            return;
        }

        // 파일 타입 검사 및 imagePreview node에 병합 
        [...files].map((file) => {
            if (!file.type.match("image/.*")) {
                alert('이미지 파일만 업로드가 가능합니다.');
                return;
            }

            const src = URL.createObjectURL(file);
            const modalPreview = createPreviewElement(src);
            // const themePreview = createPreviewElement(src);
            modalImagePreview.appendChild(modalPreview);
            // themeImagePreview.appendChild(themePreview);
        });

        deleteBtn.classList.toggle("hidden");

        initModalImageSlider();
        // initThemeImageSlider();

        // e.currentTarget.value = "";
    }
});

window.addEventListener("load", () => {
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

    themeSaveBtn.onclick = async function () {
        let placeImagePathList = await uploadInputImage();
        
        const isComplete = checkCompleteTheme();
        if (!isComplete) return;

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
                let imageOrder = j;

                placeImages[j] = {
                    "placeId": null,
                    "path": null,
                    "order": imageOrder
                }
            }

            placesImages[i] = placeImages;
        }

        const newThemeId = await uploadTheme(theme);

        if (!newThemeId) {
            displayErrorMessage();
            return;
        }

        // 각 장소에 테마 id 넣기
        places.forEach((place) => {
            place.travelThemeId = newThemeId;
        });

        const placesIds = await uploadPlaces(places);
        let placeImagePathListIndex = 0;
        // 각 이미지에 place의 id 및 주소 입력
        for (let i = 0; i < placesImages.length; i++) {
            let placeImages = placesImages[i];

            for(let j = 0; j < placeImages.length; j++) {
                let placeImage = placeImages[j];
                placeImage.placeId = placesIds[i];
                placeImage.path = placeImagePathList[placeImagePathListIndex++];
            }
            // placesImages[i].forEach((placeImage) => {
            //     placeImage.placeId = placesIds[i];
            // })
        }

        const data = {
            "placesImages": placesImages,
            "travelThemeId": newThemeId
        }

        fetch("/my/upload-img", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (response.redirected) {
                window.location.href = response.url; // 리디렉션 수행
            }
        })
    }

    async function uploadInputImage() {
        const formData = new FormData();

        inputFileLists.forEach((inputFileList) => {
            inputFileList.forEach((file) => {
                formData.append("image", file);
            })
        })

        try {
            const response = await fetch("/my/upload-file", {
                method: "POST",
                body: formData
            })

            const data = await response.json();
            return data;
        } catch (error) {
            console.log(error);
            return null;
        }
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

    async function uploadPlacesImages(placesImages) {
        for (const placeImages of placeImages) {
            for (const placeImage of placeImages) {

            }
        }
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

        if (isEmptyThemeTitle) {
            const themeTitleMessage = themeTitle.querySelector(".theme__title__header").querySelector("div");
            themeTitleMessage.classList.remove("hidden");
            return;
        }

        if (isEmptyThemeDescription) {
            const themeDescriptionMessage = themeDescription.querySelector(".theme__description__header").querySelector("div");
            themeDescriptionMessage.classList.remove("hidden");
            return;
        }

        if (isIncompleteDate) {
            const themeBookableDateMessage = themeBookableDate.querySelector(".theme__bookable-date__header").querySelector("div");
            themeBookableDateMessage.classList.remove("hidden");
            return;
        }

        if (isIncompleteTime) {
            const themePreferredTimeMessage = themePreferredTime.querySelector(".theme__preferred-time__header").querySelector("div");
            themePreferredTimeMessage.classList.remove("hidden");
            return;
        }

        return true;
    }
});