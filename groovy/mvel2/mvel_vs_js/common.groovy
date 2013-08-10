// ����
class Order {
	String no
	List<OrderItem> items = []
	PaymentMethod payment = new PaymentMethod()
	// ����
	BigDecimal postage = 0

	BigDecimal getTotalPrice() {
		postage + payment.commission + items.inject(0) { acc, item ->
			acc + item.totalPrice
		}
	}

	@Override
	String toString() {
		"Order( no: ${no}, postage: ${postage}, payment: ${payment}, total: ${totalPrice} )" + items.inject("") { acc, item -> "${acc}\n${item.toString()}" }
	}
}

// ��������
class OrderItem {
	Product product = new Product()
	BigDecimal qty = 1

	BigDecimal getPrice() {
		product.price
	}

	BigDecimal getTotalPrice() {
		getPrice() * qty
	}

	@Override
	String toString() {
		"- OrderItem( product: ${product}, qty: ${qty}, total: ${totalPrice} )"
	}
}

// ���i
class Product {
	String name
	BigDecimal price = 0

	@Override
	String toString() {
		"Product( name: ${name}, price: ${price} )"
	}
}

// �x�����@
class PaymentMethod {
	String name
	// �萔��
	BigDecimal commission = 0

	@Override
	String toString() {
		"PaymentMethod( name: ${name}, commission: ${commission} )"
	}
}
