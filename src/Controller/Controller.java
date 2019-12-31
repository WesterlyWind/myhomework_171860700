package Controller;

import java.util.*;
import Creature.*;
import World.*;
import Formation.*;

public class Controller 
{
	private World world;
	private CalabashBrothers cb;
	private Grandpa g;
	private Snake S;
	private Scorpion s;
	private Nobody[] nobodys;
	
	public Controller()
	{
		world=null;
		cb=null;
		g=null;
		S=null;
		s=null;
		nobodys=null;
	}
	public Controller(World w,CalabashBrothers cbs)
	{
		world=w;
		cb=cbs;

		g=new Grandpa();
		s=new Scorpion();
		S=new Snake();

		nobodys=new Nobody[20];
		for(int i=0;i<20;i++)
			nobodys[i]=new Nobody();
	}
	
	public boolean setPos(int x,int y,Creature t)
	{
		if(world.isEmpty(x, y))
		{
			world.setMaps(x, y, t);
			return true;
		}
		else
			return false;
	}
	//public boolean

	/*public void CBStandRandomly()//¿ØÖÆºùÂ«ÐÖµÜËæ»úÅÅÁÐ
	{
		cb.StandRandomly();
	}*/
	public World getWorld()
	{
		return world;
	}
	public void clearWorld()//Çë¿ÕµØÍ¼
	{
		world.initMap();
	}
	public void setMap()//ÉèÖÃµØÍ¼
	{
		
		clearWorld();
		//ºùÂ«ÍÞËæ»úÕ¾ÁÐ×î×ó±ß(0,1)-(0,7) Ò¯Ò¯(0,8)
		for(int i=1;i<=7;i++)
		{
			setPos(0,i,cb.getBrother(i-1));
			cb.getBrother(i-1).movetoPos(0, i);
		}
		setPos(0,8,g);
		g.movetoPos(0, 8);
		//Ñý¾«Ëæ»úÕ¾¶Ó 0º×Òí 1 ³¤Éß 2ºâ™Ä 3·½Ô² 4·æÊ¸
		int p=(int)(Math.random()*5);
		switch (p)
		{
		case 0:
			CraneWing formation0=new CraneWing();
			formation0.arrange(world, nobodys, this);
			break;
		case 1:
			LongSnake formation1=new LongSnake();
			formation1.arrange(world, nobodys, this);
			break;
		case 2:
			HengE formation2=new HengE();
			formation2.arrange(world, nobodys, this);
			break;
		case 3:
			FangYuan formation3=new FangYuan();
			formation3.arrange(world, nobodys, this);
			break;
		case 4:
			Array formation4=new Array();
			formation4.arrange(world, nobodys, this);
			break;
		}
		setPos(world.getLength()-1,8,s);
		setPos(world.getLength()-1,9,S);
		s.movetoPos(world.getLength()-1,8);
		S.movetoPos(world.getLength()-1,9);
		
		for(int i = 0; i < world.getLength();i++) {
			for(int j = 0; j < world.getWidth();j++) {
				if(!world.isEmpty(i, j)) {
					world.getCreature(i, j).setWorld(world);
				}
			}
		}
		
	}
}
