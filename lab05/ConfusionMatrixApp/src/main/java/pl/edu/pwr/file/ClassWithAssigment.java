package pl.edu.pwr.file;

public class ClassWithAssigment {

    private String className;
    private int groupID;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public ClassWithAssigment(String className, int groupID) {
        this.className = className;
        this.groupID = groupID;
    }
}
