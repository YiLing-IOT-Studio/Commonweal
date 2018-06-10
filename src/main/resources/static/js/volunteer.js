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