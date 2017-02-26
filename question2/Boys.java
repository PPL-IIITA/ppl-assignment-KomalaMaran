

public class Boys{
	String name;
	int attractive;
	int type;
	String status;
	int spent;
	int budget;
	int intelligence;
	int min_attraction;
	
	public Boys(String a, int b,int c,int d,int e){
		name = a;
		attractive= b;
		budget = c;
		intelligence = d;
		min_attraction = e;
		status = "single";
		spent=0;
		type = (int)(Math.random()*3);
	}
}
