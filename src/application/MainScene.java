package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.scene.paint.Color;
import World.*;
import Creature.*;
import Controller.*;
import javafx.application.Platform;
//import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//import javafx.scene.input.KeyEvent;

public class MainScene extends Canvas
{
	private int goodNum=8;
	private int badNum=-1;
	private boolean begin =false;
	private World world;
	private Controller controller;
	private GraphicsContext gc;
	private String fileName;
	private boolean battleOver;
	int mode;
	private FileWriter writer;
	private  BufferedReader reader;
	ExecutorService exec = Executors.newCachedThreadPool();//线程池
	static Image BG=null;	
	static Image CB1 = null;
	static Image CB2 = null;
	static Image CB3 = null;
	static Image CB4 = null;
	static Image CB5 = null;
	static Image CB6 = null;
	static Image CB7 = null;
	static Image GP = null;
	static Image NB = null;
	static Image SC = null;
	static Image SN = null;
	static Image Start=null;
	static int biasY = 60;
	/*static 
	{
		try {
			BG = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\background.jpg").toURI().toURL().toString());
			CB1 = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\1.jpg").toURI().toURL().toString());
			CB2 = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\2.jpg").toURI().toURL().toString());
			CB3 = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\3.jpg").toURI().toURL().toString());
			CB4 = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\4.jpg").toURI().toURL().toString());
			CB5 = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\5.jpg").toURI().toURL().toString());
			CB6 = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\6.jpg").toURI().toURL().toString());
			CB7 = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\7.jpg").toURI().toURL().toString());
			GP = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\Grandpa.jpg").toURI().toURL().toString());
			NB = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\Nobody.jpg").toURI().toURL().toString());
			SC = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\Scorpion.jpg").toURI().toURL().toString());
			SN = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\Snake.jpg").toURI().toURL().toString());
			Start = new Image(new File("D:\\Java程序设计\\新建文件夹\\CalabashBrother\\pic\\start.jpg").toURI().toURL().toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	static 
	{
		try {
			BG = new Image(new File("pic/background.jpg").toURI().toURL().toString());
			CB1 = new Image(new File("pic/1.jpg").toURI().toURL().toString());
			CB2 = new Image(new File("pic/2.jpg").toURI().toURL().toString());
			CB3 = new Image(new File("pic/3.jpg").toURI().toURL().toString());
			CB4 = new Image(new File("pic/4.jpg").toURI().toURL().toString());
			CB5 = new Image(new File("pic/5.jpg").toURI().toURL().toString());
			CB6 = new Image(new File("pic/6.jpg").toURI().toURL().toString());
			CB7 = new Image(new File("pic/7.jpg").toURI().toURL().toString());
			GP = new Image(new File("pic/Grandpa.jpg").toURI().toURL().toString());
			NB = new Image(new File("pic/Nobody.jpg").toURI().toURL().toString());
			SC = new Image(new File("pic/Scorpion.jpg").toURI().toURL().toString());
			SN = new Image(new File("pic/Snake.jpg").toURI().toURL().toString());
			Start = new Image(new File("pic/start.jpg").toURI().toURL().toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Thread thread = new Thread(new Runnable() {
		//主场景线程的任务是根据当前模式以及状态将战斗过程保存或者回放
		@Override
		public void run()
		{
			while(true) 
			{
				//System.err.println("running "+mode);
				//draw();
				//world.BFOutput();
				Platform.runLater(()->{
					if(mode==1) {
						if((goodNum==0)||(badNum==0)) 
						{
							//System.err.println("goodNum:"+goodNum+" badNum:"+badNum);
						}
						else 
						{
							save();
							draw();
						}
					}
					else if(mode==0)
						replay();

				});
				try {
					Thread.sleep(50);
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	public MainScene() 
	{
		super(1000, 500+biasY);
		mode = -1;
		battleOver = true;
		world=new World();
		controller = new Controller(world,new CalabashBrothers());
		gc = this.getGraphicsContext2D();
		gc.drawImage(BG, 0, 0+biasY,1000,500);

		
		gc.setLineWidth(2);
		
		for(int i = 0; i <= world.getLength(); i++)
			gc.strokeLine(50*i, 0+biasY, 50*i, 500+biasY);
		for(int i = 0; i <= world.getWidth();i++)
			gc.strokeLine(0, 50*i+biasY, 1000, 50*i+biasY);
		thread.start();
	}
	public void flashBegin()//点击开始后开始战斗，就是将生物放入线程池
	{
		if((!begin)&&mode==1) 
		{
			creatureThreadRun();
		}
	}
	public void drawBackground()
	{//context.drawImage(img,sx,sy,swidth,sheight,x,y,width,height)
		gc.drawImage(BG, 0, 0+biasY,1000,500);
		for(int i = 0; i <= world.getLength(); i++)//画竖线
			gc.strokeLine(50*i, 0+biasY, 50*i, 500+biasY);
		for(int i = 0; i <= world.getWidth();i++)//画横线
			gc.strokeLine(0, 50*i+biasY, 1000, 50*i+biasY);
	
	}
	public void drawWelcome()//初始界面
	{//context.drawImage(img,sx,sy,swidth,sheight,x,y,width,height)
		//gc.clearRect(0, 0, 1000, 500+biasY);
		gc.drawImage(Start, 250, 0+biasY,500,500);
		for(int i = 0; i <= world.getLength(); i++)//画竖线
			gc.strokeLine(50*i, 0+biasY, 50*i, 500+biasY);
		for(int i = 0; i <= world.getWidth();i++)//画横线
			gc.strokeLine(0, 50*i+biasY, 1000, 50*i+biasY);
	
	}
	public void newWar()
	{
		battleOver=false;
		world=new World();
		controller = new Controller(world,new CalabashBrothers());
		controller.setMap();//设置地图
		draw();//画出当前世界
	}
	public void draw()
	{
		int newgoodNum = 0;
		int newbadNum = 0;
		gc.clearRect(0, 0, 1000, 500+biasY);		
		//画战斗时背景
		gc.drawImage(BG, 0, 0+biasY,1000,500);
		//画格子
		//gc.setStroke(Color.BLACK);
		for(int i = 0; i <= world.getLength(); i++)
			gc.strokeLine(50*i, 0+biasY, 50*i, 500+biasY);
		for(int i = 0; i <= world.getWidth();i++)
			gc.strokeLine(0, 50*i+biasY, 1000, 50*i+biasY);
		//画人物
		for(int i = 0; i < world.getLength();i++) 
		{
			for(int j = 0; j < world.getWidth();j++) 
			{
				if(world.isEmpty(i, j)||(!world.getCreature(i, j).isLiving()))
					continue;
				if(((double)world.getCreature(i, j).getHP())/((double)world.getCreature(i, j).getFullHP())>0.7)
					gc.setStroke(Color.LIGHTGREEN);
				else if(((double)world.getCreature(i, j).getHP())/((double)world.getCreature(i, j).getFullHP())>0.4)
					gc.setStroke(Color.YELLOW);
				else
					gc.setStroke(Color.RED);
				gc.setLineWidth(8);
				if((50*j+5)<=50*j-3+((double)world.getCreature(i, j).getHP())/((double)world.getCreature(i, j).getFullHP())*48) 
				{
					gc.strokeLine(50*i+5, 50*j+biasY, 50*i-3+((double)world.getCreature(i, j).getHP())/((double)world.getCreature(i, j).getFullHP())*48, 50*j+biasY);
				}
				else
					gc.strokeLine(50*i+5, 50*j+biasY, 50*i+5+((double)world.getCreature(i, j).getHP())/((double)world.getCreature(i, j).getFullHP())*48, 50*j+biasY);

				gc.setStroke(Color.BLACK);
				gc.setLineWidth(2);
				switch(world.creatureType(i, j)) {
				case "Creature.CalabashBrother":
					newgoodNum++;
					switch(world.CBName(i, j)) {
					case "老大":
						gc.drawImage(CB1, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "老二":
						gc.drawImage(CB2, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "老三":
						gc.drawImage(CB3, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "老四":
						gc.drawImage(CB4, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "老五":
						gc.drawImage(CB5, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "老六":
						gc.drawImage(CB6, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "老七":
						gc.drawImage(CB7, 50*i+1, 50*j+biasY+1,48,48);
						break;
					}					
					break;
				case "Creature.Grandpa":
					newgoodNum++;
					gc.drawImage(GP, 50*i+1, 50*j+biasY+1,48,48);
					break;
				case "Creature.Nobody":
					if(goodNum==0)
						world.getCreature(i, j).killThread();;
					newbadNum++;
					gc.drawImage(NB, 50*i+1, 50*j+biasY+2,48,48);
					break;
				case "Creature.Snake":
					newbadNum++;
					gc.drawImage(SN, 50*i+1, 50*j+biasY+1,48,48);
					break;
				case "Creature.Scorpion":
					newbadNum++;
					gc.drawImage(SC, 50*i+1, 50*j+biasY+1,48,48);
					break;
				}
			}
		}
		goodNum = newgoodNum;
		badNum = newbadNum;
		if(goodNum==0||badNum==0) {
			for(int i = 0; i < world.getLength();i++) {
				for(int j = 0; j < world.getWidth();j++) {
					if(world.isEmpty(i, j)||(!world.getCreature(i, j).isLiving()))continue;
					else
						world.getCreature(i, j).killThread();
				}
			}
			setMode(-1);
			battleOver=true;
		}
			
	}
	public boolean isBattleOver()
	{
		return battleOver;		
	}
	public void creatureThreadRun()//将Creature线程加入线程池
	{
		begin = true;
		battleOver=false;
		World w= controller.getWorld();
		//画背景
		gc.clearRect(0, 0, 1000, 500+biasY);
		drawBackground();
		for(int i = 0; i < world.getLength();i++) {
			for(int j = 0;j<world.getWidth();j++) {
				if(!world.isEmpty(i, j)) {
					Creature createExec = world.getCreature(i, j);
					exec.execute(createExec);//加入线程池
				}
			}
		}
	}
	public void setMode(int mode)
	{
		this.mode=mode;
	}
	public void openFile(String fileName)
	{
		this.fileName=fileName;
		try 
		{
			reader=new BufferedReader(new FileReader(new File(fileName)));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();//若无法打开文件，则给出错误信息
		}
		
	}
	public void replay()
	{
		//gc.clearRect(0, 0, 1000, 500+biasY);
		drawBackground();
		for(int i = 0;i<world.getLength();i++)
		{
			String line=null;
			try 
			{
				if((line = reader.readLine())==null)return;
			
				String[]creatureLine = line.split(" ");
			
				
				for(int j = 0; j < world.getWidth(); j++) {
					switch(creatureLine[j]) {
					case "R":
						gc.drawImage(CB1, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "O":
						gc.drawImage(CB2, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "Y":
						gc.drawImage(CB3, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "G":
						gc.drawImage(CB4, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "C":
						gc.drawImage(CB5, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "B":
						gc.drawImage(CB6, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "P":
						gc.drawImage(CB7, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "g":
						gc.drawImage(GP, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "S":
						gc.drawImage(SC, 50*i+1, 50*j+biasY+1,48,48);
						break;
					case "N":
						gc.drawImage(NB, 50*i+1, 50*j+biasY+2,48,48);
						break;
					case "s":
						gc.drawImage(SN, 50*i+1, 50*j+biasY+1,48,48);
						break;
					}
				}
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	public void save()
	{
		try {
			//打开写文件器，第二个参数表示为append
			writer = new FileWriter(fileName,true);
			for(int i = 0; i < world.getLength();i++) {
				for(int j = 0; j < world.getWidth();j++) {
					if(world.isEmpty(i, j))writer.write("* ");
					else {
						switch(world.getCreature(i, j).getClass().getName()) {
						
						case "Creature.CalabashBrother":
							switch(((CalabashBrother)world.getCreature(i, j)).getInfo()) {
							case RED:
								writer.write("R ");
								break;
							case ORANGE:
								writer.write("O ");
								break;
							case YELLOW:
								writer.write("Y ");
								break;
							case GREEN:
								writer.write("G ");
								break;
							case CYAN:
								writer.write("C ");
								break;
							case BLUE:
								writer.write("B ");
								break;
							case PURPLE:
								writer.write("P ");
							}
							break;
						case "Creature.Grandpa":
							writer.write("g ");
							break;
						case "Creature.Scorpion":
							writer.write("S ");
							break;
						case "Creature.Nobody":
							writer.write("N ");
							break;
						case "Creature.Snake":
							writer.write("s ");
							break;
						}
					}
				}
				writer.write("\r\n");
			}
			//writer.write("\r\n");
			writer.close();
		}catch (IOException e) {  
            e.printStackTrace();  
        }  

	}
	
}
