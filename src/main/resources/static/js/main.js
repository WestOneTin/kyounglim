let data = $('#get-data').val(); // 검색한 data
let currentPage = $('#get-page').val(); // 현재페이지 pageabl이 0 부터 시작 됨
let totalData = $('#totaldata').val(); // 게시글 전체 갯수
let dataPerPage = 10;
let pageCount = 10;
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
        if(data=="") {
            i == currentPage ? pageHtml += '<li><a href="/' + (i - 1) + '">' + i + '</a></li>' : pageHtml += '<li><a href="/' + (i - 1) + '">' + i + '</a></li>';
        }else{
            i == currentPage ? pageHtml += '<li><a href="/' + (i - 1) + '?data=' + data + '">' + i + '</a></li>' : pageHtml += '<li><a href="/' + (i - 1) + '?data=' + data + '">' + i + '</a></li>';
        }
        /*if (currentPage == i) {
            pageHtml +=
                '<li><a href="/' + (i-1) + '?data=' + data + '">' + i + '</a></li>';
        } else {
            pageHtml += '<li><a href="/' + (i-1) + '?data=' + data + '">' + i + '</a></li>';
        }*/
    }

    if (last < totalPage) {
        pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
    }

    $("#pagination").html(pageHtml);

    //페이징 번호 클릭 이벤트
    $("#pagination li a").click(function () {
        let $id = $(this).attr("id");
        selectedPage = $(this).text();

        if ($id == "next") selectedPage = next;
        if ($id == "prev") selectedPage = prev;

        //전역변수에 선택한 페이지 번호를 담는다...
        globalCurrentPage = selectedPage;
        //페이징 표시 재호출
        paging(totalData, dataPerPage, pageCount, selectedPage);
        //글 목록 표시 재호출
    });
}
