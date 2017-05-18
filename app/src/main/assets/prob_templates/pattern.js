var inp="",ans="",probID="";
var texts = [];
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
    var i=0;
    for(i=0;i<9;i++) {
        if(ans[i]=='0') break;
    }
    ans=ans.substring(0,i);
    document.getElementById('text-hint').innerHTML = query["title"];
    var lock = new PatternLock("#patternHolder", {
        onDraw:function(pattern) {
            if(ans===pattern) judge.correct(probID);
            else {
                lock.error();
                judge.wrong(probID,pattern);
            }
        }
    });
    for(i=1;i<10;i++) texts.push(query["t"+i.toString()]);
    textLocs();
}
window.onresize = function() {
    textLocs();
}
function textLocs() {
    var startLeft = $('#patternHolder').offset().left, startTop = $('#patternHolder').offset().top;
    for(var i=0;i<9;i++) {
        var name = $('#t'+(i+1).toString());
        $(name).html(texts[i]);
        $(name).css('top',startTop+(((i-i%3)/3)+1)*90);
        $(name).css('left',startLeft+(i%3)*90+65-$(name).width()/2);
    }
}