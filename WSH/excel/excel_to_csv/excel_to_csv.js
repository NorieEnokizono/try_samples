
var args = WScript.Arguments;

if (args.length < 1) {
	WScript.Echo("<excel file>");
	WScript.Quit();
}

var fs = WScript.createObject("Scripting.FileSystemObject");
var app = WScript.CreateObject("Excel.Application");

// �m�F���b�Z�[�W�̔�\���ݒ�
app.DisplayAlerts = false;

var excelFile = args(0)
var csvFile = fs.GetBaseName(excelFile) + ".csv";

try {
	var book = app.Workbooks.Open(fs.GetAbsolutePathName(excelFile));

	// 1�V�[�g�ڂ� CSV �t�@�C���֕ۑ�
	book.Sheets(1).SaveAs(fs.GetAbsolutePathName(csvFile), 6);

	book.Close(false);
} catch(e) {
}

app.Quit();
app = null;
