package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Account;

public class AccountDao extends AbstractDao {

	public Account find(String id, String password) {
		Account account = null;
		String sql="select * from account where id = ? and password = ?";
		try(Connection conn =getConnection();
				PreparedStatement pst =conn.prepareStatement(sql)){
			pst.setString(1, id);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				account = new Account();
				account.setId(rs.getString("id"));
				account.setName(rs.getString("name"));
				account.setPassword(rs.getString("password"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}
}
