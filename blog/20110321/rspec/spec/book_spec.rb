require "java"

module Fits
	include_package "fits.sample"
end

describe "Book" do
	context "�������" do
		before do
			@b = Fits::Book.new
		end

		it "comments �� nil �ł͂Ȃ�" do
			@b.comments.should_not be_nil
		end

		it "comments �͋�" do
			@b.comments.size.should == 0
		end
	end

	context "Comment ��ǉ�" do
		before do
			@b = Fits::Book.new
			@b.comments.add(Fits::Comment.new)
		end

		it "Comment ���ǉ�����Ă���" do
			@b.comments.size.should == 1
		end
	end
end
