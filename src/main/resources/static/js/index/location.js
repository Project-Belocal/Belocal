let currentSlideIndex = 0;
showSlides(currentSlideIndex);

document.querySelector(".location__prev-button").addEventListener("click", () => { 
    showSlides(currentSlideIndex-1);
})

document.querySelector(".location__next-button").addEventListener("click", () => {
    showSlides(currentSlideIndex+1);
})

let dots = document.querySelector(".location__nav-dots").querySelectorAll(".location__dot");
for(let i = 0; i < dots.length; i++) {
    dots[i].addEventListener("click", () => {
        showSlides(i);
    })
}

function showSlides(idx) {
    let slides = document.querySelector(".location__images").querySelectorAll(".location__image");
    let dots = document.querySelector(".location__nav-dots").querySelectorAll(".location__dot");
    let len = slides.length;
    if(idx < 0)
        idx = len - 1;
    else if(idx > len - 1)
        idx = 0;

    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
        dots[i].className = dots[i].className.replace(" active", "");
    }
    
    slides[idx].style.display = "block";
    dots[idx].className += " active";
    currentSlideIndex = idx;
    
}