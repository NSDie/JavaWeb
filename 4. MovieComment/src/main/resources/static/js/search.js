$("#search").click(function () {
    var title = $("#til").val();
    if(title === null|| title === ""){
        alert("提示: 标题不能为空！！");
    }else{
        window.location.href = "./search/"+title;
    }
})