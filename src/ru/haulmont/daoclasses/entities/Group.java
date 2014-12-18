package ru.haulmont.daoclasses.entities;

/**
 * Класс, представляющий сущность "Группа".
 * Инкапсулирует данные о группе.
 * Данные:
 * <ul>
 *     <li>ID(Код группы)</li>
 *     <li>groupNumber(Номер группы)</li>
 *     <li>Faculty(Факультет)</li>
 * </ul>
 */
public class Group {
    private long groupID;
    private int groupNumber;
    private String faculty;

    /**
     * Создает пустой экземпляр группы.
     */
    public Group() {}

    /**
     * Метод возвращает ID группы.
     * @return длинное целое -- ID группы
     */
    public long getGroupID() {
        return groupID;
    }

    /**
     * Метод записывает ID группы
     * @param groupID длинное целое -- ID группы
     */
    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    /**
     * Метод возвращает номер группы
     * @return четырехзначное целое -- номер группы
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Метод записывает номер группы
     * @param groupNumber четырехзначное целое -- номер группы
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * Метод возвращает название факультета
     * @return строка, содержащая название факультета
     */
    public String getFaculty() {
        return faculty;
    }

    /**
     * Метод записывает название факультета
     * @param faculty строка, содержащая название факультета
     */
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return groupNumber + " - " + faculty;
    }
}
