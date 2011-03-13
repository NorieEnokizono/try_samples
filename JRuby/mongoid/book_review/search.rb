require "rubygems"
require "mongoid"

require "models/book"
require "models/user"
require "models/comment"

Mongoid.configure do |config|
	config.master = Mongo::Connection.new.db("book_review")
	#�L�[�̌^�� BSON::ObjectId �ɂ��邽�߂̐ݒ�
	#config.use_object_ids = true
end

Book.find(:all, :conditions => {'comments.content' => 'test1'}).order_by([:title, :asc]).each do |b|

	puts "--- #{b.title} ---"

	b.comments.each do |c|
		puts "#{c.content}, #{c.user_id} - #{c.user.name}"
	end
end

