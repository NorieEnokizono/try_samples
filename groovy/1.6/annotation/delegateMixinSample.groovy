
class SampleFeature {
	def name

	def test(msg) {
		println "${this} : $name - $msg"
	}
}

@Mixin(SampleFeature)
class MixinSample {
	void printName() {
		println name
	}
}

class DelegateSample {

	@Delegate SampleFeature feature = new SampleFeature()

	void printName() {
		println name
	}
}

ms = new MixinSample(name: "Mixin�e�X�g")
ms.printName()
ms.test "call test"

//setName ���p�ӂ���Ȃ����߈ȉ��͕s��
//ds = new DelegateSample(name: "Delegate�e�X�g")
ds = new DelegateSample()
//�ȉ����s��
//ds.name = "Delagate�e�X�g"
ds.feature.name = "Delegate�e�X�g"
ds.printName()
ds.test "call test"


def printMethods = {
	println "--- ${it} methods ---"

	it.metaClass.methods.each {m ->
		println m
	}
}

printMethods DelegateSample

println ""

printMethods MixinSample

