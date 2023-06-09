package Control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The code of this class is copied from the
 * Learning Journey Application of Group 37
 * and written by our group.
 */

public class FileControl {
    private FileReader fileReader;
    private FileWriter fileWriter;
    private Map<String, Integer> headerMap;
    private int lineCount;
    private String[][] fileData;
    private String[] headers = new String[1024];

    public FileControl() {
        fileReader = null;
        headerMap = null;
        lineCount = 0;
    }

    // You should open a file by its path
    public int open(String csvPath) {
        if (fileReader != null) {
            System.out.println("Another file is already opened, close it before open another one.");
            return 0;
        }
        try {
            fileReader = new FileReader(new File(csvPath));
            fileWriter = new FileWriter(new File(csvPath), true);
        } catch (Exception e) {
            System.out.println("Error opening file");
            return 1;
        }

        loadAllCsvData();
        return 0;
    }

    // close the current file
    public int close() {
        if (fileReader == null) {
            System.out.println("close(): No file is opened, closing operation is invalid.");
            return 0;
        }
        try {
            fileReader.close();
            fileReader = null;
            fileWriter.close();
            fileWriter = null;
        } catch (IOException e) {
            System.out.println("close(): Error closing file.");
            return 1;
        }

        return 0;
    }

    // need to be called every time loading a file, or when the current file is
    // changed
    public int loadAllCsvData() {
        if (fileReader == null) {
            System.out.println("loadAllCsvData(): You should open a file before reading from it.");
            return 1;
        }

        // create a new buffered reader
        BufferedReader reader = new BufferedReader(fileReader);

        String headerLine = null;
        try {
            headerLine = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        headers = headerLine.split(",");

        // recreate header map for the current file
        headerMap = new HashMap<>();
        headers = headerLine.split(",");

        for (int i = 0; i < headers.length; i++) {
            headerMap.put(headers[i], i);
        }

        // get the rest of the lines
        String line;
        lineCount = 0;
        // buffer with maximum data rows of 1024
        fileData = new String[1024][headers.length];
        while (true) {
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (line == null) {
                break;
            }
            String[] data = line.split(",");
            for (int i = 0; i < data.length; i++) {
                fileData[lineCount][i] = data[i];
            }
            lineCount++;
        }

        return 0;
    }

    // append a single string to the end of the current file
    public int append(String str) {
        if (fileReader == null) {
            System.out.println("append(): You should open a file before reading from it.");
            return 1;
        }

        try {
            fileWriter.write(str + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    // -1: val not found
    public int getFirstRowIndexByHeaderAndVal(String header, String val) {
        if (fileReader == null) {
            System.out.println("getFirstRowIndexByHeaderAndVal(): You should open a file before reading from it.");
            return -1;
        }

        int columnIndex = headerMap.get(header);

        for (int rowIndex = 0; rowIndex < lineCount; rowIndex++) {
            System.out.print(rowIndex);
            if (fileData[rowIndex][columnIndex].equals(val)) {
                return rowIndex;
            }
        }

        return -1;
    }

    public int getLast(String header, String val) {
        if (fileReader == null) {
            System.out.println("getLatestRowIndexByHeaderAndVal(): You should open a file before reading from it.");
            return -1;
        }

        int columnIndex = headerMap.get(header);

        for (int rowIndex = lineCount - 1; rowIndex >= 0; rowIndex--) {
            if (fileData[rowIndex][columnIndex].equals(val)) {
                return rowIndex;
            }
        }

        return -1;
    }

    public String getElement(String header, int rowIndex) {
        if (fileReader == null) {
            System.out.println("getElement(): You should open a file before reading from it.");
            return null;
        }

        int columnIndex = headerMap.get(header);
        return fileData[rowIndex][columnIndex];
    }

    public String getElement(int columnIndex, int rowIndex) {
        if (fileReader == null) {
            System.out.println("getElement(): You should open a file before reading from it.");
            return null;
        }

        return fileData[rowIndex][columnIndex];
    }

    public String[] getRow(int rowIndex) {
        if (fileReader == null) {
            System.out.println("getRow(): You should open a file before reading from it.");
            return null;
        }

        return fileData[rowIndex];
    }

    public String[] getHeaders() {
        if (fileReader == null) {
            System.out.println("getHeaders(): You should open a file before reading from it.");
            return null;
        }

        return headers;
    }

    public int deleteRecord(String header, String val) {
        if (fileReader == null) {
            System.out.println("deleteRecord(): You should open a file before reading from it.");
            return 1;
        }

        int columnIndex = headerMap.get(header);
        int rowIndex = getLast(header, val);
        if (rowIndex == -1) {
            System.out.println("deleteRecord(): Record not found.");
            return 1;
        }

        // delete the record
        for (int i = rowIndex; i < lineCount - 1; i++) {
            for (int j = 0; j < headers.length; j++) {
                fileData[i][j] = fileData[i + 1][j];
            }
        }
        lineCount--;

        // write the new data to the file
        try {
            fileWriter = new FileWriter(new File("src\\Data\\BankAccount.csv"));
            fileWriter.write(String.join(",", headers) + "\n");
            for (int i = 0; i < lineCount; i++) {
                fileWriter.write(String.join(",", fileData[i]) + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

    public int addRecord(String[] lineHeaders, String[] lineData) {
        if (fileReader == null) {
            System.out.println("addRecord(): You should open a file before reading from it.");
            return 1;
        }

        // check if the headers are valid
        for (String header : lineHeaders) {
            if (!headerMap.containsKey(header)) {
                System.out.println("addRecord(): Invalid header.");
                return 1;
            }
        }

        // align the data to the headers
        String[] alignedData = new String[headers.length];
        for (int i = 0; i < headers.length; i++) {
            alignedData[i] = "";
        }
        for (int i = 0; i < lineHeaders.length; i++) {
            alignedData[headerMap.get(lineHeaders[i])] = lineData[i];
        }

        // append the new data to the file
        try {
            fileWriter = new FileWriter(new File("src\\Data\\BankAccount.csv"), true);
            fileWriter.write(String.join(",", alignedData) + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}