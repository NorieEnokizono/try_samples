class Test {
    def count = 0

    def getFunction(name) {
        count++

        def i = 0
        def val = "test${count}-${name}"

        return {
            i++
            println "count=${count}, i=${i}, val=${val}, name=${name}"
        }
    }
}

test = new Test()

closure1 = test.getFunction("���b�Z�[�W1")

closure1()
closure1()

closure2 = test.getFunction("���b�Z�[�W2")

closure2()

closure1()
