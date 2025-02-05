package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import model.Category;
import model.Service;
import model.Address;
import model.Appointment;
import model.AddressDAO;
import model.ServiceDAO;
import model.CategoryDAO;
import model.AppointmentDAO;
import model.User;

@WebServlet("/view/appointment")
public class AppointmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServiceDAO serviceDAO;
    private CategoryDAO categoryDAO;
    private AddressDAO addressDAO;
    private AppointmentDAO appointmentDAO;

    public AppointmentServlet() {
        super();
        serviceDAO = new ServiceDAO();
        categoryDAO = new CategoryDAO();
        addressDAO = new AddressDAO();
        appointmentDAO = new AppointmentDAO();
    }

    // Handle GET request to display the booking form
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String serIdParam = request.getParameter("serId");
        String catIdParam = request.getParameter("catId");

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } else {
            int userId = user.getId();
            List<Address> addressList = null;
            try {
                addressList = addressDAO.getAddressListByUserId(userId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("addressList", addressList);
        }

        if (serIdParam == null || catIdParam == null || serIdParam.isEmpty() || catIdParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Service ID and Category Id are required.");
            return;
        }

        try {
            int serviceId = Integer.parseInt(serIdParam);
            int categoryId = Integer.parseInt(catIdParam);

            // Retrieve service details
            Service service = serviceDAO.getServiceByServiceId(serviceId);
            Category category = categoryDAO.getCategoryById(categoryId);

            if (service == null || category == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Service or Category not found.");
                return;
            }

            request.setAttribute("service", service);
            request.setAttribute("category", category);

            // Forward to appointment.jsp
            request.getRequestDispatcher("/view/appointment.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Service ID format.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving data from the database.");
        }
    }

    // Handle POST request for appointment submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Get form data
        User user = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        int addressId = Integer.parseInt(request.getParameter("address"));
        String specialRequest = request.getParameter("special-request");
        String dateStr = request.getParameter("date");

        Date bookDate = null;

        if (dateStr != null && !dateStr.isEmpty()) {
            bookDate = Date.valueOf(dateStr);
        }

        int statusId = 1; // Default status ID for a new appointment

        String feedback = request.getParameter("feedback");

        int serviceId = Integer.parseInt(request.getParameter("serId")); // Service ID from the form

        try {
            Appointment appointment = new Appointment();
            appointment.setUserId(user.getId());
            appointment.setStatusId(statusId);
            appointment.setBookingName(name);
            appointment.setBookingPhone(phone);
            appointment.setAddressId(addressId);
            appointment.setSpecialRequest(specialRequest);
            appointment.setBookDate(bookDate);
            appointment.setFeedback(feedback);
            appointment.setServiceId(serviceId);

            // Insert appointment into the database
            int appointmentId = appointmentDAO.insertAppointment(
                    appointment.getUserId(),
                    appointment.getStatusId(),
                    appointment.getBookingName(),
                    appointment.getBookingPhone(),
                    appointment.getAddressId(),
                    appointment.getBookDate(),
                    appointment.getSpecialRequest(),
                    appointment.getFeedback(),
                    appointment.getServiceId()
            );

            // Redirect to service history page after successful booking
            response.sendRedirect(request.getContextPath() + "/view/serviceHistory");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting appointment into the database.");
        }
    }
}
