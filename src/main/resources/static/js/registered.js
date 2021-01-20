$(document).ready(function () {
    $("#username").blur(function () {
        var username = $("#username").val();
        // alert(username)
        $.ajax({
            url: "/Blog/getUserNum",
            type: "get",
            data: {
                username: username
            },
            dataType: "json",
            success:function (res) {
                if (res.flag == false){
                        $("#count").css("display","contents")
                        $("#bt").attr("disabled",true);
                }else {
                    $("#count").css("display","none")
                    $("#bt").attr("disabled",false);
                }
            }
        })
    })


    $("#bt").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var InviteCode= $("#inviteCode").val();
        $.ajax({
            url: "/Blog/register",
            type: "post",
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            data:JSON.stringify({
                username:username,
                password:password,
                inviteCode:InviteCode
            }),
            success:function (res) {
                if (res.flag){
                    $("#ok").css("display","contents");
                    $("#error").html();
                    $("#username").val("");
                    $("#password").val("");
                    $("#inviteCode").val("");
                }
                if (!res.flag){
                    alert("error")
                    $("#error").html(res.message);
                    // $("")
                }
            }
        })
    })
})