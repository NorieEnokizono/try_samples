
use encoding "shiftjis";

$_ = "{�c ���}�U}";

my @temp = /\{([^}]*)\}/g;

print $temp[0];

