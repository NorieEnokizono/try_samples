package fits.sample;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

@Service
public class MongoTemplateSampleService implements SampleService {

	@Autowired
	private MongoTemplate temp;

	//Data ��ǉ�����
	public void addData(List<Data> list) {
		temp.insertList(list);
	}

	//�w�薼�� Data ���擾����
	public List<Data> getData(String name) {
		BasicDBObject query = new BasicDBObject("name", name);
		return temp.query(query, Data.class);
	}

	//�w�薼���܂݁A�w��|�C���g���傫�� Data ���擾����
	public List<Data> findData(String name, int point) {

		DBObject query = QueryBuilder.start("name").regex(Pattern.compile(".*" + name + ".*")).and("point").greaterThan(point).get();

		return temp.query(query, Data.class);
	}
}
