import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class createDNA {
	
	public static void main(String[] args) throws IOException {
		int n = 8000;
		int[] characters = new int[] {'a','c','g','t'};
		Random r = new Random(0);
		FileOutputStream out;
		try {
			out = new FileOutputStream(new File("data/temp" + n/1000 + ".txt"));
			for(int i = 0; i < n; i++) {
				out.write(characters[r.nextInt(4)]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	