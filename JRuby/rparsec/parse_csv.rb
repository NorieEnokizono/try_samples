
require 'rubygems'
require 'rparsec'

include RParsec::Parsers

eol = string "\r\n"
#pack�ŕ����񉻂ł���悤�ɁA" �̕����R�[�h��Ԃ��悤�ɂ��Ă���ivalue �̉ӏ��j
quotedChar = not_char('"') | string('""') >> value('"'[0])
#many�ŕ����R�[�h�̔z�񂪌��ʓI�ɕԂ邽�߁Apack���g���ĕ�����
quotedCell = char('"') >> quotedChar.many.bind {|s| value(s.pack("c*"))} << char('"')
cell = quotedCell | regexp(/[^,\r\n]*/)
line = cell.separated(char ',')
csvFile = (line << eol).many
#�ꉞ�A�ȉ��ł��i�������A���ʂ������ς��j
#csvFile = line.delimited(eol)

cs = $stdin.readlines.join
res = csvFile.parse cs

p res
puts res
