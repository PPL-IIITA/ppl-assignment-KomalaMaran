

public class Girls{
	String name;
	int attractive;
	String bf;
	int type;
	int budget;
	int intelligence;
	public Girls(String a, int b,int c,int d){
		name = a;
		attractive= b;
		budget = c;
		intelligence = d;
		bf="";
		type = (int)(Math.random()*3);
	}
}
