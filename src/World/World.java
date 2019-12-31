package World;
import Creature.*;
public class World {
	private static final int M=20;//地图的长
	private static final int N=10;//地图的宽
	private int goodCount;
	private int badCount;
	private Map<Creature> maps[][];
	public World()
	{
		maps = new Map[M][N];
		initMap();
		goodCount=-1;
		badCount=-1;
	}
	//public World()(int m, int n){
	public void initMap() {
		for(int i = 0; i < M;i++) {
			for(int j = 0; j < N;j++) {
				maps[i][j]=null;
			}
		}
	}
	public int getLength()
	{
		return M;
	}
	public int getWidth()
	{
		return N;
	}
	public void initializeCount(int goodboyCount, int badboyCount) 
	{
		this.goodCount = goodboyCount;
		this.badCount = badboyCount;
	}
	public void updateCount() //计算好人数和坏人数
	{
		goodCount = 0;
		badCount = 0;
		for(int i = 0; i < M; i++) 
		{
			for(int j = 0; j < N;j++) 
			{
				synchronized(this)
				{
					if(maps[i][j]==null)
						continue;
					if(maps[i][j].getClass().getSuperclass().getName()==Good.class.getName()) {
						goodCount++;
					}
					else {
						badCount++;
					}
				}
			}
		}
	}

	public void remove(int x, int y) //清空地图坐标(x,y)
	{
		synchronized(World.class){
			maps[x][y]=null;
		}
	}
	public Creature getCreature(int x, int y) //获得坐标(x,y)处creature
	{
		synchronized(this) {
			if(x<0||x>M-1||y<0||y>N-1)
				return null;
			else if(maps[x][y]==null)
				return null;
			else 
				return maps[x][y].getCreature();
		}
	}
	public boolean isEmpty(int x, int y) //(x,y)处是否为空
	{
		if(x<0||x>M-1||y<0||y>N-1)
			return false;
		synchronized(World.class)//对象锁,那么别的线程在该类所有对象上的任何操作都不能进行
		{
			return maps[x][y]==null;
		}
	}
	public boolean setMaps(int x, int y, Creature t) //填充地图(x,y)位置
	{
		synchronized(World.class)
		{
			if(!isEmpty(x,y))
				return false;
			else 
			{
				maps[x][y]=new Map<Creature>(t);
			
				return true;
			}
		}
	}
	public String creatureType(int x, int y) //获得(x,y)处生物类型
	{
		synchronized(this)
		{
			//System.out.println(maps[x][y].getCreature().getClass().getName());
			return maps[x][y].getCreature().getClass().getName();
		}
	}
	
	public int findDirection(Creature c,int x, int y) {
		for(int i = 1;i<=28;i++) 
		{
			for(int j = (i+1)/2; j <= i;j++) 
			{
				Creature c1 = getCreature(x-j,y-i+j);
				if((c1!=null)&&(c1.getClass().getSuperclass().getName()!=c.getClass().getSuperclass().getName()))return 0;
				
				c1 = getCreature(x-i+j,y-j);				
				if((c1!=null)&&(c1.getClass().getSuperclass().getName()!=c.getClass().getSuperclass().getName()))return 1;
				
				Creature c2 = getCreature(x+i-j,y-j);
				if((c2!=null)&&(c2.getClass().getSuperclass().getName()!=c.getClass().getSuperclass().getName()))return 2;
				
				Creature c3 = getCreature(x+j,y-i+j);
				if((c3!=null)&&(c3.getClass().getSuperclass().getName()!=c.getClass().getSuperclass().getName()))return 3;
				
				Creature c4 = getCreature(x+j,y+i-j);
				if((c4!=null)&&(c4.getClass().getSuperclass().getName()!=c.getClass().getSuperclass().getName()))return 4;
				
				c3 = getCreature(x+i-j,y+j);
				if((c3!=null)&&(c3.getClass().getSuperclass().getName()!=c.getClass().getSuperclass().getName()))return 5;
				
				c4 = getCreature(x-i+j,y+j);
				if((c4!=null)&&(c4.getClass().getSuperclass().getName()!=c.getClass().getSuperclass().getName()))return 6;
				
				
				
				c2 = getCreature(x-j,y+i-j);
				if((c2!=null)&&(c2.getClass().getSuperclass().getName()!=c.getClass().getSuperclass().getName()))return 7;
				
				
				
				
			}
		}
		return -1;
	}
	public boolean Containable(int x, int y, int SizeX, int SizeY) //是否会越界
	{
		if(((x + SizeX) <= M) && ((y + SizeY) <= N) && ((x + SizeX) >=0 ) && ((y + SizeY) >=0))
			return true;
		else 
			return false;
	}
	public String CBName(int x, int y) 
	{
		synchronized(this)
		{
			return ((CalabashBrother)maps[x][y].getCreature()).getName();
		}
	}
	/*public Creature getCreature(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
