# coding: shift_jis

class Course:
	def __init__(self, name):
		self.name = name

def get_courses():
	return [Course("test1"), Course("�e�X�g2"), Course("�Ă���abc")]

from System import *
from System.Collections import *

def get_courses2():
	result = Hashtable()
	result["1"] = "test1"
	result["2"] = "�e�X�g2"
	return result