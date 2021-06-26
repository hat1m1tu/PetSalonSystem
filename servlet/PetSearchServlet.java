package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PetDao;
import entity.Pet;

/**
 * Servlet implementation class Login
 */
@WebServlet("/after_login/PetSearchServlet")
public class PetSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //doGetはlogin.jspを表示するだけ
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/petmenu.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");

			String name = request.getParameter("name");
			String birthday = request.getParameter("birthday");
			String weight = request.getParameter("weight");
			String category = request.getParameter("category");
			String owner_id = request.getParameter("owner_id");


			// 検索結果の取得
			PetDao dao = new PetDao();
			List<Pet> list = dao.search(name, birthday, weight, category, owner_id);

			// 値をrequestオブジェクトにセット
			request.setAttribute("list", list);

			// 結果表示画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("pet_search_result.jsp");
			rd.forward(request, response);
	}

}
