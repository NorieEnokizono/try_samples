
using System;
using System.Linq;
using System.Reactive.Linq;

class Sample
{
	static void Main(string[] args)
	{
		// �ʏ�� Linq �̏���
		var em = from x in Enumerable.Range(1, 10) where x % 3 == 0 select x + 10;
		foreach (var x in em)
		{
			Console.WriteLine(x);
		}

		Console.WriteLine("-----");

		// Rx �̏���1
		Observable.Range(1, 10).Subscribe(Console.WriteLine);

		Console.WriteLine("-----");

		// Rx �̏���2
		(from x in Observable.Range(1, 10) where x % 3 == 0 select x + 10).Subscribe(Console.WriteLine);

		Observable.Range(1, 10).Where(x => x % 3 == 0).Select(x => x + 10).Subscribe(Console.WriteLine);

		
	}
}
