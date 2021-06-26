package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Owner;

public class OwnerDao extends AbstractDao {

	public List<Owner> findAll() {
		List<Owner> list = new ArrayList<Owner>();
		String sql = "select * from owner ORDER BY id";
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Owner owner = new Owner();
				owner.setId(rs.getInt("id"));
				owner.setName(rs.getString("name"));
				owner.setAddress(rs.getString("address"));
				owner.setTel(rs.getString("tel"));
				owner.setEmail(rs.getString("email"));
				list.add(owner);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Owner> search(String name,String address,String tel,String email) {
		List<Owner> list = new ArrayList<Owner>();
		String sql = "select * from owner where  name like ? and address like ? and tel like ? and email like ? ORDER BY id";
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, "%" + name + "%");
			ps.setString(2, "%" + address + "%");
			ps.setString(3, "%" + tel + "%");
			ps.setString(4, "%" + email + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Owner owners = new Owner();
				owners.setId(rs.getInt("id"));
				owners.setName(rs.getString("name"));
				owners.setAddress(rs.getString("address"));
				owners.setTel(rs.getString("tel"));
				owners.setEmail(rs.getString("email"));
				list.add(owners);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int add(Owner owner) {
		String sql = "insert into owner(id,name,address,tel,email) values (?,?,?,?,?)";
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, this.getNextOwnerId());
			ps.setString(2, owner.getName());
			ps.setString(3, owner.getAddress());
			ps.setString(4, owner.getTel());
			ps.setString(5, owner.getEmail());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	private int getNextOwnerId() {
		int maxOwnerId = 0;
		String sql = "SELECT MAX(id) FROM owner";
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				maxOwnerId = rs.getInt("max");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxOwnerId + 1;
	}

	public Owner find(int id) {
		Owner owner = null;
		String sql = "select * from owner where id = ?";
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				owner = new Owner();
				owner.setId(rs.getInt("id"));
				owner.setName(rs.getString("name"));
				owner.setAddress(rs.getString("address"));
				owner.setTel(rs.getString("tel"));
				owner.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return owner;
	}

	public int update(Owner owner) {
		String sql = "update owner set name = ?,address = ?,tel = ?,email = ? where id = ?";
		try (
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, owner.getName());
			ps.setString(2, owner.getAddress());
			ps.setString(3, owner.getTel());
			ps.setString(4, owner.getEmail());
			ps.setInt(5, owner.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
