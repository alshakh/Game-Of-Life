package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Ahmed Alshakh <ahmed.s.alshakh@gmail.com>
 */
public class Utils {
	
    public static String readFile(File file) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append("\n").append(sCurrentLine);
			}
		} catch (IOException e) {
            System.out.println("Error opening the file : " + file.getPath());
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				sb.delete(0, sb.length());
			}
		}
        return sb.toString();
    }
    public static void saveFile(File file, String contents) throws IOException {
        // if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(contents);
			bw.close();
    }
    public boolean WriteFile(String content, File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(content);
            writer.close();
        } catch (FileNotFoundException ex) {
            return false;
        }
        return true;
    }
    public static String visualize(boolean[][] data) {
        StringBuilder sb = new StringBuilder();
        for(boolean[] ba : data) {
            for(boolean b : ba) {
                if(b)sb.append("\u2588\u2588");
                else sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
