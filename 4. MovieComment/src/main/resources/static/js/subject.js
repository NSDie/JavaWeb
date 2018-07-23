$(".like").click(function(){
    alert($('#like-count').text());
})
AddComment.onsubmit = function () {
    var user = document.getElementById("person");
    console.log(user);
    if(user===null||user===""){
        alert("评论前请先登录！");
        return false;
    }else if($(".ipt-txt").text()===null ||$(".ipt-txt").text()==="" ){
        alert("评论不能为空！");
        return false;
    }
    return true;
}
