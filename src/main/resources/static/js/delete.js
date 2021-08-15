function del(data) {
    if (!confirm('삭제 하시겠습니까?')) {
        alert('취소하였습니다.');
    } else {
        $.ajax({
            type: 'DELETE',
            url: '/del/' + data,
            dataType: "text",
            success: function () {
                alert('삭제되었습니다.')
                let url = "/";
                location.replace(url);
            },
            error: function (error) {
                alert('에러가 발생했습니다.');
                alert(error);
            }
        });
    }
}