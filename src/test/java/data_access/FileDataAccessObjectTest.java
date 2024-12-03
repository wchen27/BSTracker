package data_access;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import data_access.FileDataAccessObject;
import io.github.cdimascio.dotenv.Dotenv;

public class FileDataAccessObjectTest {

    @Test 
    public void createPreviousSearchesTest() {
        new File("testSearches.txt").delete();
        FileDataAccessObject dao = new FileDataAccessObject("testSearches.txt");
        dao.getPreviousSearches();
        assertTrue(new File("testSearches.txt").exists());
    }

    @Test
    public void addSearchTest() {
        FileDataAccessObject dao = new FileDataAccessObject("testSearches.txt");
        dao.addSearch("Test");
        String[] searches = dao.getPreviousSearches();
        assertArrayEquals(searches, new String[]{"Test"});
    }

    @Test
    public void addSearchMultipleTest() {
        FileDataAccessObject dao = new FileDataAccessObject("testSearches.txt");
        dao.addSearch("Another");
        String[] searches = dao.getPreviousSearches();
        assertArrayEquals(searches, new String[]{"Another","Test"});
    }
    
}
