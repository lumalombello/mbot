package robot;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Adm {
	
	public int randomProb(ArrayList<Float> probMapping) {
		float aux = 0f;
		
		for(int i = 0; i < probMapping.size(); i++) {
			aux = aux + probMapping.get(i);
		}
		
		Random rand = new Random();
		int raffle = rand.nextInt((int)aux);
	
		return raffle;
	}
	
	public int selectDimension(ArrayList<Iten> itenL, ArrayList<Dimension> dimensionL, String iten, ArrayList<ArrayList<Float>> matriz, float probDefault) {
		
		int i;
		for(i = 0; i < itenL.size(); i++) {
			if(Objects.equals(itenL.get(i).getName(), iten)) {
				break;
			}
		}
		
		if(i >= itenL.size()){
			Iten new_iten = new Iten(iten);
			itenL.add(new_iten);
			
			for(int j = 0; j < dimensionL.size(); j++){
				matriz.get(j).add(probDefault);
			}
		}
		
		ArrayList<Float> probMapping = new ArrayList<Float>();
		for(int j = 0; j < dimensionL.size(); j++) {
			probMapping.add(matriz.get(j).get(i));
		}
		
		
		return randomProb(probMapping);
	}
	
	public ArrayList<ArrayList<Float>> new_dimension(String nDim, ArrayList<Dimension> dimensionL, ArrayList<Iten> itenL, ArrayList<ArrayList<Float>> matriz, float probDefault){
		Dimension novo = new Dimension(nDim);
		dimensionL.add(novo);
		
		ArrayList<Float> new_Dim = new ArrayList<>();
		for(int i = 0; i < itenL.size(); i++) {
			new_Dim.add(probDefault);
		}
	
		matriz.add(new_Dim);
		return matriz;
	}
	
	public ArrayList<ArrayList<Float>> update(ArrayList<ArrayList<Float>> matriz, ArrayList<Dimension> dimensionL, float increment, float decrement, String sort, String iten, int pos_Dim, int pos_Iten){
		
		System.out.println("Expressao Advinhada: " +iten + " Expressao Sorteada: " +sort + " Posicao da acao: " +pos_Dim + " Posicao da Expressao " +pos_Iten);
	
		if(Objects.equals(sort, iten)) {
			
			for(int i =0 ; i < dimensionL.size(); i++) {
				if(i != pos_Dim) {
				matriz.get(i).set(pos_Iten, matriz.get(i).get(pos_Iten)*decrement);
				}
				else {
					matriz.get(pos_Dim).set(pos_Iten, matriz.get(pos_Dim).get(pos_Iten)*increment);
				}
			}
		}
		else {
			matriz.get(pos_Dim).set(pos_Iten, matriz.get(pos_Dim).get(pos_Iten)/increment);
		}
	
		return matriz;
	}
}