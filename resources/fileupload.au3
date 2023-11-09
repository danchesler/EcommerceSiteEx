;#include <MsgBoxConstants.au3>

;Local $i

ControlFocus("Open", "", "Edit1")
ControlSetText("Open", "", "Edit1", "C:\Users\super\eclipse-SeleniumProject\automation-exercise\resources\testpdf.pdf")
ControlClick("Open", "", "Button1")
;MsgBox($MB_SYSTEMMODAL, "", $i)