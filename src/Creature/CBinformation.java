package Creature;

public enum CBinformation {
	//һ��Ҫ��ö�ٱ����Ķ�����ڵ�һ�У������ԷֺŽ�β��
	RED(1,"�ϴ�","��"), ORANGE(2,"�϶�","��"), YELLOW(3,"����","��"), GREEN(4,"����","��" ), CYAN(5,"����","��"), BLUE(6,"����","��"), PURPLE(7,"����","��");
	private int rank;
	private String name ;
	private String color ;
	//���캯������˽�л�����ʵ�ϣ�private�Ƕ���ģ�����ȫû�б�Ҫд����Ϊ��Ĭ�ϲ�ǿ����private�������Ҫд��Ҳֻ��дprivate��дpublic�ǲ���ͨ������ġ�
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
