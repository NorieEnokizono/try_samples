import grails.converters.JSON

def t = ["name": "�e�X�g�f�[�^", "point": 100]

println new JSON(t)

