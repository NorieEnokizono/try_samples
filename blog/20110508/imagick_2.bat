@echo off

echo �J�n - %time%

convert -define jpeg:size=100 -thumbnail 100 sample.jpg sample_2.jpg

echo �I�� - %time%