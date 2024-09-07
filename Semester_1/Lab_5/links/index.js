var FormA = [
    {label:'Фамилия', elemtype:'input', type:'text', restriction:'required', name:'name', max: '200'},
    {label:'Имя', elemtype:'input', type:'text', restriction:'required', name:'name', max: '200'},
    {label:'Отчество', elemtype:'input', type:'text', restriction:'required', name:'name', max: '200'},
    {label:'Телефон', elemtype:'input', type:'tel', restriction:'required', name:'phone-number', min:'13', max:'13'},
    {label:'Email', elemtype:'input',type:'email', restriction:'', name:'email'},
    {label:'Дата заезда', elemtype:'input', type:'date', restriction:'required', name:'arrival-date'},
    {label:'Дата выезда', elemtype:'input', type:'date', restriction:'required', name:'leave-date'},
    {label:'Отправить', elemtype:'button', type:'submit', name:'submit'}
]

const validationRules = {
    'name': /^[A-Za-zА-Яа-яЁё\s]+$/,
    'phone-number': /^\+\d{12}$/,
}


const form = document.getElementById('form')

form.addEventListener('input', function(event) {
    const input = event.target
    const fieldName = input.name
    const value = input.value

    if (validationRules[fieldName] && !validationRules[fieldName].test(value)) {
        showError(input, 'Неверный формат')
    } else {
        hideError(input)
    }
})

form.addEventListener('submit', function(event) {
    event.preventDefault()
    const inputs = form.querySelectorAll('input')
    inputs.forEach(input => {
        const fieldName = input.name
        const value = input.value

        if (validationRules[fieldName] && !validationRules[fieldName].test(value)) {
            showError(input, 'Неверный формат')
        } else {
            hideError(input)
        }
    })

    const arrivalInput = document.querySelector('input[name="arrival-date"]')
    const leaveInput = document.querySelector('input[name="leave-date"]')
    const arrivalDate = new Date(arrivalInput.value)
    const leaveDate = new Date(leaveInput.value)

    if (arrivalDate >= leaveDate) {
        showError(leaveInput, 'Дата заезда должна быть до даты выезда')
        return
    }
    hideError(leaveInput)
    document.location.reload()
})

function showError(input, message) {
    let error = input.nextElementSibling;
    if (!error || !error.classList.contains('error-message')) { 
        error = document.createElement('span')
        error.classList.add('error-message')
        input.parentNode.insertBefore(error, input.nextSibling)
    }
    error.textContent = message
}

function hideError(input) {
    const error = input.nextElementSibling
    if (error && error.classList.contains('error-message')) { 
        error.textContent = ''
    }
}

function createForm(data) {
    const form = document.getElementById("form")
    data.forEach(element => {
        switch (element.elemtype) {
            case "button":
                const button = document.createElement(element.elemtype)
                button.classList.add(element.name)
                button.setAttribute('type', element.type)
                button.setAttribute('name', element.name)
                button.textContent = element.label
                form.appendChild(button)
                break
            case "input":
                const label = document.createElement('label')
                label.textContent = element.label
                const input = document.createElement(element.elemtype)
                input.classList.add(element.name)
                input.setAttribute('type', element.type || 'text')
                input.setAttribute('name', element.name)
                if (element.restriction === 'required') {
                    input.required = true
                }
                if (element.min) {
                    input.setAttribute('min', element.min)
                }
                if (element.max) {
                    input.setAttribute('max', element.max)
                }
                if (element.pattern) {
                    input.setAttribute('pattern', element.pattern)
                }
                form.appendChild(label)
                form.appendChild(input)
                break
        }
    })
}

createForm(FormA)
