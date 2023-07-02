package tpe_prog_parte2;

public class test {
	public static void main(String[] args) {
		CSVReader lector = new CSVReader("src/tpe_prog_parte2/datasets/dataset1.txt");
		lector.read();
		System.out.println("");
		CSVReader lector2 = new CSVReader("src/tpe_prog_parte2/datasets/dataset2.txt");
		lector2.read();
		System.out.println("");
		CSVReader lector3 = new CSVReader("src/tpe_prog_parte2/datasets/dataset3.txt");
		lector3.read();
	}
}
