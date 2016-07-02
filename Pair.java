
public class Pair {
	private char value;
	private double prob;
	public Pair(char value, double prob){
		this.value=value;
		this.prob=prob;
	}
	public void setValue(char value){
		this.value=value;
	}
	public void setProb(double prob){
		this.prob=prob;
	}
	
	public char getValue(){
		return value;
	}
	public double getProb(){
		return prob;
	}
	public String toString(){
		return value+"\t"+prob;
	}
}	
