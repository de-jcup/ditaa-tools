package de.jcup.ditaa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestFileLoader {
	private static File testScriptRootFolder = new File("./jcup-ditaa-parser/src/test/resources");
	static{
		if (!testScriptRootFolder.exists()){
			// workaround for difference between eclipse test and gradle execution (being in root folder...)
			testScriptRootFolder = new File("./../jcup-ditaa-parser/src/test/resources");
		}
	}
	
	public static List<String> fetchAllTestScriptNames() {
		assertTestscriptFolderExists();
		List<String> list = new ArrayList<>();
		for (File file: testScriptRootFolder.listFiles()){
			list.add(file.getName());
		}
		return list;
	}
	
	public static String loadScriptFromTestScripts(String testScriptName) throws IOException{
		assertTestscriptFolderExists();
		
		File file = new File(testScriptRootFolder,testScriptName);
		if (!file.exists()){
			throw new IllegalArgumentException("Test case corrupt! Test script file does not exist:"+file);
		}
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = null;
			while ((line=br.readLine())!=null){
				sb.append(line);
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	private static void assertTestscriptFolderExists() {
		if (!testScriptRootFolder.exists()){
			throw new IllegalArgumentException("Test setup corrupt! Root folder of test scripts not found:"+testScriptRootFolder);
		}
	}
}
