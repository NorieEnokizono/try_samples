<?php

$doc = simplexml_load_file($argv[1]);

//�v�f�̒ǉ�
$d = $doc->addChild("data");
$d["id"] = "3";
$d->addChild("details", "added");

//�����̒ǉ�
$doc->data[1]["type"] = "node";

//�v�f�̍폜
unset($doc->data[0]);

//�v�f�̒u���iDOMDocument �� API ���g�p�j
$dom = dom_import_simplexml($doc);
$target = $dom->getElementsByTagName("details")->item(0);
$target->parentNode->replaceChild(new DOMElement("text", "update test"), $target);
$doc = simplexml_import_dom($dom);

//�v�f�̒l��ύX
$doc->data[0]->details[0] = "after";

//�����̕ύX
$doc->data[0]["ext"] = "updated";

echo $doc->asXML();

