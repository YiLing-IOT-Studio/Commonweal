/**
 * Created by 杨玉卿 on 2018/6/9.
 */
//隐藏
$("#list").hide();
$("#news").hide();
$("#record").hide();

$(".top-nav li").click(function(){
    var flag=$(this).attr('class');
    $("#publish").hide();
    $("#record").hide();
    $("#list").hide();
    $("#news").hide();
    $("#"+flag).show();
});
//发布活动，传数据
$("#pub_btn").click(function(event){
        event.preventDefault();
        var formData=document.querySelector("#publishForm");
        var publishForm=new FormData(formData);
        if($("#act_name").val()==""){
            alert("请填写活动名称！");
        }
        else if($("#act_time").val()==""){
            alert("请填写活动时间！");
        }
        else if($("#act_place").val()==""){
            alert("请填写活动地点！");
        }
        else if($("#act_deadline").val()==""){
            alert("请填写报名截止时间！");
        }
        else if($("#act_limit").val()==""){
            alert("请填写报名人数限制！");
        }
        else if($("#act_msg").val()==""){
            alert("请填写活动介绍！");
        }
        else if($("#act_msg").val().length<100){
            alert("活动介绍不少于100字！");
        }
        else if($(":radio[name='type']").is(":checked")==false){
            alert("请选择活动类别!");
        }
        else if($("#InputFile").val()==""){
            alert("请上传活动配图！");
        }
        else{
            $.ajax({
                type:"post",
                url:"/publisher",
                data:publishForm,
                processData:false,
                contentType:false,
                success:function(data){
                    if(parseInt(data)==1){
                        alert("发布成功");
                    }
                    else{
                        alert("发布失败");
                    }
                },
                error:function(){
                    alert("请求失败");
                }
            })
        }

});
//活动记录
$(".record").click(function(){
   $.ajax({
       type:"get",
       url:"/getAllActivite",
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
                       '<td class="msg">'+str+'</td>' +

                       '</tr>'));
                   //查看全文

                   var thisTxt=$("#recordTable").find("tr").eq(index).find(".msg");
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

});

//获取活动名
$.ajax({
        type:"get",
        url:"/getActiviteNames",
        dataType:"json",
        success:function(data){
            var oDiv=$("#checkActivity");
            oDiv.html("");
            if(data.length==0){
                alert("您还没有发布的活动！");
            }
            else {
                for (var i in data) {
                    var myOption = $("<option></option>");
                    myOption.append(data[i]);
                    oDiv.append(myOption)
                }
            }
        },
        error:function(){
            alert("请求失败");
        }
});

//查看活动名单
$("#checkActivityBtn").click(function(){
    var activityName=$("#checkActivity").val();
    console.log(activityName);
    $.ajax({
        type:"post",
        url:"/getpersonalinfo",
        dataType:"json",
        data:{
            "activityName":activityName
        },
        success:function(data){
            var oDiv=$("#attendList");
            oDiv.html("");
            if(data.length==0){
                oDiv.html("<h3>还没有人报名活动，再等等吧~</h3>");
            }
            else{
                $.each(data,function(index,obj) {
                    oDiv.append($(
                        '<tr>' +
                        '<td>' + obj['stuId'] + '</td>' +
                        '<td>' + obj['stuName'] + '</td>' +
                        '<td>'+obj['school']+'</td>' +
                        '<td>'+obj['major']+'</td>' +
                        '<td>'+obj['grade']+'</td>' +
                        '<td>'+obj['telephoneNumber']+'</td>' +
                        '</tr>'));
                })
            }
        },
        error:function(xhr,msg){
            alert(msg);
        }
    })
});
