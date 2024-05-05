import { fromEvent } from 'rxjs';
import { ajax } from 'rxjs/ajax';
import "./styles/styles.css"

const showButton = document.getElementById('show-data-btn');
const deleteButton = document.getElementById('delete-btn');
const dataContainer = document.getElementById('data-container');

const showFlights = (data) => {
    dataContainer.innerHTML = '';

    data.flights.forEach(flight => {
        const row = document.createElement('tr');
        const departureCityColumn = document.createElement("th");
        departureCityColumn.textContent = `${flight.departure_city}`;
        const arrivalCityColumn = document.createElement('th');
        arrivalCityColumn.textContent =  `${flight.arrival_city}`;
        const departureTimeColumn = document.createElement('th');
        departureTimeColumn.textContent = ` ${flight.departure_time}`;
        const arrivalTimeColumn = document.createElement('th');
        arrivalTimeColumn.textContent = ` ${flight.arrival_time}`;
    
        row.append(departureCityColumn);
        row.append(arrivalCityColumn);
        row.append(departureTimeColumn);
        row.append(arrivalTimeColumn);
    
        dataContainer.append(row);
    });
}

function deleteLastRow() {
    const rows = dataContainer.querySelectorAll('tr');

    if(rows.length > 0) {
        const lastRow =  rows[rows.length - 1];
        lastRow.remove();
    }
};

function loadData() {
    const url = 'http://localhost:8080/download';

    ajax.getJSON(url).subscribe({
        next: data => {
            console.log('Received data:', data);
            showFlights(data);
        },
        error: error => console.log('Error fetching data:', error)
    });
}


fromEvent(showButton, 'click').subscribe(() => loadData());
fromEvent(deleteButton, 'click').subscribe(() => deleteLastRow());
