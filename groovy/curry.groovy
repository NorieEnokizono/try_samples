def f = {x, y, z ->  x + y + z}

//x �� y �̒l���w�肳�ꂽ���
def f1 = f.curry(100, 20)

println f1(3)

//x �̒l���w�肵�����
def f2 = f.curry(1)

println f2(20, 300)

//f2 �� y �̒l���w�肵�����
def f3 = f2.curry(20)

println f3(3000)
