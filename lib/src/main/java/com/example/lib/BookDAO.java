package com.example.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Получить все книги
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setInventoryNumber(resultSet.getString("inventory_number"));
                book.setAuthor(resultSet.getString("author"));
                book.setTitle(resultSet.getString("title"));
                book.setPublisherId(resultSet.getInt("publisher_id"));
                book.setYear(resultSet.getInt("year"));
                book.setPages(resultSet.getInt("pages"));
                book.setStatusId(resultSet.getInt("status_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Добавить книгу
    public static void addBook(Book book) {
        String query = "INSERT INTO Books (inventory_number, author, title, publisher_id, year, pages, status_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, book.getInventoryNumber());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getTitle());
            statement.setInt(4, book.getPublisherId());
            statement.setInt(5, book.getYear());
            statement.setInt(6, book.getPages());
            statement.setInt(7, book.getStatusId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удалить книгу
    public static void deleteBook(int id) {
        String query = "DELETE FROM Books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Обновить книгу
    public static void updateBook(Book book) {
        String query = "UPDATE Books SET inventory_number = ?, author = ?, title = ?, publisher_id = ?, year = ?, pages = ?, status_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, book.getInventoryNumber());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getTitle());
            statement.setInt(4, book.getPublisherId());
            statement.setInt(5, book.getYear());
            statement.setInt(6, book.getPages());
            statement.setInt(7, book.getStatusId());
            statement.setInt(8, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Поиск книг по названию
    public static List<Book> searchBooksByTitle(String title) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books WHERE title LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setInventoryNumber(resultSet.getString("inventory_number"));
                book.setAuthor(resultSet.getString("author"));
                book.setTitle(resultSet.getString("title"));
                book.setPublisherId(resultSet.getInt("publisher_id"));
                book.setYear(resultSet.getInt("year"));
                book.setPages(resultSet.getInt("pages"));
                book.setStatusId(resultSet.getInt("status_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}