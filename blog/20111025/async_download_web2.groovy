import groovyx.gpars.actor.*

def dir = args[0]

System.in.readLines() collect {u ->
	def download  = Actors.actor {
		def url

		//��O�������̏���
		delegate.metaClass.onException = {
			println "failed: ${url}, ${it}"
		}

		react {urlString ->
			//URL�ڑ� (2)
			url = new URL(urlString)
			//Actor �ւ̃��b�Z�[�W���M (3)
			send url.openStream()

			react {stream ->
				//�_�E�����[�h���� (4)
				def file = new File(dir, new File(url.file).name)
				file.bytes = stream.bytes

				println "downloaded: ${url} => ${file}"
			}
		}
	}

	//Actor �ւ̃��b�Z�[�W���M (1)
	download.send u
	download
} each {
	it.join()
}

