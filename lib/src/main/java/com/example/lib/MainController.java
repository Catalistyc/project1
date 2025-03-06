package com.example.lib;

import com.almasb.fxgl.entity.action.Action;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.util.List;

public class MainController {
    @FXML
    private TableView<Book> booksTable;

    public void initialize() {
        // Загрузка данных из БД
        List<Book> books = BookDAO.getAllBooks();
        booksTable.getItems().addAll(books);
    }

    @FXML
    private void addBook() {
        // Создаем новую книгу
        Book newBook = new Book();
        newBook.setInventoryNumber("Новый инвентарный номер");
        newBook.setAuthor("Новый автор");
        newBook.setTitle("Новое название");
        newBook.setPublisherId(1); // Пример: ID издательства
        newBook.setYear(2023);
        newBook.setPages(300);
        newBook.setStatusId(1); // Пример: ID статуса

        // Добавляем книгу в базу данных
        BookDAO.addBook(newBook);

        // Обновляем таблицу
        booksTable.getItems().add(newBook);
    }

    @FXML
    private void deleteBook() {
        // Получаем выбранную книгу
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Удаляем книгу из базы данных
            BookDAO.deleteBook(selectedBook.getId());

            // Удаляем книгу из таблицы
            booksTable.getItems().remove(selectedBook);
        } else {
            System.out.println("Книга не выбрана!");
        }
    }

    @FXML
    private void editBook() {
        // Получаем выбранную книгу
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Редактируем данные книги (пример)
            selectedBook.setTitle("Новое название после редактирования");

            // Обновляем книгу в базе данных
            BookDAO.updateBook(selectedBook);

            // Обновляем таблицу
            booksTable.refresh();
        } else {
            System.out.println("Книга не выбрана!");
        }
    }

    @FXML
    private void searchBook() {
        // Пример поиска по названию
        String searchQuery = "Название для поиска";

        // Ищем книги в базе данных
        List<Book> foundBooks = BookDAO.searchBooksByTitle(searchQuery);

        // Очищаем таблицу и добавляем найденные книги
        booksTable.getItems().clear();
        booksTable.getItems().addAll(foundBooks);
    }

    @FXML
    private void calculateFine() {
        // Получаем выбранную книгу
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            // Пример: 5 дней просрочки
            int daysLate = 5;

            // Рассчитываем штраф
            double fine = FineCalculator.calculateFine(selectedBook.getYear(), daysLate);

            // Выводим результат
            System.out.println("Штраф за книгу '" + selectedBook.getTitle() + "': " + fine + " руб.");
        } else {
            System.out.println("Книга не выбрана!");
        }
    }
}