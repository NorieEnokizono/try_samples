import fj.*;
import fj.data.*;
import static fj.P.*;
import static fj.Show.*;
import static fj.data.List.*;

/**
 * OpenJDK build 1.8.0-ea-lambda-nightly-h1171-20120911-b56-b00 ��
 * JSR-335 ���g���Ď������� Functional Java �̃T���v��
 *
 */
class MoveKnight {
	public static void main(String[] args) {

		Show<KnightPos> ks = show( f((KnightPos p) -> Stream.fromString("(" + p._1() + ", " + p._2() + ")")) );

		listShow(ks).println( in3(pos(6, 2)) );

		booleanShow.println( canReachIn3(pos(6, 2), pos(6, 1)) );
		booleanShow.println( canReachIn3(pos(6, 2), pos(7, 3)) );
	}

	// �i�C�g�̎��̈ړ�����
	static F<KnightPos, List<KnightPos>> moveKnight() {
		return f( (KnightPos p) -> list(
			pos(p._1() + 2, p._2() - 1), pos(p._1() + 2, p._2() + 1),
			pos(p._1() - 2, p._2() - 1), pos(p._1() - 2, p._2() + 1),
			pos(p._1() + 1, p._2() - 2), pos(p._1() + 1, p._2() + 2),
			pos(p._1() - 1, p._2() - 2), pos(p._1() - 1, p._2() + 2)
		).filter(f( (KnightPos t) ->
			1<= t._1() && t._1() <= 8 && 1 <= t._2() && t._2() <= 8
		)) );
	}

	// 3���̈ړ��ʒu���
	static List<KnightPos> in3(KnightPos start) {
		return list(start).bind(moveKnight()).bind(moveKnight()).bind(moveKnight());
	}

	// �w��ʒu��3��œ��B�ł��邩�ۂ��𔻒�
	static boolean canReachIn3(KnightPos start, KnightPos end) {
		return in3(start).exists( f((KnightPos p) -> p._1() == end._1() && p._2() == end._2()) );
	}

	// fj.F �̃C���X�^���X�𐶐�
	static <A, B> F<A, B> f(final Func<A, B> func) {
		return new F<A, B>() {
			@Override
			public B f(A a) {
				return func.apply(a);
			}
		};
	}

	static KnightPos pos(int x, int y) {
		return new KnightPos(x, y);
	}

	// �����_�����K�p�����C���^�[�t�F�[�X
	interface Func<A, B> {
		B apply(A a);
	}

	static class KnightPos extends P2<Integer, Integer> {
		private int x;
		private int y;

		KnightPos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override public Integer _1() { return x; }
		@Override public Integer _2() { return y; }
	}
}
