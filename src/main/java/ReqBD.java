import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReqBD {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mybd" + "?useSSL=false";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "root";


    public void showRezult(String req) {
        String requestFull = "SELECT a.id, d.name_district district,s.name_street street,adr.build ,a.space, a.rooms, a.price " +
                "FROM apartaments a, district d, adress adr, streets s " +
                "WHERE a.district_id=d.district_id AND a.adress_id=adr.adress_id " +
                "AND adr.streets_id=s.streets_id AND a.adress_id=adr.adress_id";
        String reqSQL = requestFull + req;
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(reqSQL);
            System.out.printf("%-11s%-30s%-45s%-11s%-11s%-11s%n", "id", "district", "adress", "space", "rooms", "price");
            if (!resultSet.next()) System.out.printf("%80s%n", "No data with such conditions");
            resultSet.beforeFirst();
            while (resultSet.next()) {
                System.out.println(new Apartament(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3) + " " + resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7)).toString());
            }
            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getStringParametr(String nameTable, String nameParametr) {
        String reqSQL = "SELECT * FROM " + nameTable + " ORDER BY 1";
        try (Connection con = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(reqSQL);
            System.out.printf("%-11s%-30s%n", "id", nameParametr);
            if (!res.next()) System.out.printf("%30s%n", "No data with such conditions");
            res.beforeFirst();
            while (res.next()) {
                System.out.printf("%-11s%-30s%n", res.getString(1), res.getString(2));
            }
            res.close();
            st.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
