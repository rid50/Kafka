import { OnInit, Component } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

//import Stomp from 'stompjs';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

//import $ from 'jquery';
declare const $: any;

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  //url = 'http://localhost:8080/websocket'
  //client: any;
  
  constructor(private title: Title){
    //this.connection();
  }
  
  ngOnInit() {
	this.title.setTitle('Angular Spring Websocket');
	 
  //"request URL too long" (HTTP 414 error)
//debugger; 
	const stompClient = new Client({
	  // For SockJS, set a factory that creates a new SockJS instance
	  //webSocketFactory: () => new SockJS('http://localhost:8080/websocket'),
	  
	  brokerURL: 'ws://localhost:8080/websocket', // Native WebSocket URL (if available)
	  // connectHeaders: {
		// login: 'user',
		// passcode: 'password',
	  // },
	  debug: (str) => console.log(str),
	  onDisconnect: () => {
		console.log('Disconnected');
	  },
	  reconnectDelay: 5000,
	  heartbeatIncoming: 4000,
	  heartbeatOutgoing: 4000,
	});

//	const client = new WebSocket('ws://localhost:8080/websocket');
//	const stompClient = Stomp.over(client);

//stompClient.connect({}, function (frame: any) {

	// Fallback code
	if (typeof WebSocket !== 'function') {
		//debugger;
	  // For SockJS, set a factory that creates a new SockJS instance
	  // to be used for each (re)connect
	  stompClient.webSocketFactory = () => new SockJS('http://localhost:8080/websocket');
	  // client.webSocketFactory = function () {
		// return new SockJS('http://localhost:8080/websocket');
	  // };
	}

	stompClient.onConnect = function (frame) {
	//stompClient.connect({}, function (frame: any) {
	  stompClient.subscribe("/topic/greeting", (message: any) => {
		//console.log(`Received: ${message.body}`)
		if(message.body) {
			
 			// Decode base64 payload
			const binaryString = atob(message.body);
			const len = binaryString.length;
			const bytes = new Uint8Array(len);
			for (let i = 0; i < len; i++) {
				bytes[i] = binaryString.charCodeAt(i);
			}

			// Use TextDecoder to convert byte array to a proper UTF-8 string
			const decoder = new TextDecoder('utf-8');
			const decodedString = decoder.decode(bytes);

			const jsonObject = JSON.parse(decodedString);

			//const jsonObject = JSON.parse(message.body);

			//debugger;			
			// let el: HTMLElement | null = document.getElementById('stompMsg'); 
			// let clone = el!.cloneNode(true) as HTMLElement; 
			// clone.style.display = 'block';

			// document.body.appendChild(clone);

			// var container = document.getElementById("tableLayout") as HTMLTableElement;
			// // Scrolls the container down by 1 pixel every 100 milliseconds
			// var scrollInterval = setInterval(function() {
			// 	container.scrollBy(0, 1);
			// }, 100); 			

			let tableBody = document.getElementById("tableBody") as HTMLTableElement;

			// if (tableBody instanceof HTMLTableElement) {
    		// 	const newRow = tableBody.insertRow();

			// } else {
			// // Handle the case where the element is null or not a table
			// console.error("The element found is not a table element.");
			// }

			let newRow = tableBody.insertRow();
			let cell1 = newRow.insertCell(0);
    		let cell2 = newRow.insertCell(1);
    		let cell3 = newRow.insertCell(2);

			let imgElement = document.getElementById("id");
			cell1.innerHTML = jsonObject.id;
			//(imgElement as HTMLElement).innerHTML = jsonObject.id;
			
			imgElement = document.getElementById("title");
			cell2.innerHTML = jsonObject.title;
			//(imgElement as HTMLElement).innerHTML = jsonObject.title;
			
			imgElement = document.getElementById("image");
			(imgElement as HTMLImageElement).src =  "data:image/png;base64," + jsonObject.fileContent;
			
			//imgElement = document.getElementById("image");
			//(imgElement as HTMLImageElement).src = jsonObject.fileContent; 
			//jsonObject.
			//const jsonString = JSON.stringify(jsonObject, null, 4);
			//document.body.innerHTML = `<pre>${jsonString}</pre>`;
		  //$(".msg").html(jsonString)

		  //$(".msg").html(message.body)

			// const base64Image = message.body;
			// const imageElement = document.getElementById('myImage');
			// imageElement.src = 'data:image/png;base64,' + base64Image;
		}
	  });
    };

	stompClient.onStompError = function (frame) {
	  // Invoked in case of an error reported by the broker
	  // Bad login/passcode typically causes an error
	  // Compliant brokers set the `message` header with a brief message; the body may contain details.
	  // Compliant brokers terminate the connection after any error
	  console.log('Broker reported error: ' + frame.headers['message']);
	  console.log('Additional details: ' + frame.body);
	};

	stompClient.activate();  
  }


/* const socket = new WebSocket('ws://localhost:8080/websocket');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame: any) {
    console.log('Connected: ' + frame);
	  client.subscribe("/topic/greeting", (message: any) => {
    // 2. Subscribe to the image topic
    stompClient.subscribe('/topic/image', function (message) {
        // 3. Handle the incoming message
        // Assumes message.body is base64 encoded string
        const base64Image = message.body;
        const imageElement = document.getElementById('myImage');
        imageElement.src = 'data:image/png;base64,' + base64Image;
    });
}); */




  // connection(){
	// const client = new Client({
	  // brokerURL: 'ws://localhost:8080/websocket',
	  // onConnect: () => {
		// client.subscribe("/topic/greeting", (message: any) => {
		  // console.log(`Received: ${message.body}`)
		  // if(message.body) {
			// $(".msg").html(message.body)
		  // }
		// });		  
		// client.publish({ destination: '/topic/test01', body: 'First Message' });
	  // },
	  // onDisconnect: () => {
		// console.log('Disconnected');
	  // },	  
	// });

	// client.activate();	
	  
  // }
 
}
