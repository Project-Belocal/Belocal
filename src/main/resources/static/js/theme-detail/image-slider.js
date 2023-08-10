window.addEventListener("load", () => {
    initThemeImageSlider();

    function initThemeImageSlider() {
        const themeContainer = document.querySelector(".theme__image-preview__container");
        const themeSlider = document.querySelector('.theme__image-preview');
        const themeSlides = Array.from(themeSlider.querySelectorAll('li'));
        const prevBtn = themeContainer.querySelector(".theme__image-preview__prev-button");
        const nextBtn = themeContainer.querySelector(".theme__image-preview__next-button");
        const dots = document.querySelectorAll(".theme__image-preview__dot");
        
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
})