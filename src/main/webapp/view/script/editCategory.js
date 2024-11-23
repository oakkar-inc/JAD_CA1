document.addEventListener("DOMContentLoaded", function() {
	const saveBtns = document.getElementsByClassName("saveInfoBtn");

	for (let i = 0; i < saveBtns.length; i++) {
		saveBtns[i].addEventListener("click", function() {
			const serviceId = this.getAttribute("data-service-id");
			const container = event.target.closest(".details-container");
			if (container) {
				const formData = {};
				container.querySelectorAll("input, textarea, select").forEach((input) => {
					formData[input.name] = input.value;
				});
				fetch("/JAD_CA1/view/service/" + serviceId, {
					method: "PUT",
					headers: {
						"Content-Type": "application/json"
					},
					body: JSON.stringify(formData)
				}).then(response => response.json())
					.then(() => location.href = "")
					.catch(error => {
						console.error('Error:', error)
						location.href = ""
					});

			}
		});
	}

	const catSave = document.getElementById("catSave");
	catSave.addEventListener("click", function() {
		const catId = this.getAttribute("data-cat-id");
		const name = document.getElementById("catName");
		const imgUrl = document.getElementById("catImgUrl");
		const description = document.getElementById("catDescription");

		const formData = {
			name: name.value,
			imgUrl: imgUrl.value,
			description: description.value
		}


		fetch("/JAD_CA1/view/category/" + catId, {
			method: "PUT",
			headers: {
				"Content-Type": "application/json"
			},
			body: JSON.stringify(formData)
		}).then(response => response.json())
			.then(() => location.href = "")
			.catch(error => {
				console.error('Error:', error)
				location.href = ""
			});

	})
	
	const delBtns = document.getElementsByClassName("delete-button-service");
		for (let i = 0; i < delBtns.length; i++) {
			
			delBtns[i].addEventListener("click", function() {
				const addressId = this.getAttribute("data-service-id");
				fetch(`/JAD_CA1/view/service/${addressId}`, {
					method: "DELETE"
				}).then(() => location.href = "")
					.catch(error => {
						console.error('Error:', error)
						location.href = ""
					});


			});
		}
		
	const delCatBtn = document.getElementById("delCatBtn");
	delCatBtn.addEventListener("click", function() {
		const catId = this.getAttribute("data-category-id");
		        fetch(`/JAD_CA1/view/category/${catId}`, {
		            method: "DELETE"
		        }).then(() => location.href = "/JAD_CA1/view/manageCategory")
		            .catch(error => {
		                console.error('Error:', error)
		                location.href = "/JAD_CA1/view/manageCategory"
		            });
	})
		
});
