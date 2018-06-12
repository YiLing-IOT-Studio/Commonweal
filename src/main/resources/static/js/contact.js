/**
 * Created by 杨玉卿 on 2018/6/10.
 */
$.ajax({
    type:"get",
    url:"/",
    dataType:"json",
    success:function(data){
        $("#activityName").attr('value',data);
    },
    error:function(xhr,msg){
        alert(msg);
    }
})
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
            dataType:"json",
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