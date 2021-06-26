package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Pet;

public class PetDao extends AbstractDao {
	public List<Pet> findAll() {
		// 戻り値となるリスト
		List<Pet> list = new ArrayList<Pet>();
		String sql = "SELECT * FROM pet ORDER BY id";
		try (
				Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			//SQL文の実行
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Pet pet = new Pet();
				pet.setId(rs.getInt("id"));
				pet.setName(rs.getString("name"));
				pet.setBirthday((java.util.Date) rs.getDate("birthday"));
				pet.setWeight(rs.getInt("weight"));
				pet.setCategory(rs.getString("category"));
				pet.setOwnerId(rs.getInt("owner_id"));
				list.add(pet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}

	/**
	 * ペット検索メソッド
	 */
	public List<Pet> search(String name, String birthday, String weight,
			String category, String owner_id) {
		List<Pet> list = new ArrayList<Pet>();
		String sql = "SELECT * FROM pet WHERE name LIKE ? AND CAST(birthday AS TEXT) LIKE ? AND CAST(weight AS TEXT) LIKE ? AND category LIKE ? AND CAST(owner_id AS TEXT) LIKE ?";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			// バインド変数に値をセット
			st.setString(1, "%" + name + "%");
			st.setString(2, "%" + birthday + "%");
			st.setString(3, "%" + weight + "%");
			st.setString(4, "%" + category + "%");
			st.setString(5, "%" + owner_id + "%");
			// SQL文実行
			ResultSet rs = st.executeQuery();
			// ResultSetから結果の取得
			while (rs.next()) {
				Pet pet = new Pet();
				pet.setId(rs.getInt("id"));
				pet.setName(rs.getString("name"));
				pet.setBirthday((java.util.Date) rs.getDate("birthday"));
				pet.setWeight(rs.getInt("weight"));
				pet.setCategory(rs.getString("category"));
				pet.setOwnerId(rs.getInt("owner_id"));
				list.add(pet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int add(Pet pet) {
		// 戻り値
		int result = -1;
		String sql = "INSERT INTO pet (name,birthday,weight,category,owner_id) VALUES (?,?,?,?,?)";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			Date date = pet.getBirthday();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			// バインド変数に値をセット
			st.setString(1, pet.getName());
			st.setDate(2, sqlDate);
			st.setInt(3, pet.getWeight());
			st.setString(4, pet.getCategory());
			st.setInt(5, pet.getOwnerId());
			// SQL文の実行
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * ペット情報更新
	 * @param member
	 * @return
	 */
	public int update(Pet pet) {
		int result = -1;
		String sql = "UPDATE pet SET name = ?,birthday = ?, weight = ?, category = ?, owner_id = ? WHERE id = ?";
		try (Connection con = getConnection();
				PreparedStatement st = con.prepareStatement(sql)) {
			Date date = pet.getBirthday();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			// バインド変数に値をセット
			st.setString(1, pet.getName());
			st.setDate(2, sqlDate);
			st.setInt(3, pet.getWeight());
			st.setString(4, pet.getCategory());
			st.setInt(5, pet.getOwnerId());
			st.setInt(6, pet.getId());
			// SQL文の実行
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Pet find(int id) {
		Pet pet = null;
		String sql = "select * from pet where id = ?";
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pet = new Pet();
				pet.setId(rs.getInt("id"));
				pet.setName(rs.getString("name"));
				pet.setBirthday((java.util.Date) rs.getDate("birthday"));
				pet.setWeight(rs.getInt("weight"));
				pet.setCategory(rs.getString("category"));
				pet.setOwnerId(rs.getInt("owner_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pet;
	}

	/**
	 * 実験用メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		PetDao dao = new PetDao();
		//Pet p = dao.search(10003, "cccc");
		//System.out.println(m.getName());
	}
}