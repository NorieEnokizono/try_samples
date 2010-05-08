using System;
using DotNetGuru.AspectDNG.Joinpoints;

public class LogAspect
{
	//Calc �N���X�̑S���\�b�h�̌Ăяo���ʒu�ɃE�B�[�r���O
	[AroundCall("* Calc::*(*)")]
	public static object Intercept(OperationJoinPoint jp)
	{
		Console.WriteLine("--- before call : {0}", jp);

		return jp.Proceed();
	}

	//Calc �N���X�̑S�v���p�e�B�擾�̎��s�ʒu�ɃE�B�[�r���O
	[AroundBody("* Calc::get_*()")]
	public static object InterceptPorperty(MethodJoinPoint jp)
	{
		Console.WriteLine("--- before property get : {0}", jp);

		return jp.Proceed();
	}
}