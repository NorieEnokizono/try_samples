
use Storable qw(store retrieve);
use Data::Dumper;

$list = {};

$list->{'ABC'} = ['�e�X�g1', '�\1', '�`�F�b�N'];
$list->{'123'} = ['�f�[�^'];
$list->{'test'} = [];

store($list, 'data.dat');

$loadList = retrieve('data.dat');

print Data::Dumper->Dumper($loadList);

print "\n---" . ($loadList->{'ABC'}[1]) . "---\n";


while(($key, @value) = each %$loadList) {
	print "==== $key = @value \n";
}
