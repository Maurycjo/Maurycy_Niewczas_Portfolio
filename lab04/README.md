To run application: java -jar ClassLoaderApp-1.0-SNAPSHOT.jar
This application has the ability to dynamicly load classes. Classes have to implement interface processor.

![classLoader](https://github.com/Maurycjo/Maurycy_Niewczas_Portfolio/assets/59066809/2a0c03fc-319d-4dd8-a2a2-f0c141c5526a)

On this application you can load, unload classes. You can also invoking methods of these classes. 

Interface Processor


public interface Processor {
	
	boolean submitTask(String task, StatusListener sl);
	String getInfo();
	String getResult();
}
