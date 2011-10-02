require "rubygems"
require "sinatra/base"
require "webrick/https"
require "openssl"

class SampleApp < Sinatra::Base
	#Basic�F��
	use Rack::Auth::Basic do |user, pass|
		user == 'user1'
	end

	post '/' do
		p params
		'hello'
	end
end

#WEBrick �� SSL ���g�p���邽�߂̏���
#�i���s���Ɏ��ȏؖ������������������j
Rack::Handler::WEBrick.run SampleApp, {
	:Port => 8443, 
	:SSLEnable => true, 
	#�N���C�A���g�����؂��Ȃ����߂̐ݒ�
	:SSLVerifyClient => OpenSSL::SSL::VERIFY_NONE, 
	:SSLCertName => [
		["CN", WEBrick::Utils::getservername]
	]
}
