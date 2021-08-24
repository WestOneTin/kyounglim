var create = {
    init: function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save: function () {
        var form = $('#form')[0];
        var data = new FormData(form);
        console.log(form)
        $.ajax({
            type: 'POST',
            url: '/save',
            enctype: 'multipart/form-data',
            data: data,
            contentType: false,
            processData: false,
            success: function (data) {
                alert('글이 등록되었습니다.');
                let url = "/post/" + data;
                location.replace(url);
            },
            error: function (error) {
                alert('에러가 발생했습니다.');
                alert(error);
            }
        });
    }
};

create.init();
