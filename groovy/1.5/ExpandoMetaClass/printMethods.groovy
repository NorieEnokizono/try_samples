
class Data {
	String name = "�Ă���"
}

println "--- Methods ---"
println Data.metaClass.methods


println "--- Properties ---"

println Data.metaClass.properties

Data.metaClass.properties.each {
	println it.name
}
