document.addEventListener("DOMContentLoaded", async () => {
	const delBtns = document.getElementsByClassName("delete-button-address");
	for (let i = 0; i < delBtns.length; i++) {
		
		delBtns[i].addEventListener("click", function() {
			const addressId = this.getAttribute("data-address-id");
			fetch(`/JAD_CA1/api/address/${addressId}`, {
				method: "DELETE"
			}).then(() => location.href = "manageUser")
				.catch(error => {
					console.error('Error:', error)
					location.href = "manageUser"
				});


		});
	}
	
	const delUserBtns = document.getElementsByClassName("delete-button-user");
		for (let i = 0; i < delBtns.length; i++) {
			
			delUserBtns[i].addEventListener("click", function() {
				
				const userId = this.getAttribute("data-user-id");
				fetch(`/JAD_CA1/api/user/${userId}`, {
					method: "DELETE"
				}).then(() => location.href = "manageUser")
					.catch(error => {
						console.error('Error:', error)
						location.href = "manageUser"
					});


			})
		}
})