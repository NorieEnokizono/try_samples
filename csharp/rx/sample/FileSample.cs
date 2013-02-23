
using System;
using System.IO;
using System.Linq;
using System.Reactive.Disposables;
using System.Reactive.Linq;

class FileSample
{
	static void Main(string[] args)
	{
		FromFile(args[0]).Subscribe(Console.WriteLine);

		Console.WriteLine("-----");

		FromFile(args[0]).Skip(1).Take(2).Select(x => "#" + x).Subscribe(Console.WriteLine);

		Console.WriteLine("-----");

		// ���L�� Mono 3.0.3 �ł͓��삵�Ȃ� �i.NET Framework 4.5 �ł͓��삷��j
		FromAsyncFile(args[0]).Skip(1).Take(2).Select(x => "#" + x).Subscribe(Console.WriteLine);
	}

	// ����������
	private static IObservable<string> FromFile(string fileName)
	{
		return Observable.Create<string>(observer => {
			try
			{
				using(var reader = File.OpenText(fileName))
				{
					while (!reader.EndOfStream)
					{
						observer.OnNext(reader.ReadLine());
					}

					observer.OnCompleted();
					Console.WriteLine("*** close");
				}
			}
			catch (Exception error) {
				observer.OnError(error);
			}
			return Disposable.Empty;
		});
	}

	// �񓯊������� �iMono 3.0.3 �ł͐���ɓ��삵�Ȃ��j
	private static IObservable<string> FromAsyncFile(string fileName)
	{
		return Observable.Create<string>(async observer => {
			try
			{
				using(var reader = File.OpenText(fileName))
				{
					while (!reader.EndOfStream)
					{
						var line = await reader.ReadLineAsync();
						observer.OnNext(line);
					}

					observer.OnCompleted();
					Console.WriteLine("*** close");
				}
			}
			catch (Exception error) {
				observer.OnError(error);
			}
			return Disposable.Empty;
		});
	}
}
