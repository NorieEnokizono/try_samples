@Grapes([
    @Grab(group = 'com.h2database', module = 'h2', version = '1.3.157'),
    @GrabConfig(systemClassLoader = true)
])
//�ȉ��̂悤�ɂ��邾�����ƃV�X�e���N���X���[�_�[�Ɋ܂܂�Ȃ����߃G���[����
//@Grab(group = 'com.h2database', module = 'h2', version = '1.3.157')
import groovy.sql.Sql

def db = Sql.newInstance("jdbc:h2:mem:db1", "org.h2.Driver")

def sql = "select * from CSVREAD('data/test_data.csv')"

db.eachRow(sql, []) {r ->
    println r
}
