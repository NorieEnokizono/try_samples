
puts "start"

callcc do |c|
	puts "callcc start"

	c.call

	#�ȉ��͎��s����Ȃ��ic.call ���� callcc �̏I���ɃW�����v���邽�߁j
	puts "callcc end"

end

puts "end"



puts "start2"

cont = nil

callcc do |c|
	cont = c
	puts "callcc start2"
end

puts "end2"

#�ȉ��̃R�[�h�� callcc ���I��������_�ɖ߂�̂� end2 �������ɏo�͂����
#�������AJRuby �ł� LocalJumpError ����������
cont.call
