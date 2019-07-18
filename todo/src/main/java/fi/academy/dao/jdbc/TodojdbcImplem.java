
package fi.academy.dao.jdbc;

        import fi.academy.Todo;
import fi.academy.dao.TodoDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Qualifier("jdbc")
public class TodojdbcImplem implements TodoDao {
    private Connection con;

    public TodojdbcImplem() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "TLW1");
    }

    @Override
    public List<Todo> haeKaikki() {
        String sql = "SELECT * FROM todo";
        List<Todo> haetut = new ArrayList<>();
        try (PreparedStatement pr = con.prepareStatement(sql)) {
            for (ResultSet rs = pr.executeQuery(); rs.next(); ) {
                Todo t = new Todo();
                t.setId(rs.getInt("id"));
                t.setTask(rs.getString("task"));
                t.setDue(rs.getDate("due"));
                haetut.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
        return haetut;
    }

    @Override
    public int lisaa(Todo todo) {
        int avain = -1;
        String sql = "INSERT INTO todo(task, due) VALUES (?,?)";
        try (PreparedStatement pr = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, todo.getTask());
            pr.setDate(2, (Date) todo.getDue());
            pr.execute();
            ResultSet avaimet = pr.getGeneratedKeys();
            while (avaimet.next()) {
                avain = avaimet.getInt(1);
                todo.setId(avain);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avain;
    }

    @Override
    public Todo poista(int id) {
        Todo poistettu = new Todo();
        String sel = "SELECT * FROM todo WHERE id = ?";
        try (PreparedStatement prs = con.prepareStatement(sel)) {
            prs.setInt(1, id);
            prs.execute();
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                poistettu.setId(rs.getInt("id"));
                poistettu.setTask(rs.getString("task"));
                poistettu.setDue(rs.getDate("due"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "DELETE FROM todo WHERE id = ?";
        try (PreparedStatement pr = con.prepareStatement(sql)) {
            pr.setInt(1, id);
            pr.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return poistettu;
    }
}
