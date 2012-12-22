/*
 * jasperreports �� ivy-5.0.0.xml �𒼐ڕҏW����
 *
 *  (1) commons-collections �̃o�[�W������ 3.2.1 �֕ύX
 *  (2) iText �̃o�[�W������ 4.2.0 �֕ύX
 *  (3) xml-apis �̃o�[�W������ 2.0.2 �֕ύX
 *
 */
@Grab('net.sf.jasperreports:jasperreports:5.0.0')
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.*

if (args.length < 3) {
	println "<template file> <data csv file> <dest file>"
	return
}

def templateFileName = args[0]
def dataFileName = args[1]
def destFileName = args[2]

def report = JasperCompileManager.compileReport(templateFileName)

def ds = new JRCsvDataSource(dataFileName)
//�擪�s���w�b�_�[�̏ꍇ�� true ��ݒ�
ds.useFirstRowAsHeader = true

def jsPrint = JasperFillManager.fillReport(report, [:], ds)

JasperExportManager.exportReportToPdfFile(jsPrint, destFileName)
