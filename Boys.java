

public class Boys{
	String name;
	int attractive;
	int budget;
	int intelligence;
	int min_attraction;
	String status;
	public Boys(String a, int b,int c,int d,int e){
		name = a;
		attractive= b;
		budget = c;
		intelligence = d;
		min_attraction = e;
		status = "single";
	}
	/*TESTER FUNCTION
	public static void main(String args[]){
		Boys Amit =new Boys("Amit",5,1,4,7);
		System.out.println(Amit.name + "" + Amit.attractive+Amit.min_attraction);
	}*/
}
