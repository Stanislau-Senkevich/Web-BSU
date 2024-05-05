class THashStorage {
    constructor() {
        this.storage = {
            'Сочи':'200$',
            'Чечня':'500$',
            'Дагестан':'1000$',
            'Урал':'500$',
            'Ямало-Ненецкий автомный округ':'20$',
        };
    }

    AddTour(key, value) {
        this.storage[key] = value
    }
    
    DeleteTour(key) {
        if(this.storage.hasOwnProperty(key)) {
            delete this.storage[key];
            console.log('Deleted ' + key)
        }
        else {
            console.err("No item is found")
        }
    }
    
    GetTourInfo(key) {
        if(this.storage.hasOwnProperty(key)) {
            if(this.storage[key] === '') {
                console.err("No info")
                return;
            }
            console.log("Info: " + key + " is " + this.storage[key]);
        }
        else {
            console.err("No item is found")
        }
    }
    
    ListTours() {
        let string = '';
        for (let key in this.storage) {
            string += key + ': ' + this.storage[key] + '\n';
        }
        return string;
    }

    Reset() {
        this.storage = {};
    }
}

let storage = new THashStorage();

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