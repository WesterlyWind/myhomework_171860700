package Formation;

import World.World;
import Creature.*;
import Controller.Controller;

public class LongSnake extends Formation
{
	public void arrange(World world,Creature[] creatures,Controller controller)
	{
		for(int i=0;i<8;i++)
		{
			controller.setPos(world.getLength()-1,i,creatures[i]);
			creatures[i].movetoPos(world.getLength()-1,i);
		}
	}
}
