<?php

$xml = "<data><![CDATA[ test & <data> ]]></data>";

$d = simplexml_load_string($xml);
//CDATA �Z�N�V�����̓��e���p�[�X����Ȃ�
var_dump($d);

$d = simplexml_load_string($xml, 'SimpleXMLElement', LIBXML_NOCDATA);
//CDATA �Z�N�V�����̓��e���p�[�X�����
var_dump($d);



/* �o�͌���

object(SimpleXMLElement)#1 (0) {
}
object(SimpleXMLElement)#2 (1) {
  [0]=>
  string(15) " test & <data> "
}

*/

