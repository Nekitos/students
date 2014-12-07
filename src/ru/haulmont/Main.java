package ru.haulmont;

import ru.haulmont.daoclasses.DerbyDBStudents;
import ru.haulmont.entities.Group;
import ru.haulmont.entities.Student;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

public class Main {

    private static final String NAMES[] = {
        "Иван",
        "Петр",
        "Александр",
        "Алексей",
        "Владимир"
    };

    private static final String S_NAMES[] = {
        "Иванов",
        "Петров",
        "Сидоров"
    };

    private static final String PATRONYMICS[] = {
        "Михайлович",
        "Романович",
    };

    public static void main(String[] args) throws SQLException {
        DerbyDBStudents database = new DerbyDBStudents();
        database.loadDatabase("jdbc:derby:/home/nikita/Documents/CodingTime/jworkspace/students/studdb;", null, null);
        database.closeConnection();

        ArrayList<Student> students = new ArrayList<Student>();
/*        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            Student student = new Student();
            student.setGroupID(Math.abs(random.nextInt()) % 2);
            student.setName(NAMES[i % NAMES.length]);
            student.setSurname(S_NAMES[i % S_NAMES.length]);
            student.setPatronymic(PATRONYMICS[i % PATRONYMICS.length]);
            student.setBirthday(new Date(new GregorianCalendar(1992 + Math.abs(random.nextInt()) % 5, Math.abs(random.nextInt()) % 12, Math.abs(random.nextInt()) % 28).getTimeInMillis()));
            students.add(student);
        }*/

        ArrayList<Group> groups = (ArrayList)database.getAllGroups();
        for (Group g : groups) {
            System.out.println("ID: " + g.getGroupID());
            System.out.println("Number: " + g.getGroupNumber());
            System.out.println("Faculty: " + g.getFaculty());
            System.out.println();
        }

/*        for (Student s : students) {
            System.out.println("ID: " + students.indexOf(s));
            System.out.println("Name: " + s.getName());
            System.out.println("Surname: " + s.getSurname());
            System.out.println("Patronymic: " + s.getPatronymic());
            System.out.println("Birthday: " + s.getBirthday());
            System.out.println("Group: " + s.getGroupID());
            System.out.println();
        }*/
    }
}
