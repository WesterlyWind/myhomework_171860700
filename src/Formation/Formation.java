package Formation;

import Controller.Controller;
import Creature.*;
import World.World;
//������
public abstract class Formation 
{
	public abstract void arrange(World world,Creature[] creatures,Controller controller);
}
