require "stringio"
require "csv"

export = StringIO.new

CSV::Writer.generate(export) do |csv|
	csv << ["test", "���{��,\"�e�X�g"]
	csv << ["a", "b", "123"]
end

export.rewind

puts export.read
