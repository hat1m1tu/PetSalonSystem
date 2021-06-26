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
 * Servlet implementation class ServiceUpdateServlet
 */
@WebServlet("/after_login/ServiceUpdateServlet")
public class ServiceUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt((String)request.getParameter("id"));
		ServiceDao dao = new ServiceDao();
		Service service = dao.find(id);
		request.setAttribute("service", service);
		if (service == null) {
			request.setAttribute("iS", "0");
		}

		RequestDispatcher rd = request.getRequestDispatcher("./serviceupdate.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		ServiceDao dao = new ServiceDao();
		Service service = new Service();
		Service pservice = new Service();
		int id, price, status, pprice, pstatus, isSuccess = -1;
		String iS = String.valueOf(isSuccess);
		String name, pname;
		id = Integer.parseInt((String) request.getParameter("id"));
		pname = request.getParameter("pname");
		pprice = Integer.parseInt((String) request.getParameter("pprice"));
		pstatus = Integer.parseInt((String) request.getParameter("pstatus"));
//		System.out.println("id="+id);
//		System.out.println("pname="+pname);
//		System.out.println("pprice="+pprice);
//		System.out.println("pstatus="+pstatus);
		try {
		name = request.getParameter("name");
		price = Integer.parseInt((String) request.getParameter("price"));
		status = Integer.parseInt((String) request.getParameter("status"));

		}catch (NullPointerException e) {
			// TODO: handle exception
			service.setId(id);
			service.setName(pname);
			service.setPrice(pprice);
			service.setStatus(pstatus);
			request.setAttribute("service", service);
			//request.setAttribute("iS", iS);
			RequestDispatcher rd = request.getRequestDispatcher("./serviceupdate.jsp");
			rd.forward(request, response);
			return;
		}catch (NumberFormatException e) {
			// TODO: handle exception
			service.setId(id);
			service.setName(pname);
			service.setPrice(pprice);
			service.setStatus(pstatus);
			request.setAttribute("service", service);
			request.setAttribute("iS", iS);
			RequestDispatcher rd = request.getRequestDispatcher("./serviceupdate.jsp");
			rd.forward(request, response);
			return;
		}
		if(name.equals("")) {

			service.setId(id);
			service.setName(pname);
			service.setPrice(pprice);
			service.setStatus(pstatus);
			request.setAttribute("service", service);
			request.setAttribute("iS", iS);
			RequestDispatcher rd = request.getRequestDispatcher("./serviceupdate.jsp");
			rd.forward(request, response);
			return;
		}
		service.setId(id);
		service.setName(name);
		service.setPrice(price);
		service.setStatus(status);

		//System.out.println("id=" + id + "\n" + "name=" + name  + "\n" + "price=" + price + "\n" + "status=" + status + "\n");



		isSuccess = dao.update(id, service);
		iS = String.valueOf(isSuccess);
		System.out.println(iS);

		request.setAttribute("iS", iS);
		if(iS.equals("1")) {
			List<Service> list = dao.findAll();
			request.setAttribute("list", list);

			RequestDispatcher rd = request.getRequestDispatcher("./servicelist.jsp");
			rd.forward(request, response);
			return;
		}else {

			service.setId(id);
			service.setName(pname);
			service.setPrice(pprice);
			service.setStatus(pstatus);
			request.setAttribute("service", service);
			RequestDispatcher rd = request.getRequestDispatcher("./serviceupdate.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
