import groovy.text.GStringTemplateEngine
import groovyx.gpars.GParsExecutorsPool

def gte = new GStringTemplateEngine()

def strTemplate = gte.createTemplate('''
no : ${no}, name : ${name}
''')

//GStringTemplateEngine ���g����������쐬
def strList = [
	[no: "1", name: "test1"],
	[no: "aaa", name: "bbb"],
	[no: "123", name: "12345"],
	[no: "098", name: "�e�X�g�f�[�^"]
].collect {strTemplate.make(it).toString()}

//�����X���b�h�Ŏ��s
GParsExecutorsPool.withPool {
//GParsExecutorsPool.withPool(2) {
	def resList = strList.collectParallel {str ->
		println(Thread.currentThread())
		str + "-------"
	}

	resList.each {res ->
		println(res)
	}
}
