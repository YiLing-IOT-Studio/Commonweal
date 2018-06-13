/**
 * Created by 杨玉卿 on 2018/6/9.
 */
//查看已有组织名单
$(".manage").click(function(){
    $.ajax({
        type:"get",
        dataType:"json",
        url:"/getTissue",
        success:function(data){
            var oDiv=$(".grids");
            oDiv.html('');
            oDiv.append($('<table class="table table-striped">'+
                '<thead>'+
                '<tr>'+
                '<th>发布者</th>'+
                '<th>所属上级</th>'+
                '<th>资料</th>'+
                '<th>操作</th>'+
                '</tr>' +
                '</thead>'+'<tbody id="table1">'+'</tbody>'+'</table>'));
            if(data.length==0){
                oDiv.html("<h3>还没有加入平台的组织哦</h3>");
            }
            else {
                $.each(data, function (index, obj) {
                    $("#table1").append($(
                        '<tr>' +
                        '<td class="orgName">' + obj['applyName'] + '</td>' +
                        '<td>' + obj['superior'] + '</td>' +
                        '<td class="data"><span style="color:red;"><a href="' + obj['evidence'] + ' target="_blank">证明材料</a>' + '</span></td>' +
                        '<td  class="cancel"><span style="color:red;cursor:pointer;">删除</span></td>' +
                        '</tr>'));
                    //删除组织
                    $(".cancel").click(function(){
                        var cancelName=$(this).parent().find(".orgName").html();
                        $.ajax({
                            type:"post",
                            url:"/deleteOrgByApplyName",
                            data:{
                                "cancelName":cancelName
                            },
                            success:function(data){
                                if(data==1){
                                    $(this).parent().remove();
                                    alert("操作成功！");

                                }
                                else{
                                    alert("操作失败，请重试");
                                }
                            },
                            error:function(){
                                alert("请求失败");
                            }
                        })
                    });
                })

            }

        }
    });

})

//查看申请批复
$(".msg").click(function(){
    $.ajax({
        type:"get",
        dataType:"json",
        url:"/checkApplyTissue",
        success:function(data){
            var oDiv=$(".grids");
            oDiv.html('');
            oDiv.append($('<table class="table table-striped">'+
                '<thead>'+
                '<tr>'+
                '<th>申请组织名称</th>'+
                '<th>所属上级</th>'+
                '<th>申请资料</th>'+
                '<th>操作</th>'+
                '</tr>' +
                '</thead>'+'<tbody id="table2">'+'</tbody>'+'</table>'));
            if(data.length==0){
                oDiv.html("<h3>还没有组织申请加入平台哦</h3>");
            }
            else {
                $.each(data, function (index, obj) {

                    $("#table2").append($(
                        '<tr>' +
                        '<td class="applyName">' + obj['applyName'] + '</td>' +
                        '<td>' + obj['superior'] + '</td>' +
                        '<td class="data"><span style="color:red;"><a href="' + obj['evidence']+ ' " target="_blank">证明材料</a>' + '</span></td>' +
                        '<td><span class="agree" style="color:red;cursor:pointer;">同意加入</span>/<span class="disagree" style="color:red;cursor:pointer;">拒绝</span></td>' +
                        '</tr>'));
                    //同意，拒绝操作
                    $(".agree").click(function(){
                        var section=$(this).parent().parent();
                        var applyName=section.find(".applyName").html();
                        var signal=$(this).html();
                        $.ajax({
                            type:"post",
                            url:"/agreeForTissue",
                            dataType:"json",
                            data:{
                                'applyName':applyName,
                                'signal':signal
                            },
                            success:function(data){

                                if(data==1) {
                                    alert("操作成功！");
                                    section.remove();
                                }
                                else{
                                    alert("操作失败，请刷新重试")
                                }
                            },
                            error:function(){
                                alert("请求失败，请重试！");
                            }
                        })
                    });
                    $(".disagree").click(function(){

                        var section=$(this).parent().parent();
                        var applyName=section.find(".applyName").html();
                        var signal=$(this).html();
                        $.ajax({
                            type:"post",
                            url:"/agreeForTissue",
                            dataType:"json",
                            data:{
                                'applyName':applyName,
                                'signal':signal
                            },
                            success:function(data){

                                if(data==1) {
                                    alert("操作成功！");
                                    section.remove();
                                }
                                else{
                                    alert("操作失败，请刷新重试")
                                }
                            },
                            error:function(){
                                alert("请求失败，请重试！");
                            }
                        })
                    });
                })

            }

        }
    });
});

