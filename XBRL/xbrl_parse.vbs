
Set doc = WScript.CreateObject("MSXML2.DOMDocument")

doc.async = False
doc.SetProperty "SelectionLanguage", "XPath"
'XML���O��Ԃ̐ݒ�
doc.SetProperty "SelectionNamespaces", "xmlns:jpfr-t-cte=""http://info.edinet-fsa.go.jp/jp/fr/gaap/t/cte/2010-03-11"""

doc.load WScript.Arguments.Item(0)

Set values = doc.SelectNodes("//jpfr-t-cte:OperatingIncome[@contextRef='CurrentYearConsolidatedDuration']")

For Each v in values
	WScript.Echo v.Text
Next

