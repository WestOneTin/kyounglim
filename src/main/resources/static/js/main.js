var totalPages = $('#totalPages').val();
var data = $('#get-data').val();
var page = 0;

for(page; page <totalPages; page++) {
    $('<li><a href="/' + page + '?data=' + data + '">' + (page+1) + '</a></li>').appendTo('#pagination');
}

