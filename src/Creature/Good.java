package Creature;
import World.World;

public class Good extends Creature{

	Good(int AP, int DP, int HP) {
		super(AP, DP, HP);
		// TODO Auto-generated constructor stub
	}
	Good(int AP,int DP,int HP,boolean isMotivated)
	{
		super(AP,DP,HP,isMotivated);
	}
	Good(int AP,int DP,int HP,boolean isMotivated,World world)
	{
		super(AP,DP,HP,isMotivated,world);
	}
}