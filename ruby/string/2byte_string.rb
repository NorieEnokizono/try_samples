
p "�{".index("{")
p "�{" =~ /\{/
p "�{" =~ /\{/s

p "�c" =~ /c/
p "�c" =~ /c/s

puts "--------"

require 'jcode'
$KCODE = 's'

p "�{".index("{")
p "�{" =~ /\{/

