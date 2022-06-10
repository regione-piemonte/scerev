/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scadenzaassistenza.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import it.csi.scerev.scadenzaassistenza.configuration.Configuration;

public class FileManager {

    public static final String FILE_PATH = Configuration.get("file.path");
    public static final String FILE_NAME = Configuration.get("file.name");

    //-----------------------------------------------------------

    public static File getFile() throws FileNotFoundException {
        System.out.println(FILE_PATH);
        System.out.println(FILE_NAME);
        return getFile(FILE_PATH, FILE_NAME);
    }

    public static File getFile(String filePath, String fileName) throws FileNotFoundException {
    	File file = new File(FILE_PATH + FILE_NAME);
    	if(file.exists() && !file.isDirectory()) return file;
    	else throw new FileNotFoundException("File not found");
    }
    
    public static <T> List<T> getCSVFromClass(Class<T> c) throws Exception {
    	File file = getFile();
    	return convertCSVtoClass(file, c);
    }
    
    public static <T> List<T> convertCSVtoClass(File f, Class<T> c) throws Exception {
    	List<T> list = new ArrayList<T>();
    	List<String[]> csv = readCsv(f);
    	
    	Constructor<T> constructor = c.getConstructor(String[].class);
    	for(String[] s : csv) {
    		T record = constructor.newInstance(new Object[]{s});
    		list.add(record);
    	}
    	
    	return list;
    }

	public static List<String[]> readCsv(File f) {
		BufferedReader br = null;
		List<String[]> csv = new ArrayList<String[]>();
		try {
			String line = "";
			String cvsSplitBy = ",";
			
			br = new BufferedReader(new FileReader(f));			
			String[] head = null; // intestazione
			while ((line = br.readLine()) != null) {
				if(head==null) head = line.split(cvsSplitBy);
				else {
					// rimuovo eventuali doppi apici
					if(line.contains("\"")) line = line.replaceAll("\"", "");
					
					String[] row = line.split(cvsSplitBy);
					csv.add(row);
				}
			}
			return csv;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

    //-----------------------------------------------------------
}
