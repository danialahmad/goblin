package com.gen.entity;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class ActionTypeYesNo {
	private static final String SECTION_PUBLIC_BOOLEAN = "public Boolean get";
	private static final String LINE_COLUMN_TYPE_YES_NO = "    @Type(type=\"yes_no\")";
	private static final String LINE_IMPORT_HIBERNATE_TYPE = "import org.hibernate.annotations.Type;";

	public static void main(String[] args) throws IOException {
		String directory = args[0];
		Collection<File> files = FileUtils.listFiles(new File(directory), null,
				false);
		for (File file : files) {
			List<String> lines = FileUtils.readLines(file);
			lines = filterOut(lines);
			FileUtils.writeLines(file, lines);
		}
	}

	public static List<String> filterOut(List<String> lines) {
		List<String> result = new ArrayList<String>();
		boolean hit = false;
		for (String line : lines) {

			if (line.contains(SECTION_PUBLIC_BOOLEAN)) {
				result.add(LINE_COLUMN_TYPE_YES_NO);
				hit = true;
			}
			result.add(line);

		}
		if (hit) {
			List<String> linesToAdd = Arrays.asList("",
					LINE_IMPORT_HIBERNATE_TYPE);
			addToLastImport(linesToAdd, result);
		}
		return result;
	}

	public static void addToLastImport(List<String> newLines, List<String> lines) {
		int indexOfLastImport = findIndexOfLastImport(lines);
		lines.addAll(indexOfLastImport + 1, newLines);
	}

	public static int findIndexOfLastImport(List<String> lines) {
		int count = lines.size();
		int last = -1;
		for (int i = 0; i < count; i++) {
			if (lines.get(i).startsWith("import ")) {
				last = i;
			}
		}
		return last;
	}
}
