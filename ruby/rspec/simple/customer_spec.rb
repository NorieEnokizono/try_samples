
describe "�ڋq�f�[�^" do

	before :all do
		puts "�U�镑���̎��s�O"
	end

	before do
		puts "�T���v���̎��s�O"
	end

	after do
		puts "�T���v���̎��s��"
	end

	after :all do
		puts "�U�镑���̎��s��"
	end

	it "sample1" do
		puts "sample1"
		"".length.should == 0
	end

	it "sample2" do
		puts "sample2"
		"1".length.should_not == 0
	end
end