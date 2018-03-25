/*
 * File: FileData.java
 * Author: Alex Bailey
 * Date: 24 March 18
 * Purpose: Called by Driver class and reads input from file.  Returns array of String objects.
 */

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileData {
    // instance vars
    BufferedReader inputStream = null;
    ArrayList<String>  dataList = new ArrayList<>();

    // constructor
    FileData(String fileName){
        File inFile = new File(fileName);
        readData(inFile);
    }

    // getters and setters
    public ArrayList<String> getDataList() {
        return dataList;
    }

    // methods
    private void readData(File inFile) {
        String input;
        try {
            // create buffered input inputstream
            inputStream = new BufferedReader(new FileReader(inFile));
            while((input = inputStream.readLine()) != null) {
                dataList.add(input);  // add read line to array
            }
        } catch (IOException io) {
            System.out.println("Error opening file: " + io.getMessage());
        }

        try {
            if(inputStream != null) {
                inputStream.close();
            }
        } catch (IOException io) {
            System.out.println("Error closing inputStream " + io.getMessage());
        }
    }
}
