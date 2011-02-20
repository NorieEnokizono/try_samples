
class Comment
	include Mongoid::Document

	field :content
	field :created_date, :type => Date

#	has_one_related �� User �� belongs_to_related ���g����
#   �G���[���������邽�߁A�Ƃ肠���� field �ő�p
#
#	has_one_related :user
	field :user, :type => User

	embedded_in :book, :inverse_of => :comments

end
