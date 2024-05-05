let tours = {}
tours['Сочи'] = '200$'
tours['Чечня'] = '500$'
tours['Дагестан'] = '1000$'
tours['Урал'] = '400$'
tours['Хабаровск'] = '300$'
tours['Ямало-Ненецкий автомный округ'] = '50$'

function AddTour(key, value) {
    tours[key] = value
}

function DeleteTour(key) {
    if(tours.hasOwnProperty(key)) {
        delete tours[key];
        console.log('Deleted ' + key)
    }
    else {
        console.err("No item is found")
    }
}

function GetTourInfo(key) {
    if(tours.hasOwnProperty(key)) {
        if(tours[key] === '') {
            console.err("No info")
            return;
        }
        console.log("Info: " + key + " is " + tours[key]);
    }
    else {
        console.err("No item is found")
    }
}

function ListTours() {
    let string = '';
    for (let key in tours) {
        string += key + ': ' + tours[key] + '\n';
    }
    return string;
}

function Add() {
    let inputName = window.prompt("Введите название тура");
    let inputPrice = window.prompt("Введите цену тура");
    if (inputName.trim() !== null && inputPrice.trim() != null) {
        AddTour(inputName.trim(), inputPrice.trim());
    } else {
        console.error("Input is empty");
    }
}


function Delete() {
    let input = window.prompt("Введите название удаляемого тура")
    if(input != null) {
        DeleteTour(input.trim())
    }
    else {
        console.err("Input is empty")
    }
}

function Info() {
    let input = window.prompt("Введите название тура")
    if(input != null) {
        GetTourInfo(input.trim())
    }
    else {
        console.err("Input is empty")
    }
}

function Menu() {
    console.log(ListTours())
}