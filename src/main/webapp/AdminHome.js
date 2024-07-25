const pendingList = document.getElementById("pending-list");
const readyList= document.getElementById("ready-list")
const completeList=document.getElementById("complete-list");
const confirmList=document.getElementById("confirm-list");
const homeBtn=document.getElementById("home-btn");
const confirmBtn=document.getElementById("confirm-btn");
const pendingBtn=document.getElementById("pending-btn");
const deliveryBtn=document.getElementById("delivery-btn");
const completeBtn=document.getElementById("complete-btn");
const listContainer=document.getElementById("list-container");
const homeContent=document.getElementById("home-content");
function pending(){
    listContainer.style.display='block';
    homeContent.style.display='none';
    homeBtn.style.color='#36c2ce';
    confirmBtn.style.color='#36c2ce';
    completeBtn.style.color='#36c2ce';
    deliveryBtn.style.color='#36c2ce';
    pendingBtn.style.color=' #264653';
    confirmList.style.display='none';
    completeList.style.display='none';
    readyList.style.display='none';
    pendingList.style.display='block';
    pendingList.innerHTML="";
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
                 <div class="card-container">
    <div class="bike-card">
        <img class="bikeImg" src="./image/bike.jpg" alt="Bike Image">
        <div class="card-body">
            <h5 class="card-title">Bike Model: ${list.bikeModel}</h5>
            <p class="card-text">Bike Number: ${list.bikeNumber}</p>
            <p class="card-text">Service Date: ${list.serviceDate}</p>
            <p class="card-text">Service Description: ${list.serviceDescription}</p>
            <button class="book-btn" onclick="changeToDelivery(${list.bikeId},'Delivery')" id="booking_form">Ready for Delivery</button>
        </div>
    </div>
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
    pending();
    }
    else if(status=="Deliverd"){
		alert("Bike Deliverd");
		ready();
		}
	else if(status=="pending")
	{
		alert("Confirm slot");
		confirm();
	}	
	else if(status=="cancel"){
		alert("Cancel Booking");
		confirm();
	}
	
}
function ready(){
    homeContent.style.display='none';
    listContainer.style.display='block';
    homeBtn.style.color='#36c2ce';
    confirmBtn.style.color='#36c2ce';
    completeBtn.style.color='#36c2ce';
    pendingBtn.style.color='#36c2ce';
    deliveryBtn.style.color='#264653';
    confirmList.style.display='none';
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
            readyList.innerHTML +=`
                 <div class="card-container">
    <div class="bike-card">
        <img class="bikeImg" src="./image/bike.jpg" alt="Bike Image">
        <div class="card-body">
            <h5 class="card-title">Bike Model: ${list.bikeModel}</h5>
            <p class="card-text">Bike Number: ${list.bikeNumber}</p>
            <p class="card-text">Service Date: ${list.serviceDate}</p>
            <p class="card-text">Service Description: ${list.serviceDescription}</p>
            <button class="book-btn" onclick="changeToDelivery(${list.bikeId},'Deliverd')" id="booking_form">Confirm Delivered</button>
        </div>
    </div>
</div>`;
        });
    })
    .catch(error => {
        console.error('Error fetching bike data:', error);
    });
}
function complete(){
    homeContent.style.display='none';
    listContainer.style.display='block';
    homeBtn.style.color='#36c2ce';
    confirmBtn.style.color='#36c2ce';
    pendingBtn.style.color='#36c2ce';
    deliveryBtn.style.color='#36c2ce';
    completeBtn.style.color=' #264653';
    confirmList.style.display='none';
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
                 <div class="card-container">
    <div class="bike-card">
        <img class="bikeImg" src="./image/bike.jpg" alt="Bike Image">
        <div class="card-body">
            <h5 class="card-title">Bike Model: ${list.bikeModel}</h5>
            <p class="card-text">Bike Number: ${list.bikeNumber}</p>
            <p class="card-text">Service Date: ${list.serviceDate}</p>
            <p class="card-text">Service Description: ${list.serviceDescription}</p>
        </div>
    </div>
</div>`;
        });
    })
    .catch(error => {
        console.error('Error fetching bike data:', error);
    });
}
function home(){
    homeContent.style.display='flex';
    listContainer.style.display='none';
    confirmBtn.style.color='#36c2ce';
    pendingBtn.style.color='#36c2ce';
    deliveryBtn.style.color='#36c2ce';
    completeBtn.style.color='#36c2ce';
    homeBtn.style.color='#264653';
    confirmList.style.display='none';
    readyList.style.display='none';
    pendingList.style.display='none';
    completeList.style.display='none';
}

function confirm(){
    homeContent.style.display='none';
    listContainer.style.display='block';
    homeBtn.style.color='#36c2ce';
    pendingBtn.style.color='#36c2ce';
    deliveryBtn.style.color='#36c2ce';
    completeBtn.style.color='#36c2ce';
    confirmBtn.style.color='#264653';
    readyList.style.display='none';
    pendingList.style.display='none';
    completeList.style.display='none';
    confirmList.style.display='block';
    confirmList.innerHTML="";
    fetch('ConfirmServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        data.forEach(list => {
            confirmList.innerHTML += `
 <div class="card-container">
    <div class="bike-card">
        <img class="bikeImg" src="./image/bike.jpg" alt="Bike Image">
        <div class="card-body">
            <h5 class="card-title">Bike Model: ${list.bikeModel}</h5>
            <p class="card-text">Bike Number: ${list.bikeNumber}</p>
            <p class="card-text">Service Date: ${list.serviceDate}</p>
            <p class="card-text">Service Description: ${list.serviceDescription}</p>
            <p>vannakam</p>
   			<button class="book-btn"  id="" onclick="changeToDelivery(${list.bikeId},'cancel')">cancel</button>
            <button class="book-btn" onclick="changeToDelivery(${list.bikeId},'pending')" id="booking_form">confirm</button>
        </div>
    </div>
</div>`;
        });
    })
    .catch(error => {
        console.error('Error fetching bike data:', error);
    });

}