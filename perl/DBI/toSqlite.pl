
use DBI;

my $dbh = DBI->connect("dbi:SQLite:dbname=test.db", "", "");

#$dbh->do("CREATE TABLE test(id, name, point)");

my $insert = $dbh->prepare("INSERT INTO test VALUES(?, ?, ?)");
$insert->execute(10, "testdata", 100);
$insert->execute(20, "aaa", 1);

my $select = $dbh->prepare("SELECT * FROM test WHERE name like ?") || die $dbh->errstr;

#execute �Ƀp�����[�^���w�肷�鎖����
#$select->execute("%a%") || die $select->errstr;

#�v���[�X�z���_�̃C���f�b�N�X�� 1 ����
$select->bind_param(1, "%da%");
$select->execute() || die $select->errstr;

#���L�ł͌��������͎擾�ł��Ȃ�
print "count = " . $select->rows . "\n";

while (my($id, $name, $point) = $select->fetchrow_array) {
	print "$id, $name, $point\n";
}


#�ȉ��ł���
#while (my $rec = $select->fetch) {
#	print "$rec->[0], $rec->[1], $rec->[2]\n";
#}


#SQLite �̏ꍇ�͈ȉ��͕s�v
#$rc = $dbh->disconnect or warn $dbh->errstr;

