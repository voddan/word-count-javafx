package word_counter_javafx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Arrays;

public class Controller {
    public Stage stage;

    public TextField path;
    public Button file;
    public Label result;

    private FileChooser fileChooser = new FileChooser();

    public void chooseFile() {
        assert(stage != null);
        File file = fileChooser.showOpenDialog(stage);

        if(file == null) return;

        path.setText(file.getPath());

        try {
            Reader reader = new FileReader(file);
            long res = countWords(reader);
            result.setText("counted " + Long.toString(res) + " words in " + file.getName());
        } catch (FileNotFoundException e) {
            result.setText("unable to open " + file.getPath());
        }
    }

    private static long countWords(Reader reader) {
        return (new BufferedReader(reader))
                .lines()
                .flatMap(str -> Arrays.stream(str.split("\\s")))
                .filter(str -> !str.trim().equals(""))
                .count();
    }
}
