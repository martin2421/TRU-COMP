const http = require('http');
const cluster = require('cluster');
const process = require('process');
const numInstances = 2;

// Primary process
if (cluster.isPrimary) {
     console.log(`Primary ${process.pid} is running`);

     // Fork workers, i.e., child processes.
     for (let i = 0; i < numInstances; i++) {
          cluster.fork();
     }

     // 'exit' event listener
     cluster.on('exit', (worker, code, signal) => {
          console.log(`worker ${worker.process.pid} died`);
          // Let's recreate a new worker.
          cluster.fork();
     });
}

// Child process
else {

     // Workers can share any TCP connection in the round-robin scheduling.
     // In the case of Node Web Server, it is an HTTP server.

     console.log(`Worker ${process.pid} started`);

     const server = require('./server');
     const router = require('./router');
     server.start(router);

}