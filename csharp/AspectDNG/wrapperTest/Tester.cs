
using System;

public class Tester
{
	public static void Main(string[] args)
	{
		IData data = new DataWrapper();

		data.Print();

		int min, max;

		//���̃��\�b�h�̓E�F�[�r���O�̑ΏۂɂȂ�Ȃ�
		data.CheckAudio("testbstr", out min, out max);

		Console.WriteLine("min={0}, max={1}", min, max);

		Console.WriteLine(data.Test("checkdata", 500));
	}
}
