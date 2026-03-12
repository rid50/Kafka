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
  
	isHovered = false;

	tableBody: any = null;
	//tableBody = document.getElementById("tableBody") as HTMLTableElement;
	//this.tableBody.addEventListener('mouseenter', () => this.isHovered = true);
	// this.tableBody.addEventListener('mouseleave', () => this.isHovered = false);

	//that = this; 
  ngOnInit() {

	this.tableBody = document.getElementById("tableBody") as HTMLTableElement;
	this.tableBody.addEventListener('mouseenter', () => this.isHovered = true);
	this.tableBody.addEventListener('mouseleave', () => this.isHovered = false);

	this.title.setTitle('Angular Spring Websocket');
	 
	let that = this; 

//debugger; 
	const stompClient = new Client({
	  // For SockJS, set a factory that creates a new SockJS instance
	  //webSocketFactory: () => new SockJS('http://localhost:8080/websocket'),
	  
	  brokerURL: 'ws://localhost:8080/websocket', // Native WebSocket URL (if available)
	  // connectHeaders: {
		// login: 'user',
		// passcode: 'password',
	  // },
	//   debug: (str) => console.log(str),
	//   onDisconnect: () => {
	// 	console.log('Disconnected');
	//   },
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
	  let isHovered = false;
	  //const tableBody = document.getElementById("tableBody") as HTMLTableElement;
	  //tableBody.addEventListener('mouseenter', () => isHovered = true);
	  //tableBody.addEventListener('mouseleave', () => isHovered = false);		
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
			//debugger;

			// let tableBody = document.getElementById("tableBody") as HTMLTableElement;

			// if (tableBody instanceof HTMLTableElement) {
    		// 	const newRow = tableBody.insertRow();

			// } else {
			// // Handle the case where the element is null or not a table
			// console.error("The element found is not a table element.");
			// }

			let newRow = that.tableBody.insertRow();
			let cell1 = newRow.insertCell(0);
    		let cell2 = newRow.insertCell(1);
    		let cell3 = newRow.insertCell(2);

			// let el: HTMLElement | null = document.getElementById('stompMsg'); 
			// let clone = el!.cloneNode(true) as HTMLElement; 
			// clone.style.display = 'block';

			// document.body.appendChild(clone);


			//let imgElement = document.getElementById("id");
			let span = document.createElement("span");
			//span.style.width = "300px";
			span.innerHTML = jsonObject.albumTitle;
			cell1.appendChild(span);

			//(imgElement as HTMLElement).innerHTML = jsonObject.id;
			
			//imgElement = document.getElementById("title");
			cell2.innerHTML = jsonObject.imageTitle;
			//(imgElement as HTMLElement).innerHTML = jsonObject.title;
			
			//imgElement = document.getElementById("image");
			
			let img = document.createElement("img");
			img.src = "data:image/png;base64," + jsonObject.fileContent;
			img.alt = "Thumbnail";
			//img.height =84;
			//img.width = 56;
			cell3.appendChild(img);
			
			//startAutoScroll(that.tableBody);
			//triggerScroll(newRow, isHovered);
			//newRow.scrollIntoView({ behavior: 'smooth', block: 'end' });

			//(cell3 as HTMLImageElement).src =  "data:image/png;base64," + jsonObject.fileContent;
			
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

	let scrollInterval;

	function startAutoScroll(element: HTMLTableElement) {
		// element.animate({
		// 	scrollTop: element.get(0).scrollHeight
		// }, 1000);

		// element.scrollTop = element.scrollHeight;
		scrollInterval = setInterval(() => {
			//newRow.scrollTo({ top: 100, behavior: 'smooth' })
			element.scrollTop += 1;

			// If reached bottom, reset to top
			if (element.scrollTop + element.clientHeight >= element.scrollHeight) {
				element.scrollTop = 0;
			}			
		}, 20); // Adjust speed
	}
	
	
// // Stop on hover
// tableBody.addEventListener('mouseenter', () => {
//     clearInterval(scrollInterval);
// });

// // Resume on hover out
// container.addEventListener('mouseleave', () => {
//     startAutoScroll();
// });	
	function triggerScroll(element: HTMLTableRowElement, isHovered: boolean) {
	  if (!isHovered) {
		element.scrollIntoView({ behavior: 'smooth' });
	  }
	}

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


 
}
