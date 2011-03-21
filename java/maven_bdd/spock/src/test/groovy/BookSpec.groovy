package fits.sample

import spock.lang.*

class InitBookSpec extends Specification {
	def b = new Book()

	def "title �� null"() {
		expect:
			b.title == null
	}

	def "comments �� null �ł͂Ȃ�"() {
		expect:
			b.comments != null
	}

	def "comments �͋�"() {
		expect:
			b.comments.size == 0
	}
}

class SetTitleSpec extends Specification {
	def b = new Book()

	def "title ���w��"() {
		when:
			b.title = "test"

		then:
			b.title == "test"
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
