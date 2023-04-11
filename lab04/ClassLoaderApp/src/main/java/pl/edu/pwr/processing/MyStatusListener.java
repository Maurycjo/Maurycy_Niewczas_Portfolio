package pl.edu.pwr.processing;

import pl.edu.pwr.file.JavaClassFile;

public class MyStatusListener implements StatusListener{



    @Override
    public void statusChanged(Status s) {



        //classFile.changeMethodState(s.getProgress());
        System.out.println("eloo");
    }
}
