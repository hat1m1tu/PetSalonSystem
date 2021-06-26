package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OwnerDao;
import entity.Owner;

/**
 * Servlet implementation class OwnerUpdateServlet
 */
@WebServlet("/after_login/OwnerUpdateServlet")
public class OwnerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//更新をしたいレコードのidを取得
		String id = request.getParameter("id");
		//idから顧客情報を取得
		OwnerDao dao = new OwnerDao();
		Owner owner = dao.find(Integer.parseInt(id));
		// 値をrequestオブジェクトにセット
		request.setAttribute("owner", owner);
		//画面遷移
		RequestDispatcher rd = request.getRequestDispatcher("/after_login/ownerupdate_from.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//更新情報を取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		//エンティティにセット
		Owner owner = new Owner();
		owner.setId(Integer.parseInt(id));
		owner.setName(name);
		owner.setAddress(address);
		owner.setTel(tel);
		owner.setEmail(email);

		//入力された値に空文字が含まれていたの場合
		if(name.length()==0||address.length()==0||tel.length()==0||email.length()==0) {
			//リクエストに格納
			request.setAttribute("owner", owner);
			request.setAttribute("message", "記入漏れがあります。");
			// 結果表示画面に遷移
			RequestDispatcher rd = request.getRequestDispatcher("/after_login/ownerupdate_from.jsp");
			rd.forward(request, response);
			return ;
		 }

		//実行
		OwnerDao dao = new OwnerDao();
		int update = dao.update(owner);

		 //新規追加に失敗した場合
		 if(update == -1) {
			 //リクエストに格納
			 request.setAttribute("owner", owner);
			 request.setAttribute("message", "顧客情報の追加に失敗しました。<br>名前は50文字\n住所は100文字<br>電話番号は13文字<br>メールアドレスは100文字以内で入力してください。" );
			 // 結果表示画面に遷移
			 RequestDispatcher rd = request.getRequestDispatcher("/after_login/ownerupdate_from.jsp");
			 rd.forward(request, response);
			 return;
		 }

		//新規追加に成功した場合
		// 結果表示画面に遷移
		 if(update == 1) {
			 HttpServletResponse hsrp = (HttpServletResponse) response;
			 hsrp.sendRedirect("./OwnerListServlet");
		 }
	}

}
