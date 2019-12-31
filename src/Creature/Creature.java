package Creature;

import World.World;
import Creature.*;
import World.World;
import javafx.application.Platform;
import java.util.Random;

public class Creature implements Runnable//每个Creature是一个线程
{

	protected int x, y;//坐标
	
	public void movetoPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	static int count=0;
	int id;
	protected int AP;//Attack Power
	protected int DP;//Defensive Power
	protected int HP;//Health Point
	protected int fullHP;
	protected boolean Motivated;
	protected boolean living;
	protected World world;
	
	Creature(int AP, int DP, int HP){
		id=count;
		count++;
		this.AP = AP;
		this.DP = DP;
		this.HP = HP;
		fullHP=HP;
		Motivated = false;
		living = true;
	}
	Creature(int AP, int DP, int HP, boolean Motivated){
		id=count;
		count++;
		this.AP = AP;
		this.DP = DP;
		this.HP = HP;
		fullHP=HP;
		this.Motivated = Motivated;
		living = true;
	}
	Creature(int AP, int DP, int HP, boolean Motivated, World world){
		id=count;
		count++;
		this.AP = AP;
		this.DP = DP;
		this.HP = HP;
		fullHP=HP;
		this.Motivated = Motivated;
		living = true;
		this.world = world;
	}
	public void setWorld(World w) {
		this.world = w;
	}
	public int getAP() {
		return AP;
	}
	public int getDP() {
		return DP;
	}
	public int getHP() {
		synchronized(this){
		return HP;
		}
	}
	public int getFullHP() {
		return fullHP;
	}
	public void setHP(int HP) {
		synchronized(this){
		this.HP = HP;
		}
	}
	public boolean isMotivated() {
		return Motivated;
	}
	public boolean isLiving() {
		synchronized(this){
		return living;
		}
	}
	public int getAP(int radio) {
		if(Motivated)
			return AP * radio;
		else
			return AP;
	}
	public void setMotivated(boolean Motivated) {
		this.Motivated = Motivated;
	}
	public synchronized boolean setLivingStatus() {
		if(HP<=0)
			living = false;
		return living;
	}
	public void killThread() {
		living=false;
	}
	public synchronized void Attack(Creature attackedCreature) {
		Attack(attackedCreature,1);
	}
	public synchronized void Attack(Creature attackedCreature,int radio) {
		
		if(this.getClass().getSuperclass().getName()==attackedCreature.getClass().getSuperclass().getName())return;
		

		if(this.getAP(radio) > (attackedCreature.getHP() + attackedCreature.DP)){
			
			//System.err.println("***********************"+attackedCreature.getClass().getName()+" id:"+id+" die!");
			attackedCreature.setHP(0);
			attackedCreature.setLivingStatus();
			world.remove(attackedCreature.getX(), attackedCreature.getY());
			world.updateCount();
		}
		else if(this.getAP(radio) > attackedCreature.DP)
			attackedCreature.setHP(attackedCreature.getHP() - (this.getAP(radio) - attackedCreature.DP));
		/*System.err.println("this: "+this.getClass().getName()+" at("+this.x+","+this.y+")"+ "attack "+attackedCreature.getClass().getName()+" at("+attackedCreature.x+","+attackedCreature.y+")"
				+ "HP: "+attackedCreature.getHP());*/
	}

	public void run() 
	{
		

		while(isLiving()) 
		{
			Platform.runLater(()->
			{
			Creature c1 = world.getCreature(x-1, y);
			Creature c2 = world.getCreature(x, y-1);
			Creature c3 = world.getCreature(x+1, y);
			Creature c4 = world.getCreature(x, y+1);
			if(c1!=null) {
				Attack(c1);
			}
			if(c2!=null) {
				Attack(c2);
			}
			if(c3!=null) {
				Attack(c3);
			}
			if(c4!=null) {
				Attack(c4);
			}
			int movePos = world.findDirection(this, this.x, this.y);
			switch(movePos) {
			case 0:				
				if(world.isEmpty(x-1, y)) 
				{
					world.remove(x, y);
					x=x-1;
					//y=y;
					world.setMaps(this.x, this.y, this);
					break;
				}
				if(world.isEmpty(x, y-1)) {
					world.remove(x, y);
					y=y-1;
					world.setMaps(this.x, this.y, this);
					break;
				}
				
				
			case 1:
					if(world.isEmpty(x, y-1)) {
					world.remove(x, y);
					y=y-1;
					world.setMaps(this.x, this.y, this);
					break;
				}

				if(world.isEmpty(x-1, y)) {
					world.remove(x, y);
					x=x-1;
					world.setMaps(this.x, this.y, this);
				}
				break;
				
			case 2:
				if(world.isEmpty(x, y-1)) {
					world.remove(x, y);
					y=y-1;
					world.setMaps(this.x, this.y, this);
					break;
				}
				
				if(world.isEmpty(x+1, y)) {
					world.remove(x, y);
					x=x+1;
					//y=y;
					world.setMaps(this.x, this.y, this);
				}
				break;
			case 3:
				
				if(world.setMaps(x+1, y, this)) {
					world.remove(x, y);
					x=x+1;
					break;
				}

				if(world.setMaps(x, y-1, this)) {
					world.remove(x, y);
					y=y-1;
				}
				break;
			case 4:
				if(world.setMaps(x+1, y, this)) {
					world.remove(x, y);
					x=x+1;
					break;
				}

				if(world.setMaps(x, y+1, this)) {
					world.remove(x, y);
					y=y+1;
				}
				break;
			case 5:
				if(world.setMaps(this.x, this.y, this)) {
					world.remove(this.x, this.y);
					y=y+1;
					
					break;
				}

				if(world.setMaps(x+1, y, this)) {
					world.remove(x, y);
					x=x+1;
				}
				break;
			case 6:
				if(world.setMaps(x, y+1, this)) {
					world.remove(x, y);
					y=y+1;
					break;
				}

				if(world.setMaps(x-1, y, this)) {
					world.remove(x, y);
					x=x-1;
				}
				break;
			case 7:
				if(world.setMaps(x-1, y, this)) {
					world.remove(x, y);
					x=x-1;
					break;
				}

				if(world.setMaps(x, y-1, this)) {
					world.remove(x, y);
					y=y-1;
				}
				break;
				 
			}
		});
			try {
				Random r=new Random();
				Thread.sleep(r.nextInt(1000)+1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
