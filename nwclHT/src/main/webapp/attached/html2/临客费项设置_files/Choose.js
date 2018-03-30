<!--
//弹出选择窗口 
  function openA(M)   
  {   
  window.open(M,'add','scrollbars=yes,resizable=no,top='+(screen.height-380)/2+',left='+(screen.width-480)/2+',width=480,height=380;center:yes;help:no;status:no;');   
  }  

//弹出复选用户的窗口
function openAddressList(stringfiled){
win = window.open(stringfiled,"选择用户", 'width=600,height=450,top='+(screen.height-450)/2+',left='+(screen.width-600)/2+',resizeable=no,scrollbars=yes,menubar=no,status=yes;');
win.focus();
}
//-->