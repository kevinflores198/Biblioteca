const iconomenu = document.querySelector('#fa-solid'),
menu = document.querySelector('#container-items')

iconomenu.addEventListener('click', (e) => {
    menu.classList.toggle('active');
    document.body.classList.toggle('opacity');

});