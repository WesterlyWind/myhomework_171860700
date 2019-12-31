package Creature;

public class CalabashBrothers {
	private CalabashBrother[] calabashbrothers;
	public CalabashBrothers()
	{
		calabashbrothers = new CalabashBrother[7];
		StandRandomly();
	}

	public CalabashBrother getBrother(int index) //获得队伍中第index个葫芦娃
	{
		return calabashbrothers[index];
	}
	public String getName(int index) {

		return calabashbrothers[index].getName();
	}
	public String getColor(int index) {

		return calabashbrothers[index].getColor();
	}
	public void StandRandomly() //葫芦娃随机站队
	{

		int[] sequence=new int[7];
		boolean[] chosen= {false,false,false,false,false,false,false};
        for(int i=0;i<7;i++)
        {
            sequence[i]=i;
        }
        //Random r=new Random();
        for(int i=0;i<7;i++)
        {        	
            int p=(int)(Math.random()*7);
            while(chosen[p]==true)
            {
            	p=(int)(Math.random()*7);
            }
            sequence[i]=p;
            chosen[p]=true;
        }
        for(int i=0;i<7;i++)
        {
        	calabashbrothers[i]=new CalabashBrother(CBinformation.values()[sequence[i]]);
        }
	}
}
