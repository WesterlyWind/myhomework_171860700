package Creature;

public class CalabashBrother extends Good{
	private CBinformation info;
	CalabashBrother()
	{
		super(6,3,20);
		x = -1;
		y = -1;
		info = null;
		
	}
	CalabashBrother(CBinformation IF){
		super(6,3,20);
		x = -1;
		y = -1;
		info = IF;
	}
	CalabashBrother(int x, int y){
		super(6,3,20);
		this.x = x;
		this.y = y;
		info = null;
	}
	CalabashBrother(int x, int y, CBinformation IF){
		super(6,3,20);
		this.x = x;
		this.y = y;
		info = IF;
	}
	public String getName() {
		return info.getName();
	}
	public String getColor() {
		return info.getColor();
	}
	public CBinformation getInfo() 
	{
		return info;
	}
	public void setInfo(int index) //将当前葫芦娃的颜色等信息设置为第index个颜色
	{
		info = CBinformation.values()[index];
	}
	public int compareTo(CalabashBrother brother) //相等为0
	{
		// TODO Auto-generated method stub
		return this.info.compareTo(brother.info);
	}
}
