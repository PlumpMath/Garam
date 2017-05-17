var inp="",ans="",probID="";
function startTime() {
    var today = new Date();
    var h = today.getHours(), m = today.getMinutes(), d = today.getDate(), mo = today.getMonth()+1;
    m = checkTime(m);
    document.getElementById('time').innerHTML = h + ":" + m;
    document.getElementById('date').innerHTML = mo + "월 " + d + "일";
    var t = setTimeout(startTime, 500);
}
function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}
function getQueryParams(qs) {
    qs = qs.split('+').join(' ');
    var params = {},
        tokens,
        re = /[?&]?([^=]+)=([^&]*)/g;
    while (tokens = re.exec(qs)) {
        params[decodeURIComponent(tokens[1])] = decodeURIComponent(tokens[2]);
    }
    return params;
}
window.onload = function() {
    startTime();
    var query = getQueryParams(document.location.search);
    ans=query["ans"];
    probID=query["probID"];
    document.getElementById('text-hint').innerHTML = query["hint"];
    document.getElementById('btn-0').onclick = function() {
        inp+='0';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-1').onclick = function() {
        inp+='1';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-2').onclick = function() {
        inp+='2';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-3').onclick = function() {
        inp+='3';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-4').onclick = function() {
        inp+='4';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-5').onclick = function() {
        inp+='5';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-6').onclick = function() {
        inp+='6';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-7').onclick = function() {
        inp+='7';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-8').onclick = function() {
        inp+='8';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-9').onclick = function() {
        inp+='9';
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-back').onclick = function() {
        inp=inp.slice(0,inp.length-1);
        document.getElementById('text-pw').innerHTML = inp;
    }
    document.getElementById('btn-submit').onclick = function() {
        if(inp===ans) {
            judge.correct(probID);
        }
        else {
            judge.wrong(probID,inp);
            inp="";
            document.getElementById('text-pw').innerHTML = "";
        }
    }
}