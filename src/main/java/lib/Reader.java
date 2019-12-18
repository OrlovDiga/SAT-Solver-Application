package lib;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private List<String> input;

    //reading input's values with help inputFile.
    public Reader(String fileName) throws IOException {
        input = new ArrayList<>();
        Files.lines(Paths.get(fileName), StandardCharsets.UTF_8).forEach(input::add);
    }

    //reading input's values with help arguments in constructor.
    public Reader(String... inputValues) {
        input = new ArrayList<String>();
        for (int i = 0; i < inputValues.length; i++) {
            input.add(inputValues[i]);
        }
    }

    public List<String> getInput() { return input; }

    public void setInput(List input) { this.input = input; }
}
//обработать исключения!!!
//доработтаь принятие данных (лист и тп)
