const menuA = [
    {
        name: "Туры",
        submenu: [
            { 
                name: "Золотой тур по России", 
                url: "https://www.youtube.com/watch?v=dQw4w9WgXcQ" },
            {
                name: "Иностранные путевки",
                submenu: [
                    { 
                        name: "Италия", url: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
                    },
                    {
                        name: "Вьетнам", url: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
                    },
                    {
                        name: "Франция", url: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
                    },
                    { 
                        name: "Германия", url: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
                    },
                ]
            },
        ]
    },
    { 
        name: "Страховка",
        submenu: [
            {name: "Условия договора", url: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"},
            {name: "Тарифы", url: "https://www.youtube.com/watch?v=dQw4w9WgXcQ"},
        ]
    },
];

const menu = document.getElementById('menu');

function createHeaderSubmenu(header, menuItem) {
    const submenuContainer = document.createElement('div');
    submenuContainer.classList.add('submenu-container');

    header.addEventListener('click', () => {
        hide();
        submenuContainer.style.display = submenuContainer.style.display === 'block' ? 'none' : 'block';
    });

    header.appendChild(submenuContainer);
    show(menuItem.submenu, submenuContainer);
}

function createSubmenu(item, submenuItem) {
    const submenuContainer = document.createElement('div');
    submenuContainer.classList.add('submenu-container');

    submenuItem.appendChild(submenuContainer);

    submenuItem.addEventListener('click', (event) => {
        event.stopPropagation();
        submenuContainer.style.display = submenuContainer.style.display === 'block' ? 'none' : 'block';
        if (submenuContainer.style.display === 'block') {
            submenuContainer.style.left = submenuItem.offsetWidth + 'px';
            submenuContainer.style.top = submenuItem.offsetTop + 'px';
        }
    });

    show(item.submenu, submenuContainer);
}

function createHeaders(menuArray) {
    menuArray.forEach(menuItem => {
        const header = document.createElement('div');
        header.classList.add('header');

        const link = document.createElement('a');
        link.setAttribute('href', menuItem.url || '#');
        link.classList.add('link');
        link.textContent = menuItem.name;
        header.appendChild(link);
        menu.appendChild(header);

        if (menuItem.submenu) {
            createHeaderSubmenu(header, menuItem)
        }
    });
}

function show(submenuArray, parent) {
    submenuArray.forEach(item => {
        const submenuItem = document.createElement('div');
        submenuItem.classList.add('submenu-item');

        const link = document.createElement('a');
        link.setAttribute('href', item.url || '#');
        link.textContent = item.name;

        submenuItem.appendChild(link);
        parent.appendChild(submenuItem);

        if (item.submenu) {
            createSubmenu(item, submenuItem)
        }
    });
}


function hide() {
    const allSubmenuContainers = document.querySelectorAll('.submenu-container');
    allSubmenuContainers.forEach(container => {
        container.style.display = 'none';
    });
}


createHeaders(menuA);
