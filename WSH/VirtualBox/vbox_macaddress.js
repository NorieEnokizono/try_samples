
var args = WScript.Arguments;

if (args.Count() < 1) {
	WScript.Echo("<machine name>");
	WScript.Quit();
}

var vb = WScript.CreateObject("VirtualBox.VirtualBox");

// ���z�}�V���̎擾
var mc = vb.findMachine(args.Item(0));

// �ŏ��̃l�b�g���[�N�A�_�v�^�擾
var na = mc.getNetworkAdapter(0);

// MAC�A�h���X�̏o��
WScript.Echo(na.MACAddress);
