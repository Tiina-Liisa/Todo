
package fi.academy.dao;

        import fi.academy.Todo;

        import java.util.List;

public interface TodoDao {
    List<Todo>
    haeKaikki();
    int lisaa(Todo task);
    Todo poista(int id);
}
