package World;
import Creature.*;

public class Map <T extends Creature>
{
	private T creature;
	Map(){ creature=null;}
	Map(T t){creature =t;}
	public Creature getCreature() 
	{
		return (Creature) creature;
	}
}
