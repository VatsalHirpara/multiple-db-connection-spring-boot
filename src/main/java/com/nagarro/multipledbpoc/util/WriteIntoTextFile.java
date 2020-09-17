package com.nagarro.multipledbpoc.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

public class WriteIntoTextFile {

	public WriteIntoTextFile() {
	}

	public static void writeString(File file, String data) {
		try {
			FileUtils.writeStringToFile(file, data + "\n", StandardCharsets.UTF_8, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
