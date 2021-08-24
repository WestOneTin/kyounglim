var data = $('#get-data').val(); // 검색한 data
var globalCurrentPage = $('#get-page').val(); // 현재페이지
let totalData = $('#totaldata').val(); // 게시글 전체 갯수
let dataPerPage = 10;
let pageCount = 10;
let totalPage ;

$(document).ready(function (){
    paging(totalData, dataPerPage, pageCount, 1);
});

function paging(totalData, dataPerPage, pageCoutn, currentPage){
    totalPage = Math.ceil(totalData / dataPerPage); //총 페이지 수

    if(totalPage<pageCount){
        pageCount=totalPage;
    }

    let pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹
    let last = pageGroup * pageCount; //화면에 보여질 마지막 페이지 번호

    if (last > totalPage) {
        last = totalPage;
    }

    let first = last - (pageCount - 1); //화면에 보여질 첫번째 페이지 번호
    let next = last + 1;
    let prev = first - 1;

    let pageHtml = "";

    if (prev > 0) {
        pageHtml += "<li><a href='#' id='prev'> 이전 </a></li>";
    }

    //페이징 번호 표시
    for (var i = first; i <= last; i++) {
        if (currentPage == i) {
            pageHtml +=
                "<li class='on'><a href='#' id='" + i + "'>" + i + "</a></li>";
        } else {
            pageHtml += "<li><a href='#' id='" + i + "'>" + i + "</a></li>";
        }
    }

    if (last < totalPage) {
        pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
    }

    $("#pagination").append(pageHtml);
    let displayCount = "";
    displayCount = "현재 1 - " + totalPage + " 페이지 / " + totalData + "건";
    $("#displayCount").text(displayCount);


    //페이징 번호 클릭 이벤트
    $("#pagingul li a").click(function () {
        let $id = $(this).attr("id");
        selectedPage = $(this).text();

        if ($id == "next") selectedPage = next;
        if ($id == "prev") selectedPage = prev;

        //전역변수에 선택한 페이지 번호를 담는다...
        cursor = selectedPage;
        //페이징 표시 재호출
        paging(totalData, dataPerPage, pageCount, selectedPage);
    });
}

$("#dataPerPage").change(function () {
    dataPerPage = $("#dataPerPage").val();
    //전역 변수에 담긴 globalCurrent 값을 이용하여 페이지 이동없이 글 표시개수 변경
    paging(totalData, dataPerPage, pageCount, globalCurrentPage);
});

/*
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
*/



