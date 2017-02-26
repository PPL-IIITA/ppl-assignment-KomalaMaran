public class Couples{
	Boys boy;
	Girls girl;
	int compatibility;
	long happiness;
	Gifts[] gifts= new Gifts[100];
	int no_gifts;
	public Couples(Boys a, Girls b){
		boy = a;
		girl  = b;
		compatibility = (boy.budget-girl.budget)+Math.abs(boy.attractive-girl.attractive)+ 
		Math.abs(boy.intelligence - girl.intelligence);
		happiness = 0;
		no_gifts=0;
	}
}
