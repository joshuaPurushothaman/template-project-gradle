var net = require("net");

var client = net.createConnection(9000, '192.169.1.18', function () {
    // 'connect' listener.
    console.log('connected to server!');
    client.write('world!\r\n');
});

client.on('data', function (data) {
    console.log(data.toString());
    client.end();
});

client.on('end', function () {
    console.log('disconnected from server');
});