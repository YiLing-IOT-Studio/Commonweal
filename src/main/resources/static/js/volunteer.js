/**
 * Created by 杨玉卿 on 2018/6/10.
 */
$("#apply").hide();
$("#article").hide();
ajaxData();
$(".top-nav li").click(function(){
    var flag=$(this).attr('class');
    $("#record").hide();
    $("#state").hide();
    $("#article").hide();
    $("#apply").hide();
    $("#"+flag).show();
});
//提交申请
$("#applyBtn").click(function(event){
    event.preventDefault();
    var applyForm = new FormData(applyForm);
    if($("#orgName").val()==""){
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
            url:"/",
            dataType:"json",
            data:applyForm,
            processData:false,
            contentType:false,
            success:function(data){
                if(data==1){
                    alert("提交成功");
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
        url:"/",
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
                        '<td>' + obj['act_time'] + '</td>' +
                        '<td>'+obj['place']+'</td>' +
                        '<td class="msg">'+str+'</td>' +
                        '<td>'+obj['type']+'</td>' +
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
$(".msg").click(function(){
    $.ajax({
        type:"get",
        url:"/",
        dataType:"json",
        success:function(data){
            var oDiv=$("#msg");
            oDiv.html("");
            if(data==""){
                oDiv.html("您还没有任何通知消息~")
            }
            else if(data=="同意"){
                oDiv.append("<p>恭喜，您已成为本平台活动的发布者！</p>");
            }
            else if(data=="拒绝"){
                oDiv.append("<p>很遗憾，您未能通过审核成为本平台活动的发布者，下次再试试吧^-^</p>");
            }
            else{
                alert(data);
            }
        },
        error:function(xhr,msg){
            alert(msg);
        }
    })
});