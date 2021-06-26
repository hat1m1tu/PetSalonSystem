package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 全てのDAOクラスのスーパークラスです。
 * @author mtada
 *
 */
public abstract class AbstractDao {

	/** ドライバクラス名 */
	private static final String DRIVER = "org.postgresql.Driver";
	/** DBのURL */
	private static final String URL = "jdbc:postgresql:postgres";
	/** DBのユーザー名 */
	private static final String USER = "postgres";
	/** DBのパスワード */
	private static final String PASSWORD = "postgres";

	/**
	 * DBに接続します。
	 * @return Connectionオブジェクト
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            return con;
        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            return null;
        }
    }
}
