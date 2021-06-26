package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Aggregate;

public class AggregateDao extends AbstractDao {

	public List<Aggregate> getAggregate(int mode) {//modeが１ならペットの集計、それ以外ならサービスの合計
		List<Aggregate> list=new ArrayList<Aggregate>();
		String sql;
		if(mode==1) {
			sql="select category as name,sum(price) as sum from history join pet on history.pet_id = pet.id join service on history.service_id = service.id group by category";
		}else {
			sql="select name as name,sum(price) as sum from history join service on history.service_id = service.id group by name";
		}
		try(Connection conn =getConnection();
				PreparedStatement pst =conn.prepareStatement(sql)){
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Aggregate agg = new Aggregate();
				agg.setAggregate(rs.getInt("sum"));
				agg.setName(rs.getString("name"));
				list.add(agg);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
