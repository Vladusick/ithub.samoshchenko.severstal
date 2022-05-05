package TestTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {

    public static final String DB_URL = "jdbc:sqlite:C:/Users/Vladislav/IdeaProjects/ithub.samoshchenko.severstal/notes";

    public void printAllNotes() {

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id, title, text FROM notes");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String text = resultSet.getString("text");
                System.out.printf("Номер заметки: %d%n Заголовок заметки: %s%n Текст заметки: %s%n - - - %n", id, title, text);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printList() {

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT title FROM notes");

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                System.out.println("- " + title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNote(String title, String text) {

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();

            String sql = String.format("INSERT INTO notes (title, text) VALUES ('%s', '%s')", title, text);
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String deleteNoteByTitle(String title) {
        String outgoingMessage = null;

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();

            String sql = String.format("DELETE FROM notes WHERE title = '%s'", title);
            int deleteСheck = statement.executeUpdate(sql);
            if (deleteСheck == 0) {
                outgoingMessage = Commands.NOTE_NOT_FOUND_MSG.getText();
            } else outgoingMessage = Commands.NOTE_DELETED_MSG.getText();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return outgoingMessage;
    }

    public void openNoteByTitle(String titleValue) {

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();

            String sql = String.format("SELECT text FROM notes WHERE title = '%s'", titleValue);
            statement.executeQuery(sql);

            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.isBeforeFirst()) {
                System.out.println(Commands.NOTE_NOT_FOUND_MSG.getText());
            }

            while (resultSet.next()) {
                String text = resultSet.getString("text");
                System.out.println("Текст заметки: " + text);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editNoteByTitle(String oldTitle, String newTitle, String text) {

        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();

            String sql = String.format("UPDATE notes SET title = '%s', text = '%s' WHERE title = '%s';", newTitle, text, oldTitle);
            int checkQuery = statement.executeUpdate(sql);
            if (checkQuery == 0) {
                System.out.println(Commands.NOTE_NOT_FOUND_MSG.getText());
            } else System.out.println(Commands.NOTE_CHANGE_MSG.getText());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkQueryByTitle(String title) {

        boolean result = false;
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            Statement statement = connection.createStatement();

            String sql = String.format("SELECT title FROM notes WHERE title = '%s'", title);
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.isBeforeFirst()) {
                result = false;
            } else result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}