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
            var oDiv = $(".grids form");
            oDiv.html('');
            if(data.length==0){
                oDiv.html("<h3>平台刚上线，还没有活动发布~</h3>");
            }
            else {
                $.each(data, function (index, obj){
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
                                '<li><label class="readmore" data-toggle="modal" data-target="#myModa2"><input type="radio" name="activityName" class="myHiddenRadio" value="'+obj['title']+'"/>立即报名</label></li>' +
                            '</ul>' +
                            '</div>' +
                            '</div>' +

                            '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">' +
                            '<div class="modal-dialog" role="document">' +
                            '<div class="modal-content">' +
                            '<div class="modal-header">' +
                            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                            '<h4 class="modal-title" id="myModalLabel">活动提示</h4>' +
                            '</div>' +
                            '<div class="modal-body">' +
                            '<p>活动名称：'+obj['title']+'<p>'+
                            '<p>活动简介：' + obj['msg'] + '</p>' +
                            '<p>活动时间：' + obj['activityDate'] + '<br/>' +
                            '<p>活动地点：' + obj['place'] + '<br/>' +
                            '<p>报名截止时间：' + obj['deadline'] + '<br/>' +
                            '<p>人数限制：' + obj['personalLimit'] + '<br/>'
                            + '</div>' +
                            '<div class="modal-footer">' +
                            '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>')
                        );
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
                    //点击立即报名按钮
                    $(".myHiddenRadio").click(function(){
                        var activityName=$(this).attr('value');
                        $("#activityName").attr('value',activityName);
                    })
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
                '<p>'+obj['msg']+'<a href="#">...</a></p>'+
                '<div class="clear"></div>'+
                ' </div>'));
        })
    }
});

//提交报名
$("#contactBtn").click(function(event){
    event.preventDefault();
    var patternId = /^\d{12}$/;
    var stuId=$("#stuId").val();
    var patternPhone=/^1[345789]\d{9}$/;
    var telephoneNumber=$("#telephoneNumber").val();
    var patternName= /^[\u4E00-\u9FA5A-Za-z]+$/;
    var stuName=$("#stuName").val();
    var formElement=document.querySelector("#contactForm");
    var contactForm=new FormData(formElement);
    if(stuId==""||!patternId.test(stuId)){
        alert("请正确填写学号");
    }
    else if(stuName==""||!patternName.test(stuName)){
        alert("请正确填写您的姓名");
    }

    else if($("#school").val()==""){
        alert("请填写您所在的学院名称");
    }
    else if($("#major").val()==""){
        alert("请填写您的专业名称");
    }
    else if($(":radio[name='grade']").is(":checked")==false){
        alert("请选择您的年级");
    }
    else if(telephoneNumber==""||!patternPhone.test(telephoneNumber)){
        alert("请正确填写手机号");
    }
    else{
        $.ajax({
            type:"post",
            url:"/",
            data:contactForm,
            processData:false,
            contentType:false,
            success:function(data){
                if(data==1){
                    alert("报名成功");
                }
                else if(data==0){
                    alert("当前名额已满，请选择其他活动报名");
                }
                else{
                    alert("提交失败，请重试");
                }
            },
            error:function(){
                alert("请求失败");
            }
        });
    }

});










