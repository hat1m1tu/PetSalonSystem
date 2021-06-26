package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HistoryDao;
import entity.History;

/**
 * Servlet implementation class HistoryDeleteServlet
 */
@WebServlet("/after_login/HistoryDeleteServlet")
public class HistoryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		HistoryDao dao = new HistoryDao();
		History history = dao.find(id);
		if (history == null) {
			System.out.println("updateに失敗しました。");
			request.setAttribute("error", 0);
			RequestDispatcher rd = request.getRequestDispatcher("./historydelete_form.jsp");
			rd.forward(request, response);
			return;
		} else {
			request.setAttribute("history", history);
			RequestDispatcher rd = request.getRequestDispatcher("./historydelete_form.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt((String) request.getParameter("id"));
		HistoryDao dao = new HistoryDao();
		int result = dao.delete(id);
		if (result == 1) {
			response.sendRedirect("http://localhost:8080/Dsystem/after_login/HistoryListServlet?");
		} else {
			System.out.println("deleteに失敗しました。");
			request.setAttribute("error", result);
			RequestDispatcher rd = request.getRequestDispatcher("./historydelete_form.jsp");
			rd.forward(request, response);
		}
	}

}
