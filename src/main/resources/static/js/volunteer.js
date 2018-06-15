/**
 * Created by 杨玉卿 on 2018/6/10.
 */
$("#apply").hide();
$("#article").hide();
$("#msg").hide();
ajaxData();
$(".top-nav li").click(function(){
    var flag=$(this).attr('class');
    $("#record").hide();
    $("#article").hide();
    $("#apply").hide();
    $("#msg").hide();
    $("#"+flag).show();
});
//提交申请
$("#applyBtn").click(function(event){
    event.preventDefault();
    var formElement=document.querySelector("#applyForm");
    var applyForm= new FormData(formElement);
    if($("#applyName").val()==""){
        alert("请填写组织名称");
    }
    else if($("#superior").val()==""){
        alert("请填写直属上级");
    }
    else if($("#evidence").val()==""){
        alert("请上传证明材料");
    }
    else {
        $.ajax({
            type:"post",
            url:"/applyformanager",
            dataType:"json",
            data:applyForm,
            processData:false,
            contentType:false,
            success:function(data){
                if(data==1){
                    alert("提交成功");
                    window.location.replace("http://localhost:8888/volunteer")
                    // window.location.replace("https://www.zhyocean.cn:8888/volunteer");
                } else if(data == 2) {
                    alert("您已提交过申请");
                } else if(data == 3) {
                    alert("该组织名已存在");
                }
                else{
                    alert("提交失败，请重试");
                }
            },
            error:function(){
                alert("请求失败");
            }
        })
    }

});
function ajaxData(){
    $.ajax({
        type:"get",
        url:"/getmyactivitys",
        dataType:"json",
        success:function(data){
            var oDiv=$("#recordTable");
            oDiv.html("");
            if(data.length==0){
                oDiv.html("<h3>目前还没有您的活动记录..orz</h3>");
            }
            else{
                $.each(data,function(index,obj) {
                    var str = obj['msg'];
                    //限制显示词数，点击后显示所有词
                    if (obj['msg'].length >= 10) {
                        str = obj['msg'].substring(1, 10) + "...";
                    }
                    oDiv.append($(
                        '<tr>' +
                        '<td>' + obj['title'] + '</td>' +
                        '<td>' + obj['activityDate'] + '</td>' +
                        '<td>'+obj['place']+'</td>' +
                        '<td>'+obj['category']+'</td>' +
                        '</tr>'));
                    //查看全文
                    var thisTxt=$("#recordTable").find("tr").eq(obj['id']).find(".msg");
                    thisTxt.click(function(){
                        $(this).html(obj['msg']);
                    })
                })
            }
        },
        error:function(xhr,msg){
            alert(msg);
        }
    })
}
//活动记录
$(".record").click(function(){
    ajaxData();
});
//我的消息
$(".msg").click(function(){
    $.ajax({
        type:"get",
        url:"/getapplyinfo",
        dataType:"json",
        success:function(data){
            var oDiv=$("#msg");
            oDiv.html("");
            if(data=="0"){
                oDiv.html("您还没有任何通知消息~")
            }
            else if(data=="1"){
                oDiv.html("<p>恭喜，您已成为本平台活动的发布者！</p>");
            }
            else if(data=="2"){
                oDiv.html("<p>很遗憾，您未能通过审核成为本平台活动的发布者，下次再试试吧^-^</p>");
            }
            else{
                alert(data);
            }
        },
        error:function(){
            alert("请求失败");
        }
    })
});