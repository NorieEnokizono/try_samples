package fits.sample;

import android.os.AsyncTask;

class SingleAsyncTask<T, U> extends AsyncTask<T, Void, U> {
	protected U doInBackground(T... params) {
		return (params.length > 0)? doSingleTask(params[0]): null;
	}

	//Scala �̃T�u�N���X���ŃI�[�o�[���C�h���邽�߂̃��\�b�h
	protected U doSingleTask(T param) {
		return null;
	}
}
