package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Service;

public class ServiceDao extends AbstractDao {


	public List<Service> findAll() {
		List<Service> list = new ArrayList<Service>();
		String sql = "select * from service order by id";
		try(
				Connection con = getConnection();

				PreparedStatement ps = con.prepareStatement(sql);
				){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {

				Service service = new Service();
				service.setId(rs.getInt("id"));
				service.setName(rs.getString("name"));
				service.setPrice(rs.getInt("price"));
				service.setStatus(rs.getInt("status"));
				list.add(service);
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return list;
	}

	public int add(Service service) {
		String sql = "insert into service(name, price, status) values(?, ?, ?)";
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				){
			String name = service.getName();
			int price = service.getPrice();
			int status = service.getStatus();
			if (name == null || name.equals("")||price == -1||status == -1) {
				return -1;
			}
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setInt(3, status);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return -1;
	}

	public Service find(int id) {
		String sql = "select * from service where id = ?";
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Service service = new Service();
				service.setId(rs.getInt("id"));
				service.setName(rs.getString("name"));
				service.setPrice(rs.getInt("price"));
				service.setStatus(rs.getInt("status"));

				return service;
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}

	public int update(int id, Service service) {
		String sql = "update service set name = ?, price = ?, status = ? where id = ?";
		try(
				Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				){
			ps.setString(1, service.getName());
			ps.setInt(2, service.getPrice());
			ps.setInt(3, service.getStatus());
			ps.setInt(4, service.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return -1;
	}




}
