window.addEventListener("load", () => {
    const sections = document.querySelectorAll("section[class=theme]");
    let themeList = [];

    for(let i = 0; i < sections.length; i++) {
        let theme = {
            id: sections[i].id,
            currentSlideIndex: 0,
            images: sections[i].querySelectorAll(".location__image"),
            dots: sections[i].querySelectorAll(".location__dot"),
            prevButton: sections[i].querySelector(".location__prev-button"),
            nextButton: sections[i].querySelector(".location__next-button"), 
        };

        themeList[i] = theme;
    }

    themeList.forEach(theme => {
        showSlides(theme.currentSlideIndex, theme);

        theme.prevButton.addEventListener("click", () => {
            showSlides(theme.currentSlideIndex-1, theme);
        });

        theme.nextButton.addEventListener("click", () => {
            showSlides(theme.currentSlideIndex+1, theme);
        });

        theme.dots.forEach((dot, idx) => {
            dot.addEventListener("click", () => {
                showSlides(idx, theme);
            })
        })
    }) 

    function showSlides(idx, theme) {
        let images = theme.images;
        let dots = theme.dots;
        let len = images.length;

        if(idx < 0)
            idx = len - 1;
        else if(idx > len - 1)
            idx = 0;
        
        for(let i = 0; i < len; i++) {
            images[i].style.display = "none";
            dots[i].className = dots[i].className.replace(" active", "");
        }

        images[idx].style.display = "block";
        dots[idx].className += (" active");
        theme.currentSlideIndex = idx;
    }
});

// let currentSlideIndex = 0;
// showSlides(currentSlideIndex);

// document.querySelector(".location__prev-button").addEventListener("click", () => {
//     // console.log("123");
    
//     showSlides(currentSlideIndex-1);
// })

// document.querySelector(".location__next-button").addEventListener("click", () => {
//     showSlides(currentSlideIndex+1);
// })

// let dots = document.querySelector(".location__nav-dots").querySelectorAll(".location__dot");
// for(let i = 0; i < dots.length; i++) {
//     dots[i].addEventListener("click", () => {
//         showSlides(i);
//     })
// }

// const slider = {
//     currentSlideIndex: 0,
//     showSlides: function(idx) {
//         let slides = document.querySelector(".location__images").querySelectorAll(".location__image");
//         let dots = document.querySelector(".location__nav-dots").querySelectorAll(".location__dot");
//         let len = slides.length;
//         if(idx < 0)
//             idx = len - 1;
//         else if(idx > len - 1)
//             idx = 0;

//         for (let i = 0; i < slides.length; i++) {
//             slides[i].style.display = "none";
//             dots[i].className = dots[i].className.replace(" active", "");
//         }
        
//         slides[idx].style.display = "block";
//         dots[idx].className += " active";
//         currentSlideIndex = idx;
//     }
// }

// function showSlides(idx) {
//     let slides = document.querySelector(".location__images").querySelectorAll(".location__image");
//     let dots = document.querySelector(".location__nav-dots").querySelectorAll(".location__dot");
//     let len = slides.length;
//     if(idx < 0)
//         idx = len - 1;
//     else if(idx > len - 1)
//         idx = 0;

//     for (let i = 0; i < slides.length; i++) {
//         slides[i].style.display = "none";
//         dots[i].className = dots[i].className.replace(" active", "");
//     }
    
//     slides[idx].style.display = "block";
//     dots[idx].className += " active";
//     currentSlideIndex = idx;
    
// }