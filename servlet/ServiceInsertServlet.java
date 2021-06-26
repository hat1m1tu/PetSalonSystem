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
 * Servlet implementation class ServiceInsertServlet
 */
@WebServlet("/after_login/ServiceInsertServlet")
public class ServiceInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/after_login/service_insert_form.jsp");
		rd.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServiceDao dao = new ServiceDao();
		Service service = new Service();
		service.setName(request.getParameter("name"));
		try {
			service.setPrice(Integer.parseInt(request.getParameter("price")));
		}catch (NumberFormatException e) {
			// TODO: handle exception
			service.setPrice(-1);
		}catch (NullPointerException e) {
			// TODO: handle exception
			service.setPrice(-1);
		}
		try {
			service.setStatus(Integer.parseInt(request.getParameter("status")));
		}catch(NumberFormatException e) {
			service.setStatus(-1);
		}catch (NullPointerException e) {
			// TODO: handle exception
			service.setPrice(-1);
		}
		/*System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("price"));
		System.out.println(request.getParameter("status"));*/
		int isSuccess = dao.add(service);

		request.setAttribute("isSuccess", isSuccess);
		List<Service> list = dao.findAll();
		request.setAttribute("list", list);
		if(isSuccess == 1) {
			 RequestDispatcher rd = request.getRequestDispatcher("/after_login/servicelist.jsp");
			rd.forward(request, response);
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/after_login/service_insert_form.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
