
use Jcode;

$data = "�e�X�g�f�[�^";

#$encoder = Jcode->new($data, "shiftjis");
#print $encoder->utf8;

print jcode($data, "shiftjis")->utf8;
