/**
 * Created by 杨玉卿 on 2018/6/9.
 */
function ajaxData(tag,currentPage){
    $.ajax({
        type:"get",
        url:"/getActivite",
        dataType:"json",
        data:{
            'tag':tag,
            rows:"10",
            pageNo:currentPage
        },
        success:function(data){
            var rows=3;
            var oDiv = $(".grids");
            oDiv.html('');
            if(data.length==0){
                oDiv.html("<h3>平台刚上线，还没有活动发布~</h3>");
            }
            else {
                $.each(data, function (index, obj) {
                        var str = obj['msg'];
                        //限制显示词数，点击后显示所有词
                        if (obj['msg'].length >= 100) {
                            str = obj['msg'].substring(1, 100) + "...";
                        }
                        oDiv.append($('<div class="grid box">' +
                            '<div class="grid-header">' +
                            '<h3>' + obj['title'] + '</h3>' +
                            '<ul>' +
                            '<li><span>' + obj['publisher'] + '</span></li>' +
                            '<li><span>' + obj['publishDate'] + '</span></li>' +
                            '<li><span style="color:deeppink">' + '剩余' + obj['remain'] + '名额' + '</spanstyle></li>' +
                            '</ul>' +
                            '</div>' +
                            '<div class="grid-img-content">' +
                            '<a href="singlepage.html"><img src="' + obj['img'] + '"/></a>' +
                            '<p>' + str + '</p>' +
                            '<div class="clear"></div>' +
                            '</div>' +
                            '<div class="comments">' +
                            '<ul>' +
                            '<li><a href="#" id="view" data-toggle="modal" data-target="#myModal"><img src="img/views.png" title="view" /></a></li>' +
                            '<li><a href="#" id="likes"><img src="img/likes.png" title="likes" /></a></li>' +
                            '<li><a href="contact.html" id="link"><img src="img/link.png" title="link" /></a></li>' +
                            '<li><a class="readmore" href="singlepage.html">ReadMore</a></li>' +
                            '</ul>' +
                            '</div>' +
                            '</div>' +

                            '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
                            '<div class="modal-dialog" role="document">' +
                            '<div class="modal-content">' +
                            '<div class="modal-header">' +
                            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                            '<h4 class="modal-title" id="myModalLabel">' + obj['title'] + '</h4>' +
                            '</div>' +
                            '<div class="modal-body">' +
                            '活动简介：' + obj['msg'] + '<br/>' +
                            '活动时间：' + obj['activiteDate'] + '<br/>' +
                            '活动地点：' + obj['place'] + '<br/>' +
                            '报名截止时间：' + obj['deadLine'] + '<br/>' +
                            '人数限制：' + obj['personalLimit'] + '<br/>'
                            + '</div>' +
                            '<div class="modal-footer">' +
                            '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>' +
                            '<button type="button" class="btn btn-primary"><a href="contact.html">立即报名</a></button>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>'));
                        scrollTo(0, 0);//回到顶部
                        //分页
                        $("#pagination").paging({
                            rows: rows,//每页显示条数
                            pageNo: currentPage,//当前所在页码
                            totalPage: data[data.length - 1]['totalPage'],//总页数
                            totalSize: data[data.length - 1]['totalSize'],//总记录数
                            callback: function (currentPage) {
                                ajaxData(tag, currentPage);
                            }
                        });
                        var thisTxt=$(".grid-img-content").eq(obj['id']).find("p");
                        //查看全文
                        thisTxt.click(function () {
                            $(this).html(obj['msg']);
                        });
                        //点赞
                        $(".comments #likes").click(function () {
                            $(this).html('<img src="img/likes1.png" title="likes" /></a>');
                        });


                    }
                )
            }

        },
        error:function(){
            alert("请求失败");
        }
    })
}
//初始时，所有数据
ajaxData('全部活动',1);
//点击左侧分类菜单，对应数据
$(".top-nav ul li").click(function(){
    var tag=$(this).find("a").html();
    ajaxData(tag,1);
});
//注销
$('.logout').click(function () {
    $.ajax({
        type: "get",
        url: "/logout",
        dataType: "json",
        data: {},
        success: function (data) {
            if (data == '200') {
                window.location.replace("http://localhost:8888/auth1")
            }
            else {
                alert("注销失败，请重试！")
            }
        },
        error: function () {
            alert("注销请求失败");
        }
    })
});
//搜索栏搜索
$("#searchBar").click(function(event){
    event.preventDefault();
    var val=$("#searchFor").val();
    ajaxData(val,1);
});
//活动新闻
$.ajax({
    type:"get",
    url:"/",
    dataType:"json",
    success:function(data){

        var news=$(".popular-post");
        news.html('');
        $.each(data,function(index,obj){
            news.append($(' <div class="post-grid">'+
                '<img src="'+obj['news_img']+'" title="post1">'+
                '<p>Lorem ipsum dolor sit ametconsectetur dolor,'+'<a href="#">...</a></p>'+
                '<div class="clear"></div>'+
                ' </div>'));
        })

    }
});






