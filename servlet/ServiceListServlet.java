package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ServiceDao;
import entity.Service;

/**
 * Servlet implementation class ServiceListServlet
 */
@WebServlet("/after_login/ServiceListServlet")
public class ServiceListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 ServiceDao dao = new ServiceDao();
		 List<Service> list = dao.findAll();
		 request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/after_login/servicelist.jsp");
		rd.forward(request, response);
	}


}
