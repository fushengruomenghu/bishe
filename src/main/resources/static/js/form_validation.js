function checkall() {
    var ph = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
    var mail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
    var na = /^[a-zA-z]\w{3,15}$/;
    var check = true;
    $("b").remove();
    if (!ph.test($("#phone").val())) {
        $(".ph").after('<b style="color: red;">手机号码不正确！</b>');
        check = false;
    }
    if ($("#pw1").val().length < 8) {
        $(".password1").after('<b style="color: red;">密码长度不足8位</b>');
        check = false;
    }
    if ($("#pw1").val() != ($("#pw2").val())) {
        $(".password2").after('<b style="color: red;">两次密码不一致！</b>');
        check = false;
    }
    return check;
}

function check() {
    var check = checkall();
    return check;
}