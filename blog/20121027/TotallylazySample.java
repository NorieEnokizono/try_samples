
import java.math.BigDecimal;

import com.googlecode.totallylazy.Callable1;
import com.googlecode.totallylazy.Callable2;
import com.googlecode.totallylazy.Predicate;
import com.googlecode.totallylazy.Sequence;

import static com.googlecode.totallylazy.Sequences.*;

public class TotallylazySample {
	public static void main(String[] args) {

		Sequence<ProductItem> items = sequence(
			new ProductItem("id1", "���i1", new BigDecimal("1000"), 1),
			new ProductItem("id2", "���i2", new BigDecimal("3000"), 2),
			new ProductItem("id3", "���i3", new BigDecimal("1500"), 3)
		);
		// (1) �}�b�s���O
		Sequence<String> names = items.map(new Callable1<ProductItem, String>() {
			public String call(ProductItem it) {
				return it.getName();
			}
		});

		names.each(new Callable1<String, Void>() {
			public Void call(String n) {
				System.out.println(n);
				return null;
			}
		});
		// �ȉ��ł���
		/*
		for (String n : names) {
			System.out.println(n);
		}
		*/

		System.out.println("-----");

		// (2) �t�B���^�����O
		Sequence<ProductItem> highItems = items.filter(new Predicate<ProductItem>() {
			public boolean matches(ProductItem it) {
				return it.getPrice().compareTo(new BigDecimal("1500")) >= 0;
			}
		});

		highItems.each(new Callable1<ProductItem, Void>() {
			public Void call(ProductItem it) {
				System.out.println(it.getName());
				return null;
			}
		});

		System.out.println("-----");

		// (3) ��ݍ���
		BigDecimal total = items.foldLeft(BigDecimal.ZERO,
			new Callable2<BigDecimal, ProductItem, BigDecimal>() {
				public BigDecimal call(BigDecimal a, ProductItem b) {
					return a.add(b.getPrice().multiply(new BigDecimal(b.getQty())));
				}
			}
		);

		System.out.println(total);
	}
}

