package data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import use_case.previous_search.PreviousSearchDataAccessInterface;

public class FileDataAccessObject implements PreviousSearchDataAccessInterface{
    
    String fileName;

    public FileDataAccessObject(String fileName) {
        this.fileName = fileName;
    }

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
                System.out.println("Coulnd't find file: " + fileName);
                e.printStackTrace();
            }
            return out.toArray(new String[out.size()]);
        } else {
            try {
                file.createNewFile();
            } catch(IOException ex) {
                System.out.println("Couldn't create file: " + fileName);
                ex.printStackTrace();
            }
            return new String[]{};
        }
    }

    public void addSearch(String search) {
        String[] searches = this.getPreviousSearches();
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(search);
            fw.write("\n");
            for(int i = 0; i < searches.length; i++) {
                fw.write(searches[i]);
                fw.write("\n");
            }
            fw.close();
        } catch(IOException e) {
            System.out.println("Couldn't find file: " + fileName);
            e.printStackTrace();
        }
    }
}
