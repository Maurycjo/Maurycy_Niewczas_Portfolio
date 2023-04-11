package pl.edu.pwr.processing;

import pl.edu.pwr.file.JavaClassFile;

public class MyStatusListener implements StatusListener{

    private JavaClassFile classFile;
    public MyStatusListener(JavaClassFile classFile){
        this.classFile = classFile;
    }
    @Override
    public void statusChanged(Status s) {

        classFile.changeMethodState(s.getProgress());

    }
}
