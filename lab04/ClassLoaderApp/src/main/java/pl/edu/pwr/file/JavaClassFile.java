package pl.edu.pwr.file;

import java.nio.file.Path;
import java.util.ArrayList;

public class JavaClassFile extends FileElement{



    public JavaClassFile(Path filePath) {
        super(filePath);

    }


    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }
}
