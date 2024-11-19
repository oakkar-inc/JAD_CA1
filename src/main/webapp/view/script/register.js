document.addEventListener('DOMContentLoaded', function() {
    const goBackBtn = document.getElementById('goBackBtn');
   	
	goBackBtn.addEventListener('click', () => {
		location.href = "home.jsp";
	})
});