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
});