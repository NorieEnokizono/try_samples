
print index("�{", "{") . "\n";

open(F, 'data.txt');

while (<F>) {
	print index($_, "{") . "\n";
}

close(F);

print "include c\n" if ("�c" =~ /c/);


#--------------- use encoding ---------
use encoding "shiftjis";

print index("�{", "{") . "\n";

print "include c\n" if ("�c" =~ /c/);

open(F, 'data.txt');

while (<F>) {
	print index($_, "{") . "\n";
}

close(F);
