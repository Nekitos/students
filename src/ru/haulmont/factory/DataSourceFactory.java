package ru.haulmont.factory;

import ru.haulmont.daoclasses.DataSource;
import ru.haulmont.daoclasses.DerbyDataSource;

/**
 * Created by nikita on 12/18/14.
 */
public class DataSourceFactory {
    public static final int DERBY_DATA_SOURCE = 0;

    public static DataSource newDataSource(int type) {
        DataSource result = null;

        if (type == DERBY_DATA_SOURCE) {
            result = new DerbyDataSource();
            result.loadDatabase("jdbc:derby:../resources/studdb;create=true", null, null);
        }

        return result;
    }
}
