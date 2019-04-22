//清空表单样式及内容
function reset_form(ele) {
    $(ele)[0].reset();
    //清空表单样式
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}


//日期事件
$('#yyyy_mm_dd').datetimepicker({
    bootcssVer: 3,
    format: "yyyy-mm-dd",
    //endDate: 2017,
    autoclose: true,
    startView: 4,
    minView: 2,
    maxView: 4,
    language: 'zh-CN',
    todayBtn: 1,
    todayHighlight: 1
});

$('#yyyy_mm_dd2').datetimepicker({
    bootcssVer: 3,
    format: "yyyy-mm-dd",
    //endDate: 2017,
    autoclose: true,
    startView: 4,
    minView: 2,
    maxView: 4,
    language: 'zh-CN',
    todayBtn: 1,
    todayHighlight: 1
});




