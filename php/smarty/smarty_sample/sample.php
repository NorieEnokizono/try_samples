<?php
	require_once('Smarty/Smarty.class.php');

	$smarty = new Smarty;
	/* 
	 * �J�����g�� plugins �f�B���N�g������
	 * Smarty �v���O�C�����Q�Ƃ��邽�߂̐ݒ聦
	 *
	 * �� �f�t�H���g�� lib/Smarty/plugins �ɂȂ�
	 *
	 * �Ȃ��A�ȉ��̂悤�ɐݒ肷���
	 * $smarty->plugins_dir[] = 'plugins';
	 * 
	 * indirect modification of overloaded property has no effect ��
	 * �o�͂����̂Œ���
	 */
	$smarty->plugins_dir = array('plugins');

	$smarty->assign('name', 'test data');

	$smarty->display('sample.tpl');

?>