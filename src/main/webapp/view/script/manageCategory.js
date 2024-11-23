document.addEventListener("DOMContentLoaded", async () => {

	const cards = document.getElementsByClassName("cat-card");

	for (let card of cards) {
		card.addEventListener("click", () => {
			const categoryId = card.getAttribute("data-category-id");
			location.href += "/" + categoryId;
		})
	}
	
}
)
