$(document).ready(function () {
            $.ajax({
                url: "/Blog/showAllUser",
                type: "get",
                dataType: "json",
                success:function (res) {
                    if (res.flag){
                        var size = res.data.length;
                        if (size != 0 ){
                            $("#tbody").empty();
                        }
                        for (var a = 0;a<size;a++){
                            $("#tbody").append('<tr style="text-align: center;"><td style="height: 50px">'+(a+1)+'</td><td>'+res.data[a].username+'</td><td><a  href =admin/update/'+res.data[a].id+'>修改</a></td><td><a href=admin/delete/'+res.data[a].id+'>删除</a></td></tr>')
                        }
                    }
                }
            })
})


