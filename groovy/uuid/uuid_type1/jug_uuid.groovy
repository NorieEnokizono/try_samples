@Grapes([
	@Grab('com.fasterxml.uuid:java-uuid-generator:3.1.3'),
	@GrabExclude('log4j#log4j;1.2.13')
])
import com.fasterxml.uuid.Generators

// �^�C�v1 �� UUID �𐶐����� Generator
def generator = Generators.timeBasedGenerator()

(1..5).each {
	// �^�C�v1 �� UUID �𐶐�
	println generator.generate()
}
