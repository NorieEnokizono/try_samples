package fits.sample;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SampleService {

	@Autowired
	private DataRepository rep;

	//Data ��ǉ�����
	public void addData(Iterable<Data> list) {
		rep.save(list);
	}

	//�w�薼�� Data ���擾����
	public List<Data> getData(String name) {
		return rep.findByName(name);
	}

	//�w�薼���܂݁A�w��|�C���g���傫�� Data ���擾����
	public List<Data> findData(String name, int point) {
		return rep.findByNameLikeAndPointGreaterThan(name, point);
	}
}
