package com.asiainfo.dmcweb.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONArray;

public class FileUtil {
	
	public static String ReadFile(String Path) {
		
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

	public static void main(String[] args) {
		String dir = "\\dmcWeb\\js\\theme.json";
		System.out.println(ReadFile("D:\\eclipse\\jee\\dmcWeb\\src\\main\\webapp\\js\\theme.json"));
		String ja = ReadFile("D:\\eclipse\\jee\\dmcWeb\\src\\main\\webapp\\js\\theme.json");
		JSONArray fromObject = JSONArray.fromObject(ja);
		System.out.println(fromObject.toString());
		
	}
}
