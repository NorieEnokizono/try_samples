/**
* "svn log �t�@�C����" �Ɠ����̏��������s����T���v��
*
* �i���j
*    �J�����g�f�B���N�g���̃t�@�C�����w�肷��ꍇ��
*    "./�t�@�C����" �Ǝw�肷��_�ɒ���
*    �i�������Ȃ��ƃ��[�L���O�R�s�[�ł͖����Ƃ����G���[���ł�j
*/

import org.tmatesoft.svn.core.ISVNLogEntryHandler
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory
import org.tmatesoft.svn.core.wc.SVNClientManager
import org.tmatesoft.svn.core.wc.SVNRevision

//file:// �̃��|�W�g�����g�p����ꍇ�ɕK�v
FSRepositoryFactory.setup()

def manager = SVNClientManager.newInstance()
def logClient = manager.logClient

def logHandler = {logEntry -> println logEntry} as ISVNLogEntryHandler

logClient.doLog([new File(args[0])] as File[], new SVNRevision(0), SVNRevision.HEAD, true, false, 0, logHandler)

