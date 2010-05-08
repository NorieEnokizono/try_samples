
require 'java'

module Sample
	include_package "sample"
end

describe Sample::Data, "Data �̎d�l" do

	before do
		@data = Sample::Data.new("test")
	end

	it "���O�������Ă���" do
		@data.should respond_to(:getName)
		@data.getName().should eql("test")
	end

	it "���O�͕ύX�ł��Ȃ�" do
		@data.should_not respond_to(:setName)
	end
end
