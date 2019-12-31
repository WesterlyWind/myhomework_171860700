package Creature;
import World.World;

public class Bad extends Creature{

	Bad(int AP, int DP, int HP) {
		super(AP, DP, HP);
		// TODO Auto-generated constructor stub
	}
	Bad(int AP,int DP,int HP,boolean isMotivated)
	{
		super(AP,DP,HP,isMotivated);
	}
	Bad(int AP,int DP,int HP,boolean isMotivated,World world)
	{
		super(AP,DP,HP,isMotivated,world);
	}
}