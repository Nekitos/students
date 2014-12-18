package ru.haulmont.factory;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.DerbyDataSource;

/**
 * Класс DataSourceFactory содержит фабричный метод, который создает
 * объект DataSource в заисимости от параметров. Класс удобен тем,
 * что если потребуется другая БД для хранения данных, то его можно
 * расширить добавив константу и вернув экземпляр на другое хранилище
 * данных.
 */
public class DataSourceFactory {
    public static final int DERBY_DATA_SOURCE = 0;

    /**
     * Возвращает DataSource в зависимости от переданного параметра
     * @param type константа указывающая на тип сервера базы данных
     * @return экземпляр DataSource
     */
    public static DataSource newDataSource(int type) {
        DataSource result = null;

        if (type == DERBY_DATA_SOURCE) {
            result = new DerbyDataSource();
            result.loadDatabase("jdbc:derby:../resources/studdb;create=true", null, null);
        }

        return result;
    }
}
