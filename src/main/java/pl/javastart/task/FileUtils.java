package pl.javastart.task;

import java.io.*;
import java.util.List;

public class FileUtils implements Serializable {

    private void createFile(String fileName) {
        File file = new File(fileName);
        boolean fileExist = file.exists();
        if (!fileExist) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Nie udało się utworzyć pliku");
            }
        }
    }

    public void writeToFile(String fileName, List<Player> playerList) {
        createFile(fileName);
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter)
        ) {
            writer.write(playerList.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
