const tabs = document.querySelectorAll('.tab');
const tabContent = document.querySelectorAll('.tab-panel');

tabs.forEach((tab) => {
    tab.addEventListener('click', () => {
        const target = document.querySelector(tab.dataset.target);


        tabContent.forEach((tc) => {
            tc.classList.remove('is-active');
        });
        target.classList.add('is-active');



        tabs.forEach((t) => {
            t.classList.remove('is-active');
        });
        tab.classList.add('is-active');


    });
});


