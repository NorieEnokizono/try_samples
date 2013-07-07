
var args = WScript.Arguments;

if (args.Count() < 2) {
	WScript.Echo("<machine name> <output file>");
	WScript.Quit();
}

var vb = WScript.CreateObject("VirtualBox.VirtualBox");
var fs = WScript.CreateObject("Scripting.FileSystemObject");

// ���z�}�V���̎擾
var mc = vb.findMachine(args.Item(0));

var filePath = args.Item(1);

var ap = vb.createAppliance();

var des = mc.Export(ap, fs.GetBaseName(filePath));

WScript.Echo("export start");

var prog = ap.write("ovf-2.0", false, filePath);

// ��������������܂őҋ@
prog.waitForCompletion(-1);

WScript.Echo("export end");
