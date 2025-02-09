document.addEventListener("DOMContentLoaded", function () {
  const dateFilterOption = document.getElementById("dateFilterOption");

  // Apply color for each status dropdown
  document.querySelectorAll(".status-dropdown").forEach(select => {
    updateDropdownColor(select);

    // Add event listener to update color and button visibility on change
    select.addEventListener("change", function () {
      updateDropdownColor(this);
      toggleUpdateButton(this);
      setStatusId(this);
    });
  });

  if (filterForm) {
    filterForm.addEventListener("submit", function (event) {
      event.preventDefault(); // Prevent default form submission

      // Get filter values
      const nameEmailFilter = document.getElementById("nameEmailFilter").value.trim();
      const serviceFilter = document.getElementById("serviceFilter").value;
      const statusFilter = document.getElementById("statusFilter").value;
      const dateFilterOption = document.getElementById("dateFilterOption").value;
      const specificDate = document.getElementById("specificDate").value;
      const startDate = document.getElementById("startDate").value;
      const endDate = document.getElementById("endDate").value;

      // Build query parameters
      const params = new URLSearchParams();
      if (nameEmailFilter) params.append("nameEmailFilter", nameEmailFilter);
      if (serviceFilter) params.append("serviceFilter", serviceFilter);
      if (statusFilter) params.append("statusFilter", statusFilter);
      if (dateFilterOption === "date" && specificDate) {
        params.append("specificDate", specificDate);
      } else if (dateFilterOption === "period" && startDate && endDate) {
        params.append("startDate", startDate);
        params.append("endDate", endDate);
      }

      console.log("Filtering with parameters:", params.toString()); // Debugging

      // Fetch filtered results from the servlet
      fetch(`/view/manageBooking?${params.toString()}`)
        .then(response => {
          if (!response.ok) throw new Error(`Server error: ${response.status}`);
          return response.text();
        })
        .then(data => {
          document.getElementById("serviceHistoryTable").innerHTML = data;
        })
        .catch(error => console.error("Error fetching filtered data:", error));
    });
  }

  // Toggle Date Filters
  document.getElementById("dateFilterOption").addEventListener("change", function () {
    document.getElementById("dateFilter").style.display = this.value === "date" ? "block" : "none";
    document.getElementById("periodFilter").style.display = this.value === "period" ? "block" : "none";
  });

  // Update the background color and text color of the dropdown based on status
  function updateDropdownColor(selectElement) {
    const statusColors = {
      1: { bg: "rgb(255, 243, 204)", text: "rgb(196, 140, 0)" },   // pending
      2: { bg: "rgb(209, 209, 255)", text: "blue" },               // confirmed
      3: { bg: "rgb(255, 237, 202)", text: "rgb(237, 154, 0)" },    // paid
      4: { bg: "rgb(186, 239, 255)", text: "rgb(0, 123, 167)" },    // in progress
      5: { bg: "rgb(220, 255, 220)", text: "green" },               // completed
      6: { bg: "rgb(252, 210, 210)", text: "red" }                 // cancelled
    };

    const selectedValue = selectElement.value;
    const colors = statusColors[selectedValue];

    if (colors) {
      selectElement.style.backgroundColor = colors.bg;
      selectElement.style.color = colors.text;
    }
  }

  // Toggle the update button visibility based on the selected option
  function toggleUpdateButton(selectElement) {
    let defaultValue = selectElement.getAttribute('data-default') || selectElement.value;
    let selectedValue = selectElement.value;
    let updateButton = selectElement.nextElementSibling;  // Get the update button (next sibling)

    if (selectedValue !== defaultValue) {
      updateButton.style.display = "inline-block";  // Show the button if status has changed
    } else {
      updateButton.style.display = "none";  // Hide the button if status hasn't changed
    }
  }

  // Set the status ID based on the selected value (status ID)
  function setStatusId(selectElement) {
    var statusIdInput = selectElement.closest('form').querySelector('[name="statusId"]');
    var selectedOption = selectElement.options[selectElement.selectedIndex];
    var statusId = selectedOption.value; // Get the selected status ID
    statusIdInput.value = statusId;
  }

});
// Toggle the date filters based on the selected option
function toggleDateFilters() {
  const selectedOption = dateFilterOption.value;

  document.getElementById("dateFilter").style.display = "none";
  document.getElementById("periodFilter").style.display = "none";

  if (selectedOption === "date") {
    document.getElementById("dateFilter").style.display = "block";
  } else if (selectedOption === "period") {
    document.getElementById("periodFilter").style.display = "block";
  }
}
