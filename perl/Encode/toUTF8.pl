
#use Encode;
use Encode qw(from_to);

$data = "�e�X�g�f�[�^";

#Encode::from_to($data, "shiftjis", "utf8");
from_to($data, "shiftjis", "utf8");

print $data;
