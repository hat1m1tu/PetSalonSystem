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

@WebServlet("/after_login/PetListServlet")
public class PetListServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		// 検索結果の取得
		PetDao dao = new PetDao();
		List<Pet> list = dao.findAll();

		// 値をrequestオブジェクトにセット
		request.setAttribute("list", list);

		// 結果表示画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("./pet_list.jsp");
		rd.forward(request, response);
	}




	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		// 検索結果の取得
		PetDao dao = new PetDao();
		List<Pet> list = dao.findAll();

		// 値をrequestオブジェクトにセット
		request.setAttribute("list", list);

		// 結果表示画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("./pet_list.jsp");
		rd.forward(request, response);
	}
}

