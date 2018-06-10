/**
 * Created by 杨玉卿 on 2018/6/9.
 */
//加载组织名单
$.ajax({
    type:"get",
    dataType:"json",
    url:"/",
    success:function(data){
        var oDiv=$(".grid");
        oDiv.html('');
        oDiv.append($('<table class="table table-striped">'+
            '<thead>'+
                '<tr>'+
                    '<th>已加入组织名称</th>'+
                    '<th>所属部门</th>'+
                    '<th>资料</th>'+
                    '<th>操作</th>'+
                '</tr>' +
            '</thead>'+'<tbody id="table1">'+'</tbody>'+'</table>'));
        $.each(data,function(index,obj){
            $("#table1").append($(
                '<tr>'+
                    '<td>'+obj['org_name']+'</td>'+
                    '<td>'+obj['org_department']+'</td>'+
                    '<td class="data"><span style="color:red;"><img src="../static/img/link.png" title="link" /></span></td>'+
                    '<td class="cancel"><span style="color:red;">删除</span></td>'+
                '</tr>'));
        })
    }
});
//删除组织
$(".cancel").click(function(){
    $(this).parent().remove();
});
//申请批复
$("#reply").click(function(){
    $.ajax({
        type:"get",
        dataType:"json",
        url:"/",
        success:function(data){
            var oDiv=$(".grid");
            oDiv.html('');
            oDiv.append($('<table class="table table-striped">'+
                '<thead>'+
                '<tr>'+
                '<th>申请组织名称</th>'+
                '<th>所属部门</th>'+
                '<th>申请资料</th>'+
                '<th>操作</th>'+
                '</tr>' +
                '</thead>'+'<tbody id="table2">'+'</tbody>'+'</table>'));
            $.each(data,function(index,obj){
                $("#table2").append($(
                    '<tr>'+
                    '<td>'+obj['org_name']+'</td>'+
                    '<td>'+obj['org_department']+'</td>'+
                    '<td class="data"><span style="color:red;"><img src="../static/img/link.png" title="link" /></span></td>'+
                    '<td><span class="agree">同意加入</span>/<span class="disagree">拒绝</span></td>'+
                    '</tr>'));
            })
        }
    });
});
//同意，拒绝操作
$(".agree .disagree").click(function(){
    $(this).parent().remove();
    $.ajax({
        type:"post",
        url:"/",
        dataType:"json",
        data:{
            'org_name':$(this).parent().find("th").eq(0),
            'signal':$(this).html()
        },
        success:function(){
            alert("操作成功！");
        },
        error:function(){
            alert("请求失败，请重试！");
        }
    })
});
