window.onload = function () {
    // alert("11111")
    $.ajax(
        {
            url: "/Blog/selectAllTags",
            type: "get",
            dataType: "json",
            success:function (res) {
                if (res.flag){
                    var size = res.data.length;
                    for (var a = 0;a<size;a++){
                        $('#tags').append('<li><a href="'+res.data[a].url+'">'+res.data[a].tagName+'</a></li>');
                                     // <li><a href="">
                    }
                }
            }
        }
    )
    $.ajax(
        {
            url: "/Blog/Recently_Blogs",
            type: "get",
            dataType: "json",
            success:function (res) {
                if (res.flag){
                   var length = res.data.length;
                   for (var a = 0;a<length;a++){
                       $('#Recently_Blogs').append('<li><a href=/showBlogs?id="'+res.data[a].id+'">'+res.data[a].title+'</a></li>');
                   }
                }
            }
        }
    )
}