<?php

echo mb_internal_encoding() . "\n";

$db = mysql_connect("localhost", "testuser", "testuser");

if ($db) {
	echo mysql_client_encoding($db) . "\n";

	//�����R�[�h�̐ݒ�
	mysql_set_charset("utf8", $db);

	echo mysql_client_encoding($db) . "\n";

	$query = "insert into customer (id, name) values('4', '���[�U�[1')";

	$res = mysql_db_query("testuser", mb_convert_encoding($query, "UTF-8", "SJIS"), $db);

	echo "execute : $res";
	mysql_close($db);
}

?>
