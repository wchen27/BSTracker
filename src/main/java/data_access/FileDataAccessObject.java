package data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import use_case.previous_search.PreviousSearchDataAccessInterface;


/*
 * Allows for access of the data from the file
 */
public class FileDataAccessObject implements PreviousSearchDataAccessInterface{
    
    String fileName;

    public FileDataAccessObject(String fileName) {
        this.fileName = fileName;
    }

    /*
     * Gets all the previous searches done by the users
     */
    public String[] getPreviousSearches() {
        File file = new File(fileName);
        if(file.exists()) {
            ArrayList<String> out = new ArrayList<>();
            try {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()) {
                    out.add(scanner.nextLine());
                }
                scanner.close();
            } catch(FileNotFoundException e) {
                try{
                    file.createNewFile();
                } catch(IOException ex) {
                    System.out.println("Couldn't create file: " + fileName);
                    ex.printStackTrace();
                }
            }
            return out.toArray(new String[out.size()]);
        }
        return new String[]{};
    }

    public void addSearch(String search) {
        String[] prev = getPreviousSearches();
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(search);
            for(int i = 0; i < prev.length; i++) {
                fw.write("\n");
                fw.write(prev[i]);
            }
            fw.close();
        } catch(IOException e) {
            System.out.println("Couldn't find file: " + fileName);
            e.printStackTrace();
        }
    }
}
