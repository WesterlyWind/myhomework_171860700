package Formation;

import World.World;
import Creature.*;
import Controller.Controller;

public class HengE extends Formation
{
	public void arrange(World world,Creature[] creatures,Controller controller)
	{
		controller.setPos(world.getLength()-1,2,creatures[0]);
		creatures[0].movetoPos(world.getLength()-1,2);
		controller.setPos(world.getLength()-1,4,creatures[1]);
		creatures[1].movetoPos(world.getLength()-1,4);
		controller.setPos(world.getLength()-1,6,creatures[2]);
		creatures[2].movetoPos(world.getLength()-1,6);
		controller.setPos(world.getLength()-2,3,creatures[3]);
		creatures[3].movetoPos(world.getLength()-2,3);
		controller.setPos(world.getLength()-2,5,creatures[4]);
		creatures[4].movetoPos(world.getLength()-2,5);
		controller.setPos(world.getLength()-2,7,creatures[5]);
		creatures[5].movetoPos(world.getLength()-2,7);
	}
}
