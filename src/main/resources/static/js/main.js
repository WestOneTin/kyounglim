var totalPages = $('#totalPages').val();
var page = 0;

for(page; page <totalPages; page++) {
    $('<li><a href="/' + page + '">' + (page+1) + '</a></li>').appendTo('#pagination');
}