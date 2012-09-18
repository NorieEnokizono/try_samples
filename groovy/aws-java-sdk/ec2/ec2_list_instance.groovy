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

def ec2 = new AmazonEC2Client(new PropertiesCredentials(new File("setting.properties")))

ec2.describeRegions(new DescribeRegionsRequest().withRegionNames(regionName)).regions.each {
	ec2.endpoint = it.endpoint

	ec2.describeInstances().reservations.each {ins ->
		println ins
	}
}
