package TestTask;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner commandInputScanner = new Scanner(System.in);
        Scanner queryInputScanner = new Scanner(System.in);
        Repository repository = new Repository();

        CommandService commandService = new CommandService();

        System.out.println(Commands.START_MSG.getText());
        System.out.println(" - - - ");
        System.out.println(Commands.START_MSG_HELP.getText());
        System.out.println(Commands.START_MSG_NEW.getText());
        System.out.println(Commands.START_MSG_LIST.getText());

        while (true) {

            String message = commandInputScanner.nextLine();
            switch (message) {

                case "End" -> {
                    System.out.println(Commands.APP_OFF_MSG.getText());
                    System.exit(0);
                }

                case "New" -> {
                    System.out.println(Commands.NEW_NOTE_TITLE_MSG.getText());
                    String title = queryInputScanner.nextLine();
                    System.out.println(Commands.NEW_NOTE_TEXT_MSG.getText());
                    String text = queryInputScanner.nextLine();
                    repository.createNote(title, text);
                    System.out.println(Commands.NOTE_SAVED_MSG.getText());
                }

                case "Del" -> {
                    System.out.println(Commands.DEL_MSG.getText());
                    System.out.println(repository.deleteNoteByTitle(queryInputScanner.nextLine()));
                }

                case "All" -> {
                    repository.printAllNotes();
                }

                case "List" -> {
                    System.out.println(Commands.LIST_OF_NOTES_MSG.getText());
                    repository.printList();
                }

                case "Open" -> {
                    System.out.println(Commands.OPEN_TITLE_MSG.getText());
                    repository.openNoteByTitle(queryInputScanner.nextLine());
                }

                case "Edit" -> {
                    System.out.println(Commands.EDIT_TITLE_MSG.getText());
                    String oldTitle = queryInputScanner.nextLine();
                    if (repository.checkQueryByTitle(oldTitle)) {
                        System.out.println(Commands.NEW_TITLE_MSG.getText());
                        String newTitle = queryInputScanner.nextLine();
                        System.out.println(Commands.NEW_TEXT_MSG.getText());
                        String text = queryInputScanner.nextLine();
                        repository.editNoteByTitle(oldTitle, newTitle, text);
                    } else System.out.println(Commands.NOTE_NOT_FOUND_MSG.getText());
                }

                case "Help" -> {
                    commandService.printCommands();
                }

                default -> {
                    System.out.println(Commands.COMMAND_NOT_FOUND_MSG.getText());
                }
            }
        }
    }
}