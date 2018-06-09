/**
 * Created by 杨玉卿 on 2018/6/9.
 */
//发布活动
$("#publish").click(function(){
    var oDiv=$(".grid");
    oDiv.html('');
    oDiv.append($('<form>'+
        '<div class="form-group">'+
        '<label for="exampleInputEmail1">Email address</label>'+
    '<input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">'+
        '</div>'+
        '<div class="form-group">'+
        '<label for="exampleInputPassword1">Password</label>'+
        '<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">'+
        '</div>'+
        '<div class="form-group">'+
        '<label for="exampleInputFile">File input</label>'+
    '<input type="file" id="exampleInputFile">'+
        '<p class="help-block">Example block-level help text here.</p>'+
    '</div>'+
    '<div class="checkbox">'+
        '<label>'+
        '<input type="checkbox"> Check me out'+
    '</label>'+
    '</div>'+
    '<button type="submit" class="btn btn-default">Submit</button>'+
        '</form>'));
});
//活动记录
function ajaxData(currentPage){
    $.ajax({
        type:"get",
        url:"/",
        dataType:"json",
        data:{
            rows:"10",
            pageNo:currentPage
        },
        success:function(data){
            var rows=3;
            var oDiv = $(".grids");
            oDiv.html('');
            $.each(data,function(index,obj) {
                    var str=obj['msg'];
                    //限制显示词数，点击后显示所有词
                    if(obj['msg'].length>=100){
                        str=obj['msg'].substring(1,100)+"...";
                    }
                    oDiv.append($('<div class="grid box">'+
                        '<div class="grid-header">'+
                        '<h3>'+obj['title']+'</h3>'+
                        '<ul>'+
                        '<li><span>'+obj['publisher']+'</span></li>'+
                        '<li><span>'+obj['publish_date']+'</span></li>'+
                        '<li><span style="color:deeppink">'+'剩余'+obj['remain']+'名额'+'</spanstyle></li>'+
                        '</ul>'+
                        '</div>'+
                        '<div class="grid-img-content">'+
                        '<a href="singlepage.html"><img src="'+obj['img']+'"/></a>'+
                        '<p>'+str+'</p>'+
                        '<div class="clear"></div>'+
                        '</div>'+
                        '<div class="comments">'+
                        '<ul>'+
                        '<li><a href="#" id="view" data-toggle="modal" data-target="#myModal"><img src="../static/img/views.png" title="view" /></a></li>'+
                        '<li><a href="#" id="likes"><img src="../static/img/likes.png" title="likes" /></a></li>'+
                        '<li><a href="contact.html" id="link"><img src="../static/img/link.png" title="link" /></a></li>'+
                        '<li><a class="readmore" href="singlepage.html">ReadMore</a></li>'+
                        '</ul>'+
                        '</div>'+
                        '</div>'+

                        '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">'+
                        '<div class="modal-dialog" role="document">'+
                        '<div class="modal-content">'+
                        '<div class="modal-header">'+
                        '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
                        '<h4 class="modal-title" id="myModalLabel">'+obj['title']+'</h4>'+
                        '</div>'+
                        '<div class="modal-body">'+
                        '活动简介：'+obj['msg']+'<br/>'+
                        '活动时间：'+obj['act_time']+'<br/>'+
                        '活动地点：'+obj['place']+'<br/>'+
                        '报名截止时间：'+obj['deadline']+'<br/>'+
                        '人数限制：'+obj['limit']+'<br/>'
                        +'</div>'+
                        '<div class="modal-footer">'+
                        '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>'));
                    scrollTo(0,0);//回到顶部
                    //分页
                    $("#pagination").paging({
                        rows:rows,//每页显示条数
                        pageNo:currentPage,//当前所在页码
                        totalPage:data[data.length-1]['totalPage'],//总页数
                        totalSize:data[data.length-1]['totalSize'],//总记录数
                        callback:function(currentPage){
                            ajaxData(tag,currentPage);
                        }
                    });
                    //查看全文
                    $(".grid-img-content p").click(function(){
                        $(this).html(obj['msg']);
                    })
                    //点赞
                    $(".comments #likes").click(function(){
                        $(this).html('<img src="../static/img/likes1.png" title="likes" /></a>');
                    })


                }
            )

        },
        error:function(){
            alert("请求失败");
        }
    })
}
//初始时，所有数据
ajaxData(1);
//活动记录
$("#record").click(function(){
    ajaxData(1);
});
//