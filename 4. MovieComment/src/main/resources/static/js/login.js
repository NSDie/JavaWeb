$("#userlogin").click(function () {
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
        url:"./api/login",
        type:"POST",
        data:{
            username:username,
            password:password
        },
        dataType:"json",
        ascyn:false,
        success: function(data) {
            console.log(data);
            if(data.stateCode === "1"){
                alert(data.msg);
                window.location.href="./index";
            }else{
                alert(data.msg);
            }
        }
    })
})