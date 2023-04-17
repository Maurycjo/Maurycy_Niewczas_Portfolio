package pl.edu.pwr.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public class CsvFile {



    private Path filePath;
    private ArrayList<ClassWithAssigment> classWithAssigmentArrayList = new ArrayList<>();

    public ArrayList<ClassWithAssigment> getClassWithAssigmentArrayList() {
        return classWithAssigmentArrayList;
    }

    public CsvFile(Path filePath) {
        this.filePath = filePath;
    }

    public void readFile(){

        try(Stream<String> lines = Files.lines(filePath)){

            lines.forEach(line-> {

                String[] result = line.split(",");

                String className = result[0];
                int classID = Integer.parseInt(result[1]);

                classWithAssigmentArrayList.add(new ClassWithAssigment(className, classID));
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
