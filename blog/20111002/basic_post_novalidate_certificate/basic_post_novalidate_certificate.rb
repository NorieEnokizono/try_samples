require 'net/https'
require 'uri'

url = URI.parse(ARGV[0])
user = ARGV[1]
pass = ARGV[2]
postData = ARGV[3]

https = Net::HTTP.new(url.host, url.port)
#SSL�g�p�̗L����
https.use_ssl = true
#SSL�ؖ����̌��؂����Ȃ����߂̐ݒ�
https.verify_mode = OpenSSL::SSL::VERIFY_NONE

res = https.start do
	req = Net::HTTP::Post.new(url.path)
	#Basic�F��
	req.basic_auth user, pass

	#POST�f�[�^�̐ݒ�
	req.body = postData

	#POST
	https.request(req)
end

#���ʂ̏o��
print res.body
