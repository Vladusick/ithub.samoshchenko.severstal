package TestTask;

import java.util.ArrayList;
import java.util.List;

public class CommandService {

    public void printCommands() {
        List<String> commands = new ArrayList<>();

        commands.add(Commands.LIST_OF_COMMANDS.getText());
        commands.add(Commands.NEW.getText());
        commands.add(Commands.LIST.getText());
        commands.add(Commands.ALL.getText());
        commands.add(Commands.OPEN.getText());
        commands.add(Commands.DEL.getText());
        commands.add(Commands.EDIT.getText());
        commands.add(Commands.END.getText());
        commands.add(Commands.HELP.getText());

        for (String command : commands) {
            System.out.println(command);
        }
    }
}