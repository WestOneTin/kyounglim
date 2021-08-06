var create = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var form = $('#form');
        var data = new FormData(form);
        alert(form);

        $.ajax({
            type: 'POST',
            url: '/save',
            dataType: 'multipart/form-data',
            data: data
        }).done(function() {
            alert('글이 등록되었습니다.');
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    }

};

create.init();
