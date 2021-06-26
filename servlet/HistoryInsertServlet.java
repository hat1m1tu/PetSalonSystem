package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HistoryDao;
import entity.History;

/**
 * Servlet implementation class HistoryInsertServlet
 */
@WebServlet("/after_login/HistoryInsertServlet")
public class HistoryInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./historyinsert_form.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int pet_Id = Integer.parseInt((String) request.getParameter("pet_Id"));
		int service_Id = Integer.parseInt((String) request.getParameter("service_Id"));
		String service_Date = request.getParameter("service_Date");
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
		Date date = null;
		try {
			date = sdFormat.parse(service_Date);
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("pet_Id", pet_Id);
			request.setAttribute("service_Id", service_Id);
			request.setAttribute("service_Date", service_Date);
			//デバッグ用
			System.out.println(service_Date);

			request.setAttribute("error", -1);
			RequestDispatcher rd = request.getRequestDispatcher("./historyinsert_form.jsp");
			rd.forward(request, response);
		}
		History history = new History();
		history.setPetId(pet_Id);
		history.setServiceId(service_Id);
		history.setServiceDate(date);
		HistoryDao dao = new HistoryDao();
		int result = dao.add(history);
		if (result == 1) {
			response.sendRedirect("http://localhost:8080/Dsystem/after_login/HistoryListServlet?");
		} else {
			System.out.println("insertに失敗しました。");
			request.setAttribute("error", result);
			RequestDispatcher rd = request.getRequestDispatcher("./historyinsert_form.jsp");
			rd.forward(request, response);
		}

	}

}
