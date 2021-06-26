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

import dao.PetDao;
import entity.Pet;
/**
 * Servlet implementation class Login
 */
@WebServlet("/after_login/PetUpdateServlet")
public class PetUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    //doGetはlogin.jspを表示するだけ
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String id = request.getParameter("id");
        System.out.println("id " + id);
        //idから顧客情報を取得
        PetDao dao = new PetDao();
        Pet pet = dao.find(Integer.parseInt(id));
        System.out.println("name " + pet.getName());
        // 値をrequestオブジェクトにセット
        request.setAttribute("pet", pet);
        RequestDispatcher rd = request.getRequestDispatcher("/after_login/pet_update_form.jsp");
        rd.forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Pet pet = new Pet();
        String strid = request.getParameter("id");
        String birthday = request.getParameter("birthday");
        String strweight = request.getParameter("weight");
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String strowner_id = request.getParameter("owner_id");
        int weight = 0;
        int owner_id = 0;
        int id = 0;
        try {
            id = Integer.parseInt(strid);
            weight = Integer.parseInt(strweight);
            owner_id = Integer.parseInt(strowner_id);
        } catch (NumberFormatException e) {
            //編集
            request.setAttribute("error", "ペット情報の更新に失敗しました。\n名前は50文字\n誕生日はデート型\n体重は整数\n種類は100文字以内で入力してください");
            request.setAttribute("name", name);
            request.setAttribute("birthday", birthday);
            request.setAttribute("weight", strweight);
            request.setAttribute("category", category);
            request.setAttribute("owner_id", strowner_id);
            e.printStackTrace();
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdFormat.parse(birthday);
        } catch (ParseException e) {
            request.setAttribute("error", "ペット情報の更新に失敗しました。\n名前は50文字\n誕生日はデート型\n体重は整数\n種類は100文字以内で入力してください");
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("birthday", birthday);
            request.setAttribute("weight", strweight);
            request.setAttribute("category", category);
            request.setAttribute("owner_id", strowner_id);
            RequestDispatcher rd = request.getRequestDispatcher("pet_update_form.jsp");
            rd.forward(request, response);
        }
        if(name.length() > 50 || category.length() > 100) {
            request.setAttribute("error", "ペット情報の更新に失敗しました。\n名前は50文字\n誕生日はデート型\n体重は整数\n種類は100文字以内で入力してください");
            request.setAttribute("name", name);
            request.setAttribute("birthday", birthday);
            request.setAttribute("weight", strweight);
            request.setAttribute("category", category);
            request.setAttribute("owner_id", strowner_id);
            RequestDispatcher rd = request.getRequestDispatcher("pet_update_form.jsp");
            rd.forward(request, response);
        }
        pet.setId(id);
        pet.setName(name);
        pet.setBirthday(date);
        pet.setWeight(weight);
        pet.setCategory(category);
        pet.setOwnerId(owner_id);
        // 結果の取得
        PetDao dao = new PetDao();
        int result = dao.update(pet);
        System.out.println(result);
        // エラーの場合入力画面に遷移
        RequestDispatcher rd = request.getRequestDispatcher("/after_login/PetListServlet");
        rd.forward(request, response);
    }
}