package pl.edu.pwr.file;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.stream.Stream;

public class CsvFileElement extends FileElement{



    public CsvFileElement(String filePath){
        super(filePath);
    }

    public String getFileNameWithInfo(){
        return "Csv  | " + getFileName();
    }


    public void readFile(){

        try(Stream<String> lines = Files.lines(getFilePath())){

            lines.forEach(line-> {
                System.out.println(line);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
