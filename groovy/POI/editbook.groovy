import java.io.*
import org.apache.poi.hssf.usermodel.*

//�R���X�g���N�^�ǉ�
HSSFWorkbook.metaClass.constructor << {String xlsFileName ->
    new HSSFWorkbook(new FileInputStream(xlsFileName))
}

//���\�b�h�ǉ�
HSSFSheet.metaClass.getCell << {row, col -> 
    rowObj = getRow(row)
    if (rowObj == null) rowObj = createRow(row)

    cellObj = rowObj.getCell((short)col)
    return (cellObj != null)? cellObj: rowObj.createCell((short)col)
}

//�v���p�e�B�ǉ�
HSSFCell.metaClass.getText << { ->
    richStringCellValue.string
}

//�v���p�e�B�ǉ�
HSSFCell.metaClass.setText << {text ->
    setCellValue(new HSSFRichTextString(text))
}

wb = new HSSFWorkbook("test.xls")
sh = wb.getSheet("�e�X�g�V�[�g")

cell = {row, col -> sh.getCell(row, col)}

last_row = {sheet -> sheet.getRow(sheet.lastRowNum)}

println cell(3, 0).text

println "�Ō�̃Z�� (${sh.lastRowNum}, ${last_row(sh).lastCellNum})"

cell(3, 0).text = "�e�X�g"
cell(20, 3).text = "���������E�E�E"

outfile = new FileOutputStream("test_new.xls")

wb.write(outfile)
outfile.close()
