# coding: shift_jis

import re

print "�e�X�g"

msg = "�Ă��ƂŁ[��abc��������"

m = re.search("([abc]+)", msg)

print m.group()

