const bikeList = document.getElementById("bike-list");
const serviceForm= document.getElementById("service-form")
const addBikeForm=document.getElementById("addbike-form");
const bikeNo=document.getElementById("bike-no");
const bikeIdInput = document.getElementById("bike-id");
const bikeModelInput = document.getElementById("bike-model");
const bikeNumberInput = document.getElementById("bike-number");
const service=document.getElementById("service-info");
document.addEventListener('DOMContentLoaded', () => {
    fetch('BikeServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(bike => {
            bikeList.innerHTML += `
                <div class="bike-card">
                    <p class="b-id">${bike.bikeId}</p>
                    <p class="b-model">${bike.bikeModel}</p>
                    <p class="b-number">${bike.bikeNumber}</p>
                    <p class="service-info" id="service-info">wait for comformation</p>
                    <button class="book-btn" onclick="bookService(${bike.bikeId},'${bike.bikeModel}','${bike.bikeNumber}')" id="booking_form">Book Service</button>
                </div>`;
        });
    })
    .catch(error => {
        console.error('Error fetching bike data:', error);
    });
});


function addBike() {
	bikeList.style.display='none';
	serviceForm.style.display='none';
    addBikeForm.style.display = 'block';
    
}

function cancel() {
	serviceForm.style.display='none';
    addBikeForm.style.display = 'none';
    bikeList.style.display='block';
}

function bookService(bikeId,bikeModel,bikeNumber) {
	bikeNo.innerHTML=bikeModel;
	bikeIdInput.value = bikeId;
    bikeModelInput.value = bikeModel;
    bikeNumberInput.value = bikeNumber;
	bikeList.style.display = 'none';
    addBikeForm.style.display='none';
	serviceForm.style.display='block';
    console.log('Booking service for bike ID:', bikeModel);
}
function bookAlert(){
	alert("Check your mail for conformation.");
}


   
