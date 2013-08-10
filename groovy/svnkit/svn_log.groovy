@Grab('org.tmatesoft.svnkit:svnkit:1.7.8')
import org.tmatesoft.svn.core.*
import org.tmatesoft.svn.core.wc.*

def repoUrl = 'http://localhost/svn/sample/trunk'

def manager = SVNClientManager.newInstance()
def logClient = manager.logClient

// discoverChangedPaths �� true �ɂ���ƕύX�����t�@�C�������X�g�A�b�v�����
logClient.doLog(SVNURL.parseURIDecoded(repoUrl), args, SVNRevision.HEAD, SVNRevision.HEAD, new SVNRevision(1), true, true, 5, {
	println it
	println "-----------------"

} as ISVNLogEntryHandler)
