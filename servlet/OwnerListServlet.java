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
 * Servlet implementation class OwnerListServlet
 */
@WebServlet("/after_login/OwnerListServlet")
public class OwnerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 検索結果の取得
		OwnerDao dao = new OwnerDao();
		List<Owner> owner = dao.findAll();
		// 値をrequestオブジェクトにセット
		request.setAttribute("owner", owner);
		// 結果表示画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("/after_login/ownerlist.jsp");
		rd.forward(request, response);
	}

}