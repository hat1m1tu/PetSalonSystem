package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OwnerDao;
import entity.Owner;

/**
 * Servlet implementation class OwnerSearchServlet
 */
@WebServlet("/after_login/OwnerSearchServlet")
public class OwnerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/after_login/ownersearch_from.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 検索結果の取得
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");

		//実行
		OwnerDao dao = new OwnerDao();
		List<Owner> owner = dao.search(name, address, tel, email);

		// 値をrequestオブジェクトにセット
		request.setAttribute("owner", owner);
		// 結果表示画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/after_login/ownersearch_result.jsp");
		rd.forward(request, response);

	}

}
