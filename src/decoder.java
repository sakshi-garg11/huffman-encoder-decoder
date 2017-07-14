import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class decoder {

	public static void decode(File input, File code_table) throws Exception {
		FileWriter fw = new FileWriter(new File("decoded.txt"));
		// InputStream is = new BufferedInputStream(new
		// FileInputStream("encoded.bin"));
		BitSet bs;
		try {
			byte[] byteArray = Files.readAllBytes(input.toPath());
			bs = BitSet.valueOf(byteArray);
			StringBuilder encodedString = new StringBuilder();
			// for (int i = 0; i <= bs.length(); i++) {
			for (int i = 0; i < byteArray.length * 8; i++) {
				if (bs.get(i)) {
					encodedString.append("1");
				} else {
					encodedString.append("0");
				}
			}

			// System.out.println(encodedString);

			Map<String, String> codeTable = read_code_table(code_table);

			int start = 0;
			int end = 1;
			int length = encodedString.length();
			if (length <= 0) {
				return;
			}

			StringBuilder sb = new StringBuilder();
			String s = "";
			int count = 0;
			while (count < encodedString.length()) {
				char c = encodedString.charAt(count);
				s = s + c;
				if (codeTable.containsKey(s)) {

					sb.append(codeTable.get(s) + "\n");
					s = "";

				}
				count++;
				
			}
			fw.write(sb.toString());
			fw.flush();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fw.close();
		}

	}

	public static Map<String, String> read_code_table(File codeTableFile) throws IOException {
		Scanner sc = new Scanner(codeTableFile);

		Map<String, String> codeTable = new HashMap<String, String>();
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			String[] parts = str.split(" ");

			codeTable.put(parts[1], parts[0]);
		}

		sc.close();
		return codeTable;
	}

public static void main(String args[]) throws Exception{
	File file_encode = new File(args[0]);
	File file_codetable = new File(args[1]);
	decode(file_encode, file_codetable);
}

}
