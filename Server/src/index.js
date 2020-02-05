var http=require('http');
var url=require('url');

function getall(request, response, connection) {

	connection.query('SELECT id,name,surname,age FROM students', function (error, results, fields) {
		if (error) throw error;
		Object.keys(results).forEach(function(key) {
			var row = results[key];
			response.write(row.id + ";" + row.name + ";" + row.surname + ";" + row.age + '\n');
		});
		response.end();
	});
}

function clear(request, response, connection) {

	connection.query('DELETE FROM students', function (error, results, fields) {
		if (error) throw error;
		response.end();
	});
}

function add(request,response,connection,params) {
	connection.query('INSERT INTO students (name,surname,age) VALUES ("' + params.name + '","' + params.surname + '","' + params.age + '")', function (error, results, fields) {
		if (error) throw error;
		response.end();
	});
}

function delete_(request,response,connection,params) {
	connection.query('DELETE FROM students WHERE id="' + params.id + '"', function (error, results, fields) {
		if (error) throw error;
		response.end();
	});
}

var server=http.createServer(function(req,res){

	res.writeHead(200, {'Content-Type': 'text/plain'});

	var mysql      = require('mysql');
	var connection = mysql.createConnection({
		host     : 'localhost',
		user     : 'root',
		password : '',
		database : 'BBDD_ej1'
	});

	connection.connect((err) => {
		if (err) {
			console.log(err);
			throw err;
		}
	});

	var pathname=url.parse(req.url).pathname;
	let request_url = url.parse(req.url, true);
	var params=request_url.query;
	switch(pathname){
		case '/getall':
			getall(req,res,connection);
		break;
		case '/clear':
			clear(req,res,connection);
		break;
		case '/add':
			add(req,res,connection,params);
		break;
		case '/delete':
			delete_(req,res,connection,params);
		break;
		default:
		break;
	}

}).listen(8080);
