using System;

public class Data
{
	public string Id {get; set;}
	public string Name {get; set;}
	public int Point {private get; set;}

	public void Print()
	{
		Console.WriteLine("{0}, {1}, {2}", Id, Name, Point);
	}
}

public class Test
{
	public static void Main(string[] args)
	{
		//�R���X�g���N�^�Ɉ������Ƃ�Ȃ��ꍇ�� () ���ȗ���
		Data d = new Data {Id = "id:1", Name = "test1", Point = 15};
		d.Print();

		//private �̂��� Point �� get �ł��Ȃ�
		//Console.WriteLine(d.Point);

		var a = new {d.Id, d.Name};

		Console.WriteLine("anonymous: {0}, {1}", a.Id, a.Name);
	}

}