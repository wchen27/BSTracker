package use_case.previous_search;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.Test;

import data_access.FileDataAccessObject;
import io.github.cdimascio.dotenv.Dotenv;

public class FileDataAccessObjectTest {
   
    @Test
    public void getPreviousSearchesTest() {
        Dotenv env = Dotenv.configure().filename("test.env").load();
        FileDataAccessObject dao = new FileDataAccessObject("testSearches.txt");
        String[] searches = dao.getPreviousSearches();
        assertArrayEquals(searches, new String[]{"Test"});
    }

    @Test
    public void getPreviousSearchesFileNotFoundTest() {
        Dotenv env = Dotenv.configure().filename("test.env").load();
        FileDataAccessObject dao = new FileDataAccessObject("doesntexist.txt");
        assertThrows(FileNotFoundException.class, () -> {dao.getPreviousSearches();});
    }
    
}
