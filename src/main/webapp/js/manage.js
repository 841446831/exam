var menu = document.querySelector('.menu');
var content = document.querySelector('.content');
menu.addEventListener('click', function(event){
	var el = event.target;
	var url = el.dataset.url;
	console.log(url);
	$(content).load(url);
});