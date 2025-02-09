document.addEventListener("DOMContentLoaded", async () => {
	const delBtns = document.getElementsByClassName("delete-button-address");
	for (let i = 0; i < delBtns.length; i++) {
		
		delBtns[i].addEventListener("click", function() {
			console.log("deleteAddress clicked");
			const addressId = this.getAttribute("data-address-id");
			fetch(`${contextPath}/api/address/${addressId}`, {
				method: "DELETE"
			}).then(() => location.href = `${contextPath}/ManageUser`)
				.catch(error => {
					console.error('Error:', error)
					location.href = `${contextPath}/manageUser`
				});


		});
	}
	
	const delUserBtns = document.getElementsByClassName("delete-button-user");
		for (let i = 0; i < delBtns.length; i++) {
			
			delUserBtns[i].addEventListener("click", function() {
				console.log("deleteUser clicked");
				const userId = this.getAttribute("data-user-id");
				fetch(`${contextPath}/api/user/${userId}`, {
					method: "DELETE"
				}).then(() => location.href = `${contextPath}/ManageUser`)
					.catch(error => {
						console.error('Error:', error)
						location.href = `${contextPath}/ManageUser`
					});


			})
		}
})