@echo off

set GROOVY_JAR=%GROOVY_HOME%\embeddable\groovy-all-1.7.4.jar

rem   groovyc�ɂ��R���p�C�����s�igroovyc �����̂܂܎g���ƃo�b�`��
rem   �I�����Ă��܂����߃R���p�C���[�𒼐ڎ��s�j
java -Dgroovy.home=%GROOVY_HOME% -classpath %GROOVY_JAR% org.codehaus.groovy.tools.GroovyStarter --main org.codehaus.groovy.tools.FileSystemCompiler --conf %GROOVY_HOME%\conf\groovy-starter.conf ImplInf.groovy

rem  JAR�t�@�C����
jar cfm implinf.jar manifest.mf *.class

rem  .NET�A�Z���u����
ikvmc implinf.jar %GROOVY_JAR%
