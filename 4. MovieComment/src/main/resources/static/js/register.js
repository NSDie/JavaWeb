
$("#register").click(function(){
    var username = $("#username").val();
    var password = $("#psw").val();
    var repassword = $("#repsw").val();
    if( username === null || username === "" || password === null || password === "" )
    {
        alert("提示:账号和密码不能为空");
    }else if (password != repassword){
        alert("提示:两次密码不一致！");
    }else{
        $.ajax({
            type:"POST",
            url:"./api/register",
            data:{
                username:username,
                password:password
            },
            dataType:"json",
            ascyn:false,
            success: function(data){
                console.log(data);
                if(data.stateCode == '1'){
                    alert(data.msg);
                    window.location.href="login";
                }
                else{
                    alert(data.msg);

                }
            }
        })
    }
})