//var url = "http://192.168.3.37:8080/exam/types";
var url = "http://localhost:8080/exam/types";


var app = new Vue({
    el: '#app',
    data: {
        superTypeList:[]
    }
})

// $.ajax({
//     url:url,
//     type:'GET',
//     jsonp:'callback',
//     dataType:'jsonp',
//     success:function (data) {
//         console.log(data);
//     }
// })

function callback(data){
    app.superTypeList=data;
}
var srcipt=document.createElement("script");
srcipt.src = url;
document.body.appendChild(srcipt);