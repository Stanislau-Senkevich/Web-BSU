class TLocalStorage {
    constructor() {}

    AddTour(key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    }
    
    DeleteTour(key) {
        if (localStorage.getItem(key)) {
            localStorage.removeItem(key);
            console.log('Deleted ' + key + ' from local storage.');
        } else {
            console.error("No item was found in local storage.");
        }
    }
    
    GetTourInfo(key) {
        const storedValue = localStorage.getItem(key);
        if (storedValue !== null) {
            console.log("Info for key '" + key + "': " + JSON.parse(storedValue));
        } else {
            console.error("No item is found in local storage.");
        }
    }
    
    ListTours() {
        let string = '';
        for (let i = 0; i < localStorage.length; i++) {
            const key = localStorage.key(i);
            const value = JSON.parse(localStorage.getItem(key));
            string += key + ': ' + value + '\n';
        }
        return string;
    }

    Reset() {
        localStorage.clear();
        console.log('Local storage cleared.');
    }
}

let storage = new TLocalStorage();

function Add() {
    let inputName = window.prompt("Введите название тура");
    let inputPrice = window.prompt("Введите цену тура");
    if (inputName.trim() !== null && inputPrice.trim() != null) {
        storage.AddTour(inputName.trim(), inputPrice.trim());
    } else {
        console.error("Input is empty");
    }
}


function Delete() {
    let input = window.prompt("Введите название удаляемого тура")
    if(input != null) {
        storage.DeleteTour(input.trim())
    }
    else {
        console.err("Input is empty")
    }
}

function Info() {
    let input = window.prompt("Введите название тура")
    if(input != null) {
        storage.GetTourInfo(input.trim())
    }
    else {
        console.err("Input is empty")
    }
}

function Menu() {
    console.log(storage.ListTours())
}
