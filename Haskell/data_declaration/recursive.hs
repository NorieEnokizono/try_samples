
data Stack a = Empty | Push a (Stack a)

isEmpty :: Stack a -> Bool
isEmpty Empty = True
isEmpty (Push _ _) = False

top :: Stack a -> a
top (Push a _) = a

pop :: Stack a -> Stack a
pop (Push _ stk) = stk

main = do
	let e = Empty
	let t1 = Push "test" (Empty)
	let t2 = Push "test2" (Push "test2-1" (Empty))

	-- True �Əo��
	print $ isEmpty e
	-- False �Əo��
	print $ isEmpty t1
	-- False �Əo��
	print $ isEmpty t2

	-- "test" �Əo��
	print $ top t1
	-- "test2-1" �Əo��
	print $ top (pop t2)
