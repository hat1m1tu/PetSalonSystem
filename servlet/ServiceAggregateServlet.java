package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AggregateDao;
import entity.Aggregate;

/**
 * Servlet implementation class ServiceAggregateServlet
 */
@WebServlet("/after_login/ServiceAggregateServlet")
public class ServiceAggregateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceAggregateServlet() {
        super();
        // TODO Auto-generated constructor stub

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Aggregate> list = new ArrayList<Aggregate>();
		AggregateDao dao = new AggregateDao();
		list=dao.getAggregate(2);
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("./ServiceAggregate.jsp");
		rd.forward(request, response);
	}

}
