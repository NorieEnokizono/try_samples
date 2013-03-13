rx = require 'rxjs'
fs = require 'fs'

fromFile = (file) ->
	rx.Observable.create (observer) ->
		stream = fs.createReadStream(file, {encoding: 'utf-8'})

		# �G���[������
		stream.on 'error', (ex) -> observer.onError ex
		# ������
		stream.on 'end', -> observer.onCompleted()

		stream.on 'close', -> console.log '*** close'

		buf = ''

		stream.on 'data', (data) ->
			list = (buf + data).split(/\n|\r\n/)
			buf = list.pop()
			# 1�s���̃f�[�^�� PUSH
			observer.onNext line for line in list

		# �������Ȃ��֐���Ԃ�
		->

fromFile(process.argv[2]).skip(1).take(2).subscribe (x) -> console.log '#' + x
