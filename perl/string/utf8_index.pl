
use Encode qw(from_to);

print index("AB�c�{", "c") . "\n";

my $dataSrc = "AB�c�{";
my $dataTrg = "B�c";

from_to($dataSrc, "shiftjis", "utf8");
from_to($dataTrg, "shiftjis", "utf8");

#$dataSrc = uc($dataSrc);
#$dataTrg = uc($dataTrg);

print index($dataSrc, $dataTrg) . "\n";

print ($dataSrc =~ /$dataTrg/) . "\n";
