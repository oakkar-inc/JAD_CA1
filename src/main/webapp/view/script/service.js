document.addEventListener("DOMContentLoaded", () => {
    const cards = document.querySelectorAll(".card");
    const bookNowButton = document.getElementById("bookNowButton");
    const errorMessage = document.getElementById("errorMessage");
    let selectedCard = null;

    // Hide error message initially
    if (errorMessage) {
        errorMessage.style.display = 'none';
    }

    cards.forEach(card => {
        card.addEventListener("click", () => {
            const cardName = card.querySelector(".h3").textContent;
            console.log(`Selected card: ${cardName}`);

            const serviceId = card.id;

            cards.forEach(c => c.classList.remove("selected"));
            card.classList.add("selected");
            selectedCard = card.id;

            // Hide error message when a card is selected
            if (errorMessage) {
                errorMessage.style.display = 'none';
            }

            const categoryId = bookNowButton.getAttribute("data-category-id");
            const loginUserId = bookNowButton.getAttribute("data-user-id");
            console.log("loginUser: " + loginUserId);

            if (loginUserId == "NoLogin") {
                bookNowButton.href = `login.jsp`;
                return;
            } else {
                bookNowButton.href = `appointment?catId=${categoryId}&serId=${serviceId}`;
            }
        });
    });

    // Add click event listener to the Book Now button
    bookNowButton.addEventListener("click", (e) => {
        if (selectedCard === null) {
            e.preventDefault(); // Prevent navigation
            if (errorMessage) {
                errorMessage.style.display = 'block';
                errorMessage.scrollIntoView({ behavior: 'smooth' }); 
            }
        }
    });
});