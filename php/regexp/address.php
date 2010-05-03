<?php

function parseAddress($listString) {
	$list = split(";", $listString);

	$res = array();

	$pattern = '(.*)<(.*)>';
//	$pattern = "([^<>]*)<([^<>]+)>";

	foreach ($list as $it) {
		$mail = trim($it);
		$name = "";

		if ($mail) {
			if (ereg($pattern, $it, $m)) {
				$name = trim($m[1]);
				$mail = trim($m[2]);
			}

			if (strpos($mail, '@') !== false) {
				array_push($res, array("name" => $name, "mail" => $mail));
			}
		}
	}
	return $res;
}

function dumpList($list) {
	foreach($list as $i) {
		echo "name : ${i['name']}, mail : ${i['mail']} \n";
	}
}

dumpList(parseAddress("system@abc.com"));
dumpList(parseAddress("�Ǘ��� <system@test.co.jp>"));
dumpList(parseAddress(" <system12@test.co.jp>"));
dumpList(parseAddress("abc@aaa<1>"));

echo "\n";

dumpList(parseAddress("�Ǘ���1 <system@test.co.jp>; �Ǘ���2<system@aaaa.bbb.ccc >; test@bb.cc; aa<b>;<u1@aaa.co.jp>;d<e>123;-1;false;  ><;abc@aaa<1>"));


?>