import java.io.*

def printMetric(val) {
    println "${val.@value}, ${val.'@package'}.${val.@source}, ${val.@name}"
}

def printProblem(doc, id, problemValue) {

    def metric = doc.Metric.find {it.@id == id}

    def list = metric.Values.Value.findAll {it.@value.toInteger() > problemValue}

    list.each {
        printMetric(it)
    }
}

def doc = new XmlSlurper().parse(new File("metrics.xml"))

println "NBD ( > 8)"
printProblem(doc, "NBD", 8)

/*
println ""
println "NBD ���\�b�h���̍ő�l�X�g�� ( > 5)"

printProblem(doc, "NBD", 5)

println ""
println "MLOC ���\�b�h�̍s�� ( > 100)"

printProblem(doc, "MLOC", 100)

*/