package app.clickablo.saveload;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SaveLoadService {

    private static final String SAVE_PATH = "./Clickablo/saves/";
    private final Path saveDir;
    private final ObjectMapper mapper = new ObjectMapper();

    public SaveLoadService() throws IOException {
        String userHome = System.getProperty("user.home");
        this.saveDir = Paths.get(userHome, SAVE_PATH);
        if (!Files.exists(saveDir)) {
            Files.createDirectories(saveDir);
        }
    }

    public void saveCharacter(Character character) throws IOException {
        String fileName = character.getName() + ".json";
        File outputFile = new File(saveDir.toString(), fileName);
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(outputFile, character);
    }

    public List<Character> loadCharacters() throws IOException {
        List<Character> characters = new ArrayList<>();
        try (Stream<Path> stream = Files.list(saveDir)) {
            stream.filter(p -> p.toString().endsWith(".json"))
                    .forEach(p -> {
                        try {
                            Character c = mapper.readValue(p.toFile(), Character.class);
                            characters.add(c);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
        return characters;
    }

    public Character loadCharacter(String fileName) throws IOException {
        File inFile = saveDir.resolve(fileName).toFile();
        return mapper.readValue(inFile, Character.class);
    }

}
