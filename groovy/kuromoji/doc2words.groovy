@Grab('com.atilika.kuromoji:kuromoji-ipadic:0.9.0')
import com.atilika.kuromoji.ipadic.Token
import com.atilika.kuromoji.ipadic.Tokenizer
import java.text.Normalizer
import groovy.transform.Immutable

@Immutable
class SpeechLevel {
	String level1
	String level2
}

def target = [
	new SpeechLevel('����', '���'),
	new SpeechLevel('����', '�ŗL����'),
	new SpeechLevel('����', '�����\'),
	new SpeechLevel('����', '�T�ϐڑ�'),
	new SpeechLevel('����', '�`�e�����ꊲ'),
	new SpeechLevel('����', '����'),
	new SpeechLevel('�`�e��', '����')
]

def stopWords = '* ���� ���� ���� �Ȃ� �ł��� ���� ����'.split(' ')

def tokenizer = new Tokenizer()

def doc = Normalizer.normalize(
	new File(args[0]).getText('UTF-8'),
	Normalizer.Form.NFKC
)

def res = tokenizer.tokenize(doc).findAll {
	target.contains(
		new SpeechLevel(it.partOfSpeechLevel1, it.partOfSpeechLevel2)
	)
}.findAll {
	!stopWords.contains(it.baseForm)
}.collect {
	it.baseForm
}.join(' ')

println res
