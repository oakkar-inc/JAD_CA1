document.addEventListener("DOMContentLoaded", () => {
    // Function to get the current date adjusted to Singapore timezone (UTC+8)
    const getSingaporeDate = (offsetDays = 0) => {
        const now = new Date();
        const utc = now.getTime() + now.getTimezoneOffset() * 60000; // Convert to UTC
        const singaporeTime = new Date(utc + 8 * 3600000); // Add 8 hours for Singapore timezone
        singaporeTime.setDate(singaporeTime.getDate() + offsetDays); // Add offset days
        return singaporeTime;
    };

    // Format date to YYYY-MM-DD
    const formatDate = (date) => {
        return date.toISOString().split('T')[0];
    };

    // Get tomorrow's date and one year from tomorrow in Singapore timezone
    const tomorrow = getSingaporeDate(1);
    const oneYearFromTomorrow = new Date(tomorrow);
    oneYearFromTomorrow.setFullYear(tomorrow.getFullYear() + 1);

    // Set min and max dates
    const dateInput = document.getElementById('date');
    if (dateInput) {
        dateInput.min = formatDate(tomorrow);
        dateInput.max = formatDate(oneYearFromTomorrow);
    }
});
