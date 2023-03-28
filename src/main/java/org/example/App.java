package org.example;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App
{

    public static void main(String[] args) throws FileNotFoundException
    {
        String rootDir = args[0];
        File root = new File(rootDir);
        String[] extensions = {"java"};

        for (File file : FileUtils.listFiles(root, extensions, true))
        {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine().trim().toUpperCase();

                if (line.contains("\"SELECT ") || line.contains("INSERT INTO") || line.contains("\"UPDATE ") || line.contains("CREATE TABLE "))
                {
                    int index = line.toUpperCase().indexOf("FROM");

                    if (index != -1)
                    {
                        String tableName = line.substring(index + 5).trim();

                        if (tableName.contains(" "))
                        {
                            if (!tableName.substring(0, tableName.indexOf(" ")).equals("\""))
                            {
                                tableName = tableName.substring(0, tableName.indexOf(" "));
                            }
                        }
                        System.out.println("Table name: " + tableName);
                    }
                    else
                    {
                        System.out.println("Line : " + line);
                    }
                }
            }

            scanner.close();
        }
    }
}

