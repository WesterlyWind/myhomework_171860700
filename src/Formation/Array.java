package Formation;

import World.World;
import Creature.*;
import Controller.Controller;

public class Array extends Formation
{
	public void arrange(World world,Creature[] creatures,Controller controller)
	{
		controller.setPos(world.getLength()-1,5,creatures[0]);
		creatures[0].movetoPos(world.getLength()-1,5);
		controller.setPos(world.getLength()-2,4,creatures[1]);
		creatures[1].movetoPos(world.getLength()-2,4);
		controller.setPos(world.getLength()-3,5,creatures[2]);
		creatures[2].movetoPos(world.getLength()-3,5);
		controller.setPos(world.getLength()-3,3,creatures[3]);
		creatures[3].movetoPos(world.getLength()-3,3);
		controller.setPos(world.getLength()-3,4,creatures[4]);
		creatures[4].movetoPos(world.getLength()-3,4);
		controller.setPos(world.getLength()-3,6,creatures[5]);
		creatures[5].movetoPos(world.getLength()-3,6);
		controller.setPos(world.getLength()-4,4,creatures[6]);
		creatures[6].movetoPos(world.getLength()-4,4);
		controller.setPos(world.getLength()-5,5,creatures[7]);
		creatures[7].movetoPos(world.getLength()-5,5);
	}
}
