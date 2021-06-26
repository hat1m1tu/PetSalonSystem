package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import entity.Account;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //doGetはlogin.jspを表示するだけ
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		//IDが入力されていない場合、ログイン失敗
		if (id.length() == 0) {
			request.setAttribute("message", "IDまたはパスワードが違います");
			// ログイン画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		Account acc=null;

		//IDに整数以外の値を入力した場合、ログイン失敗
		try {
			// IDを整数に変換
			// DBにアクセスし、ID・パスワードが正しいか確かめる
			AccountDao dao =new AccountDao();
			acc =dao.find(id, password);
		} catch (Exception e) {
			request.setAttribute("message", "IDには整数を入力してください");
			// ログイン画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}

		// ログイン成功
		if (acc != null) {

			// 会員情報をセッションに登録
			HttpSession session = request.getSession(true);
			session.setAttribute("account", acc);

			// ホーム画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/after_login/MainMenuServlet");
			rd.forward(request, response);

		} else {
			// ID・パスワードが異なる場合、ログイン失敗
			request.setAttribute("message", "IDまたはパスワードが違います");

			// ログイン画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}

}
