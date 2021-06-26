package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.History;

public class HistoryDao extends AbstractDao {

	public History find(int id) {
		History history = new History();
		String sql = "SELECT * FROM history WHERE id = ? ORDER BY id";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				history.setId(rs.getInt("id"));
				history.setPetId(rs.getInt("pet_id"));
				history.setServiceId(rs.getInt("service_id"));
				history.setServiceDate(rs.getTimestamp("service_date"));
				return history;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<History> findAll() {
		List<History> list = new ArrayList<History>();
		String sql = "SELECT * FROM history ORDER BY id";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				History history = new History();
				history.setId(rs.getInt("id"));
				history.setPetId(rs.getInt("pet_id"));
				history.setServiceId(rs.getInt("service_id"));
				history.setServiceDate(rs.getTimestamp("service_date"));
				list.add(history);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public int add(History history) {
		int result = 0;
		String sql = "INSERT INTO history (pet_id,service_id, service_date) VALUES(?, ?, ?)";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, history.getPetId());
			st.setInt(2, history.getServiceId());
			Date date = history.getServiceDate();
			System.out.println(date);
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
			st.setTimestamp(3, sqlDate);

			return st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int update(History history) {
		int result = 0;
		String sql = "UPDATE history SET pet_id = ?, service_id=?, service_date=? WHERE id=?";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, history.getPetId());
			st.setInt(2, history.getServiceId());
			Date date = history.getServiceDate();
			System.out.println(date);
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
			st.setTimestamp(3, sqlDate);
			st.setInt(4, history.getId());

			return st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int delete(int id) {
		int result = 0;
		String sql = "DELETE FROM history WHERE id=?";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			st.setInt(1, id);

			return st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
