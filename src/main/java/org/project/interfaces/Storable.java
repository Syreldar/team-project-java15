package org.project.interfaces;

import org.project.database.Database;

public interface Storable {
    void register(Database database);
}
