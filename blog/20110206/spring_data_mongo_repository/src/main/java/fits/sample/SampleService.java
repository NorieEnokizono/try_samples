package fits.sample;

import java.util.List;

public interface SampleService {

	//Data ��ǉ�����
	void addData(List<Data> list);

	//�w�薼�� Data ���擾����
	List<Data> getData(String name);

	//�w�薼���܂݁A�w��|�C���g���傫�� Data ���擾����
	List<Data> findData(String name, int point);
}
