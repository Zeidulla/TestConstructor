//Мы должны уменьшать таймер после каждой секунды, чтобы сделать это,.
//мы собираемся создать функцию Javascript, которая будет сначала вызываться при загрузке страницы теста,
//а затем мы будем вызывать эту функцию рекурсивно после каждой секунды для обратного отсчета времени
var tim;
 var min = '${sessionScope.min}';
 var sec = '${sessionScope.sec}';
 var f = new Date();

 function customSubmit(someValue){ 
 document.questionForm.minute.value = min; 
 document.questionForm.second.value = sec; 
 document.questionForm.submit(); 
 } 

 function examTimer() {
 if (parseInt(sec) >0) {

 document.getElementById("showtime").innerHTML = "Time Remaining :"+min+" Minutes ," + sec+" Seconds";
 sec = parseInt(sec) - 1; 
 tim = setTimeout("examTimer()", 1000);
 }
 else {

 if (parseInt(min)==0 && parseInt(sec)==0){
 document.getElementById("showtime").innerHTML = "Time Remaining :"+min+" Minutes ," + sec+" Seconds";
 alert("Time Up");
 document.questionForm.minute.value=0;
 document.questionForm.second.value=0;
 document.questionForm.submit();

 }

 if (parseInt(sec) == 0) { 
 document.getElementById("showtime").innerHTML = "Time Remaining :"+min+" Minutes ," + sec+" Seconds"; 
 min = parseInt(min) - 1;
 sec=59;
 tim = setTimeout("examTimer()", 1000);
 }

 }
 }