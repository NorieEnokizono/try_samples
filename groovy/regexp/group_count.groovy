
def str = "at\t\t\t\t\t"

def m = str =~ /\t/

//count �� Groovy �̊g���� Java �ɂ� groupCount() �����Ȃ�
println "count : ${m.count}, group count: ${m.groupCount()}"

while(m.find()) {
	println "$m"
}
