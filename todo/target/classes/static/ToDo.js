function performGetRequest1() {
    var resultElement = document.getElementById('getResult1');
    resultElement.innerHTML = '';

    axios.get('http://localhost:8080/api/todo')
        .then(function (response) {
            resultElement.innerHTML = generateSuccessHTMLOutput(response);
        })
        .catch(function (error) {
            resultElement.innerHTML = generateErrorHTMLOutput(error);
        });
}

function generateSuccessHTMLOutput(response) {
    return  response.data.map(function (todo) {
        return (
            '<ul id="myUL">' + '<li>' + todo.id + '. ' + todo.task + '</li>' + '</ul>'

        );
    }).join('');
}

function generateErrorHTMLOutput(error) {
    return  '<h4>OOOPS! Virhe.</h4>';
}

document.getElementById('getResult1').addEventListener('submit', performPostRequest);

function performPostRequest(e) {
    // Create a new list item when clicking on the "Add" button
    var printValue = document.getElementById('printResult');
    var inputValue = document.getElementById('myInput').value;
    printValue.innerText = '';

    axios.post('http://localhost:8080/api/todo', {
        task: inputValue
    })
        .then(function(response) {
            printValue.innerHTML = "Let's see if this is working!";
        })
        .catch(function (error) {
            printValue.innerHTML = generateErrorHTMLOutput(error);
        });


    }
//HERE DELETE FUNCTION!
// Click on a close button to hide the current list item
var close = document.getElementsByClassName("close");
var i;
for (i = 0; i < close.length; i++) {
    close[i].onclick = function() {
        var div = this.parentElement;
        div.style.display = "none";
    }
}

