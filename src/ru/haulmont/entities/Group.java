package ru.haulmont.entities;

/**
 * Created by nikita on 11/29/14.
 */
public class Group {
    private long groupID;
    private long groupNumber;
    private String faculty;

    public Group() {

    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public long getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(long groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
