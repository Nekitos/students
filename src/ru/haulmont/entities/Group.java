package ru.haulmont.entities;

/**
 * Created by nikita on 11/29/14.
 */
public class Group {
    private long groupID;
    private int groupNumber;
    private String faculty;

    public Group() {

    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return groupNumber + " - " + faculty;
    }
}
