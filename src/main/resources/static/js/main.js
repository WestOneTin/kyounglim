var data = $('#get-data').val(); // 검색한 data
var cursor = $('#get-page').val(); // 현재페이지
var total = $('#totalPages').val(); // 총 갯수

function pageAlgo(bottomSize, cursor){
    let firstBottomNumber = cursor % bottomSize + 1 // 하단 처음
    let lastBottomNumber = cursor % bottomSize + bottomSize; // 하단 마지막

    if(lastBottomNumber > total) lastBottomNumber = total;  //총 갯수보다 큰 경우 방지

    return {
        firstBottomNumber,
        lastBottomNumber,
        total,
        bottomSize,
        cursor
    }
}

//280개의 데이터, 하단에는 20개씩, 1개화면에는 10개, 지금 나의페이지는 21
let info = pageAlgo(5, cursor)

//실제 출력하는 방법 샘플
for(let i = info.firstBottomNumber ; i <= info.lastBottomNumber; i++){
    i == info.cursor ? $('<li><a href="/' + (i-1) + '?data=' + data + '">' + i + '</a></li>').appendTo('#pagination') : $('<li><a href="/' + (i-1) + '?data=' + data + '">' + i + '</a></li>').appendTo('#pagination');
};
/*

var update = {
    init : function() {
        var _this = this;
        $('#btn-update').on('click', function () {
            var val = $(this);
            var tr = val.parent().parent();
            var td = tr.children();
            var data = {
                id: td.eq(0).text(),
                item: td.eq(3).text(),
                material: td.eq(4).text(),
                stock: td.eq(5).text(),
                content: td.eq(6).text(),
                file_id: td.eq(0).text(),
                file_filename: td.eq(1).text()
            };

            _this.save(data);
        });
    },
    save : function (data) {
        var dto = encodeURI(data);

        $.ajax({
            type: 'GET',
            url: '/create?dto=' + dto,
            dataType: 'text',
        }).done(function() {
            alert("성공");
        }).error(function (error) {
            alert("error : " + error);
        });
    }
}

update.init();*/
