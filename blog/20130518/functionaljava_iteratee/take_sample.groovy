@Grab('org.functionaljava:functionaljava:3.1')
import fj.F
import fj.P1
import fj.data.IO
import fj.data.Option
import static fj.data.Iteratee.*

import java.nio.charset.StandardCharsets

def take(int n) {
	def step
	step = { int count, List acc, Input s ->
		// Empty ���̏������e�iInput ����̏ꍇ�j
		def empty = { IterV.cont( { step(count, acc, it) } as F) } as P1
		// EOF ���̏������e�i�I�[�ɒB�����ꍇ�j
		def eof = { IterV.done(acc, s) } as P1

		// El ���̏������e�iInput �ɒl���ݒ肳��Ă���ꍇ�j
		def el = {
			return { value ->
				if (count <= 0) {
					IterV.done(acc, s)
				}
				else {
					IterV.cont({ step(count - 1, acc << value, it) } as F)
				}
			} as F
		} as P1

		s.apply(empty, el, eof)
	}

	IterV.cont({ step(n, [], it) } as F)
}

def iter = IterV.drop(1).bind({ take(3) } as F)

def ioIter = IO.enumFileLines(new File(args[0]), Option.some(StandardCharsets.UTF_8), iter)

ioIter.run().run().each {
	println "#${it}"
}
