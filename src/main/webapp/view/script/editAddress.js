document.addEventListener('DOMContentLoaded', function() {
    const cancelBtn = document.getElementById('cancelBtn');
    cancelBtn.addEventListener('click', function() {
        location.href = 'profile.jsp';
    });

	const updateBtn = document.getElementById('updateBtn');
	    updateBtn.addEventListener('click', function() {
			
			console.log("updateBtn clicked");
			
	        const addressId = document.getElementById('addressId').value;
	        const postal = document.getElementById('postal').value;
	        const floor = document.getElementById('floor').value;
	        const unit = document.getElementById('unit').value;
			
			const urlParams = new URLSearchParams(window.location.search);
			const byAdmin = urlParams.get("byAdmin") || false;
			

	        fetch(`${contextPath}/api/address`, {
	            method: "PUT",
	            headers: {
	                "Content-Type": "application/json"
	            },
	            body: JSON.stringify({
	                addressId: addressId,
	                postal: postal,
	                floor: floor,
	                unit: unit,
					byAdmin: byAdmin
	            })
	        }).then(response => response.json())
			  .then(() => location.href=`${contextPath}/view/profile.jsp`)
			  .catch(error => {
				console.error('Error:', error)
				location.href=`${contextPath}/view/profile.jsp`
			});
	    });
});