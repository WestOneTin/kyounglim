let data = $('#get-data').val(); // 검색한 data
let currentPage = $('#get-page').val(); // 현재페이지 pageabl이 0 부터 시작 됨
let totalData = $('#totaldata').val(); // 게시글 전체 갯수
let dataPerPage = 10;
let pageCount = 5;
let totalPage;

$(document).ready(function () {
    //페이징 표시 호출
    paging(totalData, dataPerPage, pageCount, currentPage+1);
});

function paging(totalData, dataPerPage, pageCount, currentPage) {
    console.log("currentPage : " + currentPage);

    totalPage = Math.ceil(totalData / dataPerPage); //총 페이지 수

    if(totalPage<pageCount){
        pageCount=totalPage;
    }

    let pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹
    console.log("pageGroup : " + pageGroup);

    let last = pageGroup * pageCount; //화면에 보여질 마지막 페이지 번호
    if (last > totalPage) {
        last = totalPage;
    }

    let first = last - (pageCount - 1); //화면에 보여질 첫번째 페이지 번호
    let next = last + 1;
    let prev = first - 1;

    console.log("last : " + last);
    console.log("first : " + first);
    console.log("next : " + next);
    console.log("prev : " + prev);

    let pageHtml = "";

    if (prev > 0) {
        pageHtml += "<li><a href='#' id='prev'> 이전 </a></li>";
    }

    //페이징 번호 표시
    for (var i = first; i <= last; i++) {
        if (currentPage == i) {
            /*pageHtml +=
                "<li><a href='/#?data" + data + "' id='" + i + "'>" + i + "</a></li>";
        } else {
            pageHtml += "<li><a href='/#?data=" + data + "' id='" + i + "'>" + i + "</a></li>";*/
            pageHtml +=
                '<li><a href="/' + (i-1) + '?data=' + data + '">' + i + '</a></li>';
        } else {
            pageHtml += '<li><a href="/' + (i-1) + '?data=' + data + '">' + i + '</a></li>';
        }
    }

    if (last < totalPage) {
        pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
    }

    $("#pagination").html(pageHtml);

    //페이징 번호 클릭 이벤트
    $("#pagination li a").click(function () {
        let $id = $(this).attr("id");
        selectedPage = $(this).text();

        if ($id == "next") selectedPage = next; console.log("selectedPage next : " + selectedPage);
        if ($id == "prev") selectedPage = prev; console.log("selectedPage prev : " + selectedPage);

        //전역변수에 선택한 페이지 번호를 담는다...
        globalCurrentPage = selectedPage;
        //페이징 표시 재호출
        paging(totalData, dataPerPage, pageCount, selectedPage);
        //글 목록 표시 재호출
    });
}


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



