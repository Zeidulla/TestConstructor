
let index=-1;
let arr =[];
let q;
let ansh;
let size = document.getElementById('size').value;
let testId = document.getElementById('testID').value;
function checkans()
{


    if(index==-1){
        index++;
        q=document.getElementById('q'+index);
        ansh= document.getElementById('ans'+index);
        q.removeAttribute("hidden");
        ansh.removeAttribute("hidden");
        let butt=document.getElementById('butt').value='Следующий вопрос';
    }
    else if(index<size-1){
        let nameans="answer"+index;
        console.log(nameans);
        let answer = document.querySelector('input[name='+nameans+']:checked').value;
        arr.push(answer);
        console.log(arr);
        q=document.getElementById('q'+index);
        ansh= document.getElementById('ans'+index);
        q.setAttribute("hidden","true");
        ansh.setAttribute("hidden","true");
        index++;
        q=document.getElementById('q'+index);
        ansh= document.getElementById('ans'+index);
        q.removeAttribute("hidden");
        ansh.removeAttribute("hidden");

    }
    else if(index==size-1){
        let nameans="answer"+index;
        let answer = document.querySelector('input[name='+nameans+']:checked').value;
        arr.push(answer);
        q=document.getElementById('q'+index);
        ansh= document.getElementById('ans'+index);
        q.setAttribute("hidden","true");
        ansh.setAttribute("hidden","true");
        let butt=document.getElementById('delreg').remove();
        let form=document.getElementById('formAnswers');

        for(let i=0;i<arr.length;i++){
            form.innerHTML+="<input type=\"hidden\" name=\"answers\" value=\""+arr[i]+"\">";
        }
        form.innerHTML+="<input class=\"reginaBut\" type=\"submit\" value=\"Завершить тест\">";
    }

}