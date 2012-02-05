@Grapes([
    @Grab("com.h2database:h2:1.3.164"),
    @GrabConfig(systemClassLoader = true)
])
import groovy.sql.Sql

class Func {
	static String toChar(int value) {
		println "toChar : int ${value}"
		value.toString()
	}
	//Oracle �̓��t�t�H�[�}�b�g�� Java �p�ɕϊ��i�ꕔ�̂݁j
	static String toChar(Date value, String dateFormat) {
		if (value == null) {
			return value
		}

		dateFormat = dateFormat.toLowerCase()
								.replaceAll("mm", "MM")
								.replaceAll("hh24", "HH")
								.replaceAll("mi", "mm")

		println "toChar : ${value}, ${dateFormat}"

		value.format(dateFormat)
	}

	/* ��L�̑���Ɉȉ��ł���
	static String toChar(String value, String dateFormat) {
		if (value == null) {
			return value
		}

		dateFormat = dateFormat.toLowerCase()
						.replaceAll("mm", "MM")
						.replaceAll("hh24", "HH")
						.replaceAll("mi", "mm")

		println "toChar : ${value}, ${dateFormat}"

		def dateCls = (value.length() > 10)? java.sql.Timestamp: java.sql.Date
		dateCls.valueOf(value).format(dateFormat)
	}
	*/
}

//�C��������DB�Ƃ��� H2 ���g�p
def db = Sql.newInstance("jdbc:h2:mem:", "org.h2.Driver")

db.execute("create table TDATA as select * from CSVREAD('testdata.csv')")
//�ȉ��ł���
//db.execute("create table TDATA as select * from CSVREAD('classpath:/testdata.csv')")


//TO_CHAR �����[�U�[��`�֐��Ƃ��Ē�`
db.execute('CREATE ALIAS TO_CHAR FOR "Func.toChar"')

def sql = '''
	select
		TO_CHAR(no) as no, 
		title,
		TO_CHAR(create_datetime, 'yyyy/mm/dd hh24:mi:ss') as cdatetime,
		TO_CHAR(create_date, 'yyyy/mm/dd') as cdate
	from TDATA
'''

db.eachRow(sql) {r ->
	println "�������� : ${r.no}, ${r.title}, ${r.cdatetime}, ${r.cdate}"
}
