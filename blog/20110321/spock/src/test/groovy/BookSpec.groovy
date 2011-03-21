package fits.sample

import spock.lang.*

class InitBookSpec extends Specification {
	def b = new Book()

	def "comments �� null �ł͂Ȃ�"() {
		expect:
			b.comments != null
	}

	def "comments �͋�"() {
		expect:
			b.comments.size == 0
	}
}

class AddCommentSpec extends Specification {
	def b = new Book()

	def "Comment ��ǉ�"() {
		when:
			b.comments.add(new Comment())
		then:
			b.comments.size == 1
	}
}
