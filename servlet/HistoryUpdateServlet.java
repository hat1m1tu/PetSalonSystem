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
 * Servlet implementation class HistoryUpdateServlet
 */
@WebServlet("/after_login/HistoryUpdateServlet")
public class HistoryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		HistoryDao dao = new HistoryDao();
		History history = dao.find(id);
		//listの画面でhistoryのテーブルが削除されたとき。
		if (history == null) {
			System.out.println("updateに失敗しました。");
			request.setAttribute("error", 0);
			RequestDispatcher rd = request.getRequestDispatcher("./historyupdate_form.jsp");
			rd.forward(request, response);
			return;
		} else {
			//デバッグ
			//SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
			//sdFormat.format(history.getServiceDate());
			//System.out.println(sdFormat.format(history.getServiceDate()));

			request.setAttribute("history", history);
			RequestDispatcher rd = request.getRequestDispatcher("./historyupdate_form.jsp");
			rd.forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pet_Id = Integer.parseInt((String) request.getParameter("pet_Id"));
		int service_Id = Integer.parseInt((String) request.getParameter("service_Id"));
		String service_Date = request.getParameter("service_Date");
		int id = Integer.parseInt((String) request.getParameter("id"));
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd/HH:mm");
		Date date = null;
		try {
			date = sdFormat.parse(service_Date);
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("pet_Id", pet_Id);
			request.setAttribute("service_Id", service_Id);
			request.setAttribute("service_Date", service_Date);
			request.setAttribute("id", id);
			System.out.println(pet_Id);
			System.out.println(service_Id);
			System.out.println(service_Date);
			System.out.println(id);

			request.setAttribute("error", -1);
			RequestDispatcher rd = request.getRequestDispatcher("./historyupdate_form.jsp");
			rd.forward(request, response);
		}
		History history = new History();
		history.setPetId(pet_Id);
		history.setServiceId(service_Id);
		history.setServiceDate(date);
		history.setId(id);
		HistoryDao dao = new HistoryDao();
		int result = dao.update(history);
		if (result == 1) {
			response.sendRedirect("http://localhost:8080/Dsystem/after_login/HistoryListServlet?");
		} else {
			System.out.println("updateに失敗しました。");
			request.setAttribute("error", result);
			RequestDispatcher rd = request.getRequestDispatcher("./historyupdate_form.jsp");
			rd.forward(request, response);
		}
	}

}
