function check() {
	if ($("#name").val()=='') {
		alert("学员姓名不能为空！");
		return false;
	}
	if (isNaN($("#keshi").val()) || $("#keshi").val() =='') {
		alert("课时必须是数字！");
		return false;
	}
	if (isNaN($("#kejia").val()) || $("#kejia").val() =='') {
		alert("课价必须是数字！");
		return false;
	}
	if (isNaN($("#gift").val()) || $("#gift").val() =='') {
		alert("赠送课时必须是数字！");
		return false;
	}
	if (isNaN($("#amount").val()) || $("#amount").val() =='') {
		alert("总金额必须是数字！");
		return false;
	}
	var checked = false;
	$('input[name="course"]:checked').each(function(){
        checked = true;
    });
	if (!checked) {
		alert("至少选择一门课程！");
		return false;
	}
	return true;
}