import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LibraryGUI_DB extends JFrame {

    JTextField idField, titleField, authorField;
    JTextArea displayArea;

    // DB credentials
    static final String USER = "javauser";
static final String PASS = "Java@1234";

static final String URL =
"jdbc:mysql://127.0.0.1:3306/library_db?useSSL=false&serverTimezone=UTC";


    public LibraryGUI_DB() {
        setTitle("Library Management - MySQL");
        setSize(500, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Book ID:"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Title:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Author:"));
        authorField = new JTextField();
        panel.add(authorField);

        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View");
        JButton deleteBtn = new JButton("Delete");

        panel.add(addBtn);
        panel.add(viewBtn);
        panel.add(deleteBtn);

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        addBtn.addActionListener(e -> addBook());
        viewBtn.addActionListener(e -> viewBooks());
        deleteBtn.addActionListener(e -> deleteBook());
    }

    Connection getConnection() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(URL, USER, PASS);
}

    void addBook() {
        try (Connection con = getConnection()) {
            String sql = "INSERT INTO books VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setString(2, titleField.getText());
            ps.setString(3, authorField.getText());

            ps.executeUpdate();
            displayArea.setText("Book is added successfully !\n");
        } catch (Exception e) {
            displayArea.setText(" Error: " + e.getMessage());
        }
    }

    void viewBooks() {
    try (Connection con = getConnection()) {

        String sql = "SELECT * FROM books";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        displayArea.setText("Book List:\n");

        boolean hasData = false;
        while (rs.next()) {
            hasData = true;
            displayArea.append(
                rs.getInt("id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("author") + "\n"
            );
        }

        if (!hasData) {
            displayArea.append("No books found.\n");
        }

    } catch (Exception e) {
        displayArea.setText("ERROR:\n" + e.toString());
    }
}


    void deleteBook() {
        try (Connection con = getConnection()) {
            String sql = "Delete from which book id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idField.getText()));

            int rows = ps.executeUpdate();
            if (rows > 0)
                displayArea.setText("Book is deleted\n");
            else
                displayArea.setText("Book not found\n");
        } catch (Exception e) {
            displayArea.setText(" Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI_DB().setVisible(true));
    }
}
