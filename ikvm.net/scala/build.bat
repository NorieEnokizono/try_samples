@echo off

set SCALA_JAR=%SCALA_HOME%\lib\scala-library.jar
set SCALA_COMPILE_JAR=%SCALA_HOME%\lib\scala-compiler.jar;%SCALA_JAR%

rem   �R���p�C�����s�iscalac ���g���ƃo�b�`���I�����Ă��܂��̂Ŏ��O�Ŏ��{�j
java -Dscala.home=%SCALA_HOME% -cp %SCALA_COMPILE_JAR% scala.tools.nsc.Main SampleTest.scala

rem  JAR�t�@�C����
jar cfm sample.jar manifest.mf *.class

rem  .NET�A�Z���u����
ikvmc sample.jar %SCALA_JAR%
