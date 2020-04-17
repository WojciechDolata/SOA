package library.backend;

import javax.ejb.EJB;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {

    @EJB
    private DatabaseManagerInterface databaseManager;


    @org.junit.jupiter.api.Test
    void getAllBooks() {
        System.out.println(databaseManager.getAllBooks());

    }
}