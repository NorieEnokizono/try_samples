import spock.lang.*

class DataSpock extends Specification {

	def "check"() {
		when:
			def res = new Data().check("abc")
		then:
			res == "test:abc"
	}

	def "check many"() {
		expect:
			new Data().check(msg) == result
		where:
			msg   | result
			"aaa" | "test:aaa"
			""    | ""
			null  | null
	}

	def "check many when-then"() {
		when:
			def res = new Data().check(msg)
		then:
			res == result
		where:
			msg   | result
			"aaa" | "test:aaa"
			""    | ""
			null  | null
	}

}