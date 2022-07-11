import java.sql.SQLException;
import java.util.List;

public interface Dao {
    public int insert(Book book) throws SQLException;

    public List<Book> getAll() throws SQLException;
}