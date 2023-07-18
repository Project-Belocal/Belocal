window.addEventListener("load", () => {
    const main = document.querySelector("main");
    const sections = document.querySelectorAll("section[class=theme]");
    
    main.addEventListener("click", function(e) {
        let el = e.target;

        if(el.classList.contains("heart-filled")) {
            e.preventDefault();

            const themeSection = el.closest(".theme");

            themeSection.addEventListener("transitionend", function() {
                themeSection.classList.add("hidden");
            })
            themeSection.classList.add("slide-to-left");

            let {memberId, travelThemeId} = el.dataset;
            
            fetch(`/api/wishlists/${travelThemeId}/members/${memberId}`, {
                method: "DELETE"
            })
            .then(response => response.json())
            .then(result => {
                if(result === 1)
                    console.log("clear");
            })
        }
    })

    sections.forEach(section => {
        initThemeImageSlider(section);
    })

    function initThemeImageSlider(section) {
        const themeContainer = section.querySelector(".theme__image-preview__container");
        const themeSlider = section.querySelector('.theme__image-preview');
        const themeSlides = Array.from(themeSlider.querySelectorAll('li'));
        const prevBtn = themeContainer.querySelector(".theme__image-preview__prev-button");
        const nextBtn = themeContainer.querySelector(".theme__image-preview__next-button");
        const dots = section.querySelectorAll(".theme__image-preview__dot");
        
        let isDragging = false;
        let startPos = 0;
        let currentTranslate = 0;
        let prevTranslate = 0;
        let currentIndex = 0;
        let prevIndex = 0;

        themeSlides.forEach((slide) => {
            slide.addEventListener('touchstart', touchStart);
            slide.addEventListener('touchmove', touchMove);
            slide.addEventListener('touchend', touchEnd);
        });

        prevBtn.addEventListener("click", slideToPrevious);
        nextBtn.addEventListener("click", slideToNext);

        dots[currentIndex].classList.add("active");

        function touchStart(event) {
            const touch = event.touches[0];
            startPos = touch.clientX;
            isDragging = true;
            themeSlider.classList.add('grabbing');
        }

        function touchMove(event) {
            if (!isDragging) 
                return;
            const touch = event.touches[0];
            const currentPos = touch.clientX;
            const diff = currentPos - startPos;
            currentTranslate = prevTranslate + diff;
        }

        function touchEnd() {
            isDragging = false;
            const movedBy = currentTranslate - prevTranslate;

            if (movedBy < -100 && currentIndex < themeSlides.length - 1) {
                prevIndex = currentIndex;
                currentIndex++;
            } else if (movedBy > 100 && currentIndex > 0) {
                prevIndex = currentIndex;
                currentIndex--;
            }

            setPositionByIndex();
            themeSlider.classList.remove('grabbing');
        }

        function slideToNext() {
            if(currentIndex < themeSlides.length - 1) {
                prevIndex = currentIndex;
                currentIndex++;
            }

            setPositionByIndex();
        }

        function slideToPrevious() {
            if(currentIndex > 0) {
                prevIndex = currentIndex;
                currentIndex--;
            }

            setPositionByIndex();
        }


        function setPositionByIndex() {
            dots[prevIndex].classList.remove("active");
            dots[currentIndex].classList.add("active");

            currentTranslate = currentIndex * -100;
            prevTranslate = currentTranslate;
            setSliderPosition();
        }

        function setSliderPosition() {
            themeSlider.style.transform = `translateX(${currentTranslate}%)`;
        }
    }

    // let themeList = [];

    // for(let i = 0; i < sections.length; i++) {
    //     let theme = {
    //         id: sections[i].id,
    //         currentSlideIndex: 0,
    //         images: sections[i].querySelectorAll(".theme__image"),
    //         dots: sections[i].querySelectorAll(".theme__image-preview__dot"),
    //         prevButton: sections[i].querySelector(".theme__image-preview__prev-button"),
    //         nextButton: sections[i].querySelector(".theme__image-preview__next-button"), 
    //     };

    //     themeList[i] = theme;
    // }
    
    // themeList.forEach(theme => {
    //     showSlides(theme.currentSlideIndex, theme);

    //     theme.prevButton.addEventListener("click", () => {
    //         showSlides(theme.currentSlideIndex-1, theme);
    //     });

    //     theme.nextButton.addEventListener("click", () => {
    //         showSlides(theme.currentSlideIndex+1, theme);
    //     });

    //     theme.dots.forEach((dot, idx) => {
    //         dot.addEventListener("click", () => {
    //             showSlides(idx, theme);
    //         })
    //     })
    // }) 

    // function showSlides(idx, theme) {
    //     let images = theme.images;
    //     let dots = theme.dots;
    //     let len = images.length;

    //     if(idx < 0)
    //         idx = len - 1;
    //     else if(idx > len - 1)
    //         idx = 0;
        
    //     for(let i = 0; i < len; i++) {
    //         images[i].style.display = "none";
    //         dots[i].className = dots[i].className.replace(" active", "");
    //     }

    //     images[idx].style.display = "block";
    //     dots[idx].className += (" active");
    //     theme.currentSlideIndex = idx;
    // }
});