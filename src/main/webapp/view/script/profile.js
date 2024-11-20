document.addEventListener('DOMContentLoaded', function() {
	const addAddressBtn = document.getElementById('addAddressBtn');
	const newAddressForm = document.getElementById('newAddressForm');
	addAddressBtn.addEventListener('click', function() {
		newAddressForm.attributes.removeNamedItem('hidden');
		addAddressBtn.attributes.setNamedItem(document.createAttribute('hidden'));
	});

	const cancelBtn = document.getElementById('cancelBtn');
	cancelBtn.addEventListener('click', function() {
		newAddressForm.attributes.setNamedItem(document.createAttribute('hidden'));
		addAddressBtn.attributes.removeNamedItem('hidden');
	});

	const editInfoBtn = document.getElementById('editInfoBtn');
	editInfoBtn.addEventListener('click', function() {
		const name = document.getElementById('name');
		const email = document.getElementById('email');
		const mobile = document.getElementById('mobile');
		const passwordLabel = document.getElementById('passwordLabel');
		const password = document.getElementById('password');
		const saveInfoBtn = document.getElementById('saveInfoBtn');
		const editCancelBtn = document.getElementById('editCancelBtn');

		name.removeAttribute('readonly');
		email.removeAttribute('readonly');
		mobile.removeAttribute('readonly');
		passwordLabel.removeAttribute('hidden');
		password.removeAttribute('hidden');
		saveInfoBtn.removeAttribute('hidden');
		editInfoBtn.setAttribute('hidden', '');
		editCancelBtn.removeAttribute('hidden');
	});

	const deleteBtn = document.getElementsByClassName('delete-button');
	for (let i = 0; i < deleteBtn.length; i++) {
		deleteBtn[i].addEventListener('click', function() {
			const addressId = this.getAttribute('address-id');
			
			fetch("/JAD_CA1/api/address/" + addressId, {
				method: "DELETE"
			}).then(response => response.json())
				.then(() => location.href = "profile.jsp")
				.catch(error => {
					console.error('Error:', error)
					location.href = "profile.jsp"
				});
		});

	}
});