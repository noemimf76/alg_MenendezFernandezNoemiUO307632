package p3;

public class Sustraccion4
{

static long cont;

public static void rec4 (int n)
{
	if (n<=0) 
		cont++;
	else
	{
		for (int i=0;i<n;i++) cont++; // O(n)
		for (int i=0;i<n;i++){
			for (int k=0;k<n;k++){
				 cont++;
			}
		}
		rec4 (n-1);
		 // O(n)
	}
}



public static void main (String arg []) 
{
	long t1,t2,cont;
	int nVeces= Integer.parseInt (arg [0]);
	 
	for (int n=20;n<=100;n++)
	{
		t1 = System.currentTimeMillis ();

		for (int repeticiones=1; repeticiones<=nVeces;repeticiones++)
		{ 		
			cont=0;
			rec4 (n);

		} 

		t2 = System.currentTimeMillis ();

		System.out.println (" n="+n+ "**TIEMPO="+(t2-t1)+"**nVeces="+nVeces);
	}  // for
} // main
} //class