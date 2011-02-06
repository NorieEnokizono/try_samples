package fits.sample;

import java.util.List;
import org.springframework.data.document.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<Data, java.math.BigInteger> {
	//�w�薼�� Data ���擾����
	List<Data> findByName(String name);

	//�w��̖��O���܂݁A�|�C���g���w��l���傫�� Data ���擾����
	List<Data> findByNameLikeAndPointGreaterThan(String name, int point);

}
