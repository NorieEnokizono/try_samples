/**
 * Grab �̃G���[��������邽��
 * .groovy/grapes/com.amazonaws/aws-java-sdk/ivy-1.3.19 ��
 * httpclient �� rev �����l�� 4.1 ���� 4.2.1 �֕ύX���Ă���
 */
@Grab('com.amazonaws:aws-java-sdk:1.3.19')
import com.amazonaws.services.ec2.*
import com.amazonaws.auth.*

def props = new Properties()
props.load(getClass().getResourceAsStream("setting.properties"))

def ec2 = new AmazonEC2Client(new BasicAWSCredentials(props.ec2_accessKey, props.ec2_secretKey))

// ���[�W�������ɏ����擾
ec2.describeRegions().regions.each {r ->
	println "*** ${r.regionName} ***"

	// �Ώۂ̃��[�W������ݒ肷��
	ec2.endpoint = r.endpoint

	println "--- Addresses ---"

	ec2.describeAddresses().addresses.each {
		println it
	}

	println "--- Availability Zones ---"

	ec2.describeAvailabilityZones().availabilityZones.each {
		println it
	}

	println "--- BundleTasks ---"

	ec2.describeBundleTasks().bundleTasks.each {
		println it
	}

	println "--- ConversionTasks ---"

	ec2.describeConversionTasks().conversionTasks.each {
		println it
	}

	println "--- CustomerGateways ---"

	ec2.describeCustomerGateways().customerGateways.each {
		println it
	}

	println "--- DHCP Options ---"

	ec2.describeDhcpOptions().dhcpOptions.each {
		println it
	}

	/* ���������̂Œ���
	println "--- Images ---"

	ec2.describeImages().images.each {
		println it
	}
	*/

	println "--- Instances ---"

	ec2.describeInstances().reservations.each {
		println it
	}

	println "--- InternetGateways ---"

	ec2.describeInternetGateways().internetGateways.each {
		println it
	}

	println "--- NetworkInterfaces ---"

	ec2.describeNetworkInterfaces().networkInterfaces.each {
		println it
	}
}
