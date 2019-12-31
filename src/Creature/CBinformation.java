package Creature;

public enum CBinformation {
	//一定要把枚举变量的定义放在第一行，并且以分号结尾。
	RED(1,"老大","赤"), ORANGE(2,"老二","橙"), YELLOW(3,"老三","黄"), GREEN(4,"老四","绿" ), CYAN(5,"老五","青"), BLUE(6,"老六","蓝"), PURPLE(7,"老七","紫");
	private int rank;
	private String name ;
	private String color ;
	//构造函数必须私有化。事实上，private是多余的，你完全没有必要写，因为它默认并强制是private，如果你要写，也只能写private，写public是不能通过编译的。
	private CBinformation(int rank,String name,String color)//
	{
		this.rank=rank;
		this.name=name;
		this.color=color;
	}
	public int getRank()
	{
		return rank;
	}
	public String getName() {
		return name;
	}
	public String getColor() {
		return color;
	}
}
