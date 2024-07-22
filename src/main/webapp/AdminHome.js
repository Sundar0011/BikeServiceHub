const pendingList = document.getElementById("pending-list");
const readyList= document.getElementById("ready-list")
const completeList=document.getElementById("complete-list");
function pending(){
    readyList.style.display='none';
    completeList.style.display='none';
    pendingList.style.display='block';
    console.log("pending function");
    readyList.innerHTML="";
    fetch('pendingServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(list => {
            pendingList.innerHTML += `
                <div class="bike-card">
                	<p class="b-number">${list.bikeId}</p>
                    <p class="b-number">${list.bikeModel}</p>
                    <p class="b-number">${list.bikeNumber}</p>
                    <p class="b-id">${list.serviceDate}</p>
                    <p class="b-model">${list.serviceDescription}</p>
                    <button class="book-btn" onclick="changeToDelivery(${list.bikeId},'Delivery')" id="booking_form">Ready for Delivery</button>
                   
                </div>`;
        });
    })
    .catch(error => {
        console.error('Error fetching bike data:', error);
    });
}
function changeToDelivery(bikeId, status) {
    const params = new URLSearchParams();
    console.log(bikeId);
    params.append('bikeId', bikeId);
    params.append('status', status);
	
    fetch('changeStatus', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: params
    })
      .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Status updated successfully');
            // Optionally, refresh the list
            pending();
        } else {
            alert('Failed to update status');
        }
    })
    .catch(error => {
        console.error('Error updating status:', error);
    });
    if(status=="Delivery"){
    alert("Ready to delivery");
    ready();
    }
    else if(status=="Deliverd"){
		alert("Bike Deliverd");
		complete();
		}
    
}
function ready(){
   
    completeList.style.display='none';
    pendingList.style.display='none';
    readyList.style.display='block';
    readyList.innerHTML="";
    fetch('readyServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(list => {
            readyList.innerHTML += `
                <div class="bike-card">
                	<p class="b-number">${list.bikeId}</p>
                    <p class="b-number">${list.bikeModel}</p>
                    <p class="b-number">${list.bikeNumber}</p>
                    <p class="b-id">${list.serviceDate}</p>
                    <p class="b-model">${list.serviceDescription}</p>
                    <button class="book-btn" onclick="changeToDelivery(${list.bikeId},'Deliverd')" id="booking_form">Confirm Delivered</button>
                   
                </div>`;
        });
    })
    .catch(error => {
        console.error('Error fetching bike data:', error);
    });
}
function complete(){
    readyList.style.display='none';
    pendingList.style.display='none';
    completeList.style.display='block';
    completeList.innerHTML="";
    fetch('completeServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(list => {
            completeList.innerHTML += `
                <div class="bike-card">
                	<p class="b-number">${list.bikeId}</p>
                    <p class="b-number">${list.bikeModel}</p>
                    <p class="b-number">${list.bikeNumber}</p>
                    <p class="b-id">${list.serviceDate}</p>
                    <p class="b-model">${list.serviceDescription}</p>
                    <button class="book-btn"  id="booking_form">deliverd</button>
                   
                </div>`;
        });
    })
    .catch(error => {
        console.error('Error fetching bike data:', error);
    });
}
function home(){
    readyList.style.display='none';
    pendingList.style.display='none';
    completeList.style.display='none';
}

