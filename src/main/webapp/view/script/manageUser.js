document.addEventListener("DOMContentLoaded", async () => {
	const delBtns = document.getElementsByClassName("delete-button");
	for (let i = 0; i < delBtns.length; i++) {
		
		delBtns[i].addEventListener("click", function() {
			const addressId = this.getAttribute("address-id");
			console.log("deleteting " + addressId)
			fetch(`/JAD_CA1/api/address/${addressId}`, {
				method: "DELETE"
			}).then(() => location.href = "manageUser")
				.catch(error => {
					console.error('Error:', error)
					location.href = "manageUser"
				});


		});
	}
})