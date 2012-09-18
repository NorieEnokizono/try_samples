/**
 * Grab �̃G���[��������邽��
 * .groovy/grapes/com.amazonaws/aws-java-sdk/ivy-1.3.19 ��
 * httpclient �� rev �����l�� 4.1 ���� 4.2.1 �֕ύX���Ă���
 */
@Grab('com.amazonaws:aws-java-sdk:1.3.19')
import com.amazonaws.services.ec2.*
import com.amazonaws.services.ec2.model.*
import com.amazonaws.auth.*

def regionName = "ap-northeast-1"
def imageId = args[0]

def ec2 = new AmazonEC2Client(new PropertiesCredentials(new File("setting.properties")))

ec2.describeRegions(new DescribeRegionsRequest().withRegionNames(regionName)).regions.each { r ->
	ec2.endpoint = r.endpoint

	ec2.describeImages(new DescribeImagesRequest().withImageIds(imageId)).images.each {
		println "${it.imageId}, ${it.name}, ${it.description}, ${it.architecture}, ${it.ownerId}"
	}
}
