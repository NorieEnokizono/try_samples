
class When(a: => Any) {
	def when(b: Boolean) = if(b) a
}

implicit def toWhen(a: => Any) = new When(a)

println("test1").when(true)
//��̏ȗ��L�q�n
println("test1") when true
println("test2") when false

