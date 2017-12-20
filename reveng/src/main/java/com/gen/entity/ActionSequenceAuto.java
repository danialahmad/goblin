package com.gen.entity;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class ActionSequenceAuto {
	private static final String LINE_IMPORT_AUTO = "import static javax.persistence.GenerationType.AUTO;";
	private static final String LINE_IMPORT_SEQUENCE = "import static javax.persistence.GenerationType.SEQUENCE;";
	private static final String SECTION_GENERATE_SEQUENCE = "@GeneratedValue(strategy=SEQUENCE, generator=\"generator\")";
	private static final String PARAMETER_GENERATE_SEQUENCE = "strategy=SEQUENCE";
	private static final String PARAMETER_GENERATE_AUTO = "strategy=AUTO";

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
		for (String line : lines) {

			if (line.equals(LINE_IMPORT_SEQUENCE)) {
				line = LINE_IMPORT_AUTO;
			}
			if (line.contains(SECTION_GENERATE_SEQUENCE)) {
				line = line.replace(PARAMETER_GENERATE_SEQUENCE,
						PARAMETER_GENERATE_AUTO);
			}
			result.add(line);

		}
		return result;
	}
}
