package World;
import Creature.*;
public class World {
	private static final int M=20;//��ͼ�ĳ�
	private static final int N=10;//��ͼ�Ŀ�
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
	public void updateCount() //����������ͻ�����
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

	public void remove(int x, int y) //��յ�ͼ����(x,y)
	{
		synchronized(World.class){
			maps[x][y]=null;
		}
	}
	public Creature getCreature(int x, int y) //�������(x,y)��creature
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
	public boolean isEmpty(int x, int y) //(x,y)���Ƿ�Ϊ��
	{
		if(x<0||x>M-1||y<0||y>N-1)
			return false;
		synchronized(World.class)//������,��ô����߳��ڸ������ж����ϵ��κβ��������ܽ���
		{
			return maps[x][y]==null;
		}
	}
	public boolean setMaps(int x, int y, Creature t) //����ͼ(x,y)λ��
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
	public String creatureType(int x, int y) //���(x,y)����������
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
	public boolean Containable(int x, int y, int SizeX, int SizeY) //�Ƿ��Խ��
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
