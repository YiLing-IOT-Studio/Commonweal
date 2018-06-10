/**
 * Created by 杨玉卿 on 2018/6/10.
 */
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