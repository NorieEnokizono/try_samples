
import java.io.*
import org.apache.poi.hslf.usermodel.*

ss = new SlideShow()

s1 = ss.createSlide()
s1.addTitle().text = "�e�X�g�X���C�h"


outfile = new FileOutputStream("test.ppt")

ss.write(outfile)
outfile.close()

