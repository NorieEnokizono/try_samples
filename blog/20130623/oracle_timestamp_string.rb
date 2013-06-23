# -*- encoding: utf-8 -*-
require 'java'
require_relative 'lib/ojdbc14.jar'
#require_relative 'lib/ojdbc5.jar'
#require_relative 'lib/ojdbc6.jar'

module Ojdbc
	include_package 'oracle.sql'
end

date = Ojdbc::TIMESTAMP.new('2013-06-07 13:20:30')

puts date.to_string
puts date.to_jdbc.to_string
puts date.timestamp_value.to_string
puts date.string_value
