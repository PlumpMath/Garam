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
    var image = document.getElementById("hint-image");
    image.src = query["hint"];
    document.getElementById('btn-submit').onclick = function() {
        var inp = document.getElementById('ans-textbox').value;
        if(inp===ans) {
            judge.correct(probID);
        }
        else {
            judge.wrong(probID,inp);
            document.getElementById('ans-textbox').value = "";
        }
    }
}