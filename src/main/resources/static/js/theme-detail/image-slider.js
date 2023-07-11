window.addEventListener("load", () => {
    initThemeImageSlider();

    function initThemeImageSlider() {
        const themeSlider = document.querySelector('.theme__image-preview');
        const themeSlides = Array.from(themeSlider.querySelectorAll('li'));
        console.log(themeSlides);
        
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
})