package org.project.interfaces;

import org.project.database.Database;
import org.project.charts.Chart;

public interface Storable {
    void register(Database database, Chart chart);
}
