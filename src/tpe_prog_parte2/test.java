package tpe_prog_parte2;

public class test {
	public static void main(String[] args) {
		CSVReader lector = new CSVReader("C:\\Users\\lucho\\git\\TPE_prog3\\src\\tpe_prog_parte2\\datasets\\dataset1.txt");
		lector.read();
		CSVReader lector2 = new CSVReader("C:\\Users\\lucho\\git\\TPE_prog3\\src\\tpe_prog_parte2\\datasets\\dataset2.txt");
		lector2.read();
		CSVReader lector3 = new CSVReader("C:\\Users\\lucho\\git\\TPE_prog3\\src\\tpe_prog_parte2\\datasets\\dataset3.txt");
		lector3.read();
	}
}
