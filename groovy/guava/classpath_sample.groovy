package sample

@Grab('com.google.guava:guava:16.0-rc1')
import com.google.common.reflect.ClassPath

class SampleData {
}

/* �f�B���N�g���������� JAR �t�@�C�������X�L�������邾���Ȃ̂�
 * ���̃X�N���v�g���Œ�`���Ă���N���X�͎擾�ł��Ȃ�
 */
ClassPath.from(getClass().classLoader).getTopLevelClasses('sample').each {
	println "package: ${it.packageName}, simpleName: ${it.simpleName}, name: ${it.name}"
}

/*
 * com.google.common �p�b�P�[�W�����̃N���X�͑��݂��Ȃ�
 */
ClassPath.from(getClass().classLoader).getTopLevelClasses('com.google.common').each {
	println "package: ${it.packageName}, simpleName: ${it.simpleName}, name: ${it.name}"
}

/*
 * com.google.common �̃T�u�p�b�P�[�W���̃N���X���o��
 */
ClassPath.from(getClass().classLoader).getTopLevelClassesRecursive('com.google.common').each {
	println "package: ${it.packageName}, simpleName: ${it.simpleName}, name: ${it.name}"
}

