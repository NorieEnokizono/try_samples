<?php

$file = "filesize.php";

//SplFileInfo �N���X�̗��p
$f = new SplFileInfo($file);

$fs = sprintf("%u", $f->getSize());

echo "filesize : " . $fs . "\n";
echo "date : " . date("YmdHis", $f->getMTime()) . "\n";


//�e��֐��̗��p
//4GB �܂ł̃t�@�C���T�C�Y���擾�ł���悤�ɂ���
$fs = sprintf("%u", filesize($file));

echo "filesize : $fs \n";
echo "date : " . date("YmdHis", filemtime($file)) . "\n";

?>
