package robot;
import java.util.ArrayList;
import java.util.Objects;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable{

	public static void main(String[] args) {
		
		float increment = 1.4f;
		float decrement = 0.6f;
		float probDefault = 1;
		Adm adm = new Adm();
		
		System.out.println("Digite a expressao sorteada");
		Scanner sc = new Scanner(System.in);
		String sort = sc.next();
		
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Se deseja começar uma nova matriz digite 0, se não digite 1");
		int option = sc1.nextInt();
		
		ArrayList<Dimension> dimensionL = new ArrayList<>();
		
		Dimension acao1 = new Dimension();
		Dimension acao2 = new Dimension();
		Dimension acao3 = new Dimension();
		Dimension acao4 = new Dimension();
		Dimension acao5 = new Dimension();
		Dimension acao6 = new Dimension();
		
		acao1.setName("1 - Alegria");
		acao2.setName("2 - Surpresa");
		acao3.setName("3 - Desprezo");
		acao4.setName("4 - Tristeza");
		acao5.setName("5 - Raiva");
		acao6.setName("6 - Nojo");

		dimensionL.add(acao1);
		dimensionL.add(acao2);
		dimensionL.add(acao3);
		dimensionL.add(acao4);
		dimensionL.add(acao5);
		dimensionL.add(acao6);
		
		ArrayList<Iten> itenL = new ArrayList<>();
		
		Iten expressao1 = new Iten();
		Iten expressao2 = new Iten();
		Iten expressao3 = new Iten();
		Iten expressao4 = new Iten();
		Iten expressao5 = new Iten();
		Iten expressao6 = new Iten();
		
		expressao1.setName("Desprezo");
		expressao2.setName("Alegria");
		expressao3.setName("Raiva");
		expressao4.setName("Tristeza");
		expressao5.setName("Nojo");
		expressao6.setName("Surpresa");
		
		itenL.add(expressao1);
		itenL.add(expressao2);
		itenL.add(expressao3);
		itenL.add(expressao4);
		itenL.add(expressao5);
		itenL.add(expressao6);
		
		ArrayList<ArrayList<Float>> matriz = new ArrayList<ArrayList<Float>>();
		
		if(option == 0) {
			
			for(int  i = 0; i < dimensionL.size(); i++) {
				ArrayList<Float> aux = new ArrayList<>();
				for(int j = 0; j < itenL.size(); j++) {
					aux.add(probDefault);
				}
				matriz.add(aux);
			}
			
		}
		else {
			try {
				FileInputStream fi = new FileInputStream("matrizRoboManha");
				ObjectInputStream oi = new ObjectInputStream(fi);
				matriz = (ArrayList<ArrayList<Float>>) oi.readObject();
			}
			catch(Exception e ){
				e.printStackTrace();
			}
		}
		
		int posDim = adm.selectDimension(itenL, dimensionL, sort, matriz, probDefault);
		
		System.out.println("A acao sorteada foi: " +dimensionL.get(posDim).getName());
		
		int posIten = 0;
		for(int i = 0; i < itenL.size(); i++ ) {
			if(Objects.equals(itenL.get(i).getName(),sort)) {
				posIten = i;
				break;
			}
		}
		
	
		
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Digite a acao advinhada");
		String iten = sc2.nextLine(); // expressao detectada

		adm.update(matriz, dimensionL, increment, decrement, sort, iten, posDim, posIten);
	
		for(int  i = 0; i < dimensionL.size(); i++) {
			for(int j = 0; j < itenL.size(); j++) {
				System.out.printf("%.2f    ", matriz.get(i).get(j));
			}
			System.out.println();
		}
		
		try {
			FileOutputStream fo = new FileOutputStream("matrizRoboManha");
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			oo.writeObject(matriz);
			oo.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		sc.close();
		sc1.close();
		sc2.close();
		
	}

}