
const tripId = document.getElementById("tripId").value;

document.querySelector(".edit").addEventListener("click", (ev) => {
    ev.preventDefault();
    let obj = [];

    let vehicles = document.querySelectorAll(".vehicle")

    for (let i = 0; i < vehicles.length; i++) {
        let vehiclesGroups = {};

        vehiclesGroups['vehicleId'] = vehicles.item(i).id;

        let passengers = [];

        const childElement = vehicles.item(i).children;

        for (let j = 0; j < childElement.length; j++) {

            if (childElement.item(j).classList.contains("passenger")) {
                passengers.push(childElement.item(j).id);
            }
        }

        vehiclesGroups['passengers'] = passengers;
        obj.push(vehiclesGroups);
    }

    let passengersSection = document.querySelector(".passengers");
    const childElement = passengersSection.children;

    let availablePassengers = [];
    for (let j = 0; j < childElement.length; j++) {

        if (childElement.item(j).classList.contains("passenger")) {
            availablePassengers.push(childElement.item(j).id);
        }
    }

    let final = {}

    final['passengers'] = availablePassengers;
    final['carsOrganisations'] = obj

     patchData(final);

    window.location.replace("https://usf1997test.herokuapp.com/portal");
})

async function patchData(obj) {
    const response =  await fetch(`https://usf1997test.herokuapp.com/api/trip/edit/${tripId}`, {
        method: 'PATCH',
        headers : {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(obj)
    });
}

document.querySelector(".add-vehicles").addEventListener("click", (ev) => {
    const id = ev.target.id;
    window.location.replace("https://usf1997test.herokuapp.com/portal/trips/add/vehicles/" + id);
})

document.querySelector(".add-passengers").addEventListener("click", (ev) => {
    const id = ev.target.id;
    window.location.replace("https://usf1997test.herokuapp.com/portal/trips/add/passengers/" + id);
})

let arrow = document.querySelectorAll(".arrow");
for (let i = 0; i < arrow.length; i++) {
    arrow[i].addEventListener("click", (e)=>{
        let arrowParent = e.target.parentElement.parentElement;//selecting main parent of arrow
        arrowParent.classList.toggle("showMenu");
    });
}
let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".bx-menu");
console.log(sidebarBtn);
sidebarBtn.addEventListener("click", ()=>{
    sidebar.classList.toggle("close");
});

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("text");
    ev.target.appendChild(document.getElementById(data));
}