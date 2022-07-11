import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Run implements Dao{
    private static int id;
    private static String name;
    private static String author;
    private static int price;
    Connection connection = Connections.getMssql();
    private static final String SQL_SELECT = "SELECT * FROM book";
    private static final String SQL_INSERT = "insert into book values(?,?,?,?)";
    private static Scanner input = new Scanner(System.in);
    public Run() throws SQLException {
    }
    @Override
    public int insert(Book book) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        preparedStatement = connection.prepareStatement(SQL_INSERT);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, author);
        preparedStatement.setInt(4, price);
        result = preparedStatement.executeUpdate();
        return result;
    }
    public void add(Book book){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id");
        id = sc.nextInt();
        System.out.println("Enter name");
        name = sc.next();
        System.out.println("Enter author");
        author = sc.next();
        System.out.println("Enter price");
        price = sc.nextInt();
        book = new Book(id,name,author,price);
    }
    @Override
    public List<Book> getAll() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> products = new ArrayList<>();
        preparedStatement = connection.prepareStatement(SQL_SELECT);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("ID: " + resultSet.getInt("ID")
                    + " - Name: " + resultSet.getString("Name")
                    + " - Author: " + resultSet.getString("Author")
                    + " - Price: " + resultSet.getInt("Price"));
        }
        return products;
    }
    public void menu() throws SQLException {
        System.out.println("==========Books=========");
        System.out.println("1. Add book");
        System.out.println("2. Save book");
        System.out.println("3. Display book");
        System.out.println("4. Exit");
    }

    public static void main(String[] args) throws SQLException {
        Book book = new Book();
        Run run = new Run();
        run.menu();
        while(true) {
            int choice;
            System.out.println("Your choice: ");
            choice = input.nextInt();
            if (choice == 1) {
                run.add(book);
                run.menu();
            } else if (choice == 2) {
                run.insert(book);
                run.menu();
            } else if (choice == 3) {
                run.getAll();
                run.menu();
            } else if (choice == 4) {
                System.exit(0);
            }
        }
    }
}