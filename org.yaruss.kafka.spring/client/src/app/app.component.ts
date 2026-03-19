import { OnInit, Component } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

//import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

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
  
  // @Injectable({ providedIn: 'root' })
  constructor(private title: Title, private http: HttpClient){
    //this.connection();
  }

  //caption: string = "Start";
  start = true;
  disable = false;
  
  clickMe(): any {
	
	//alert("kuku");
	//console.log("******************************* start: " + this.start);
//debugger;
	this.disable = true;
	
	let url: string;
	if (!this.start) {
		url = 'http://localhost:8080/start_kafka_broker';
		this.start = true;
		//this.caption = "Start";
	} else {
		url = 'http://localhost:8080/stop_kafka_broker';
		this.start = false;
		//this.caption = "Stop";
	}
	
    this.http.get<any>(url).subscribe({	//The .get() method returns an Observable that emits the response data
		next: () => {
			setTimeout(() => {
				this.disable = false;
			}, 1000);
		},
		error: () => {
			this.disable = false;
		}
    });	
  }  

	// clickMe() {
		// alert("Button was clicked!");
		// window.location.href = 'http://localhost:8080/start_kafka_broker';
	  
	// }
  
	isHovered = false;

	//tableBody: HTMLTableElement;

	//table: any;
		
	//tableBody = document.getElementById("tableBody") as HTMLTableElement;
	//this.tableBody.addEventListener('mouseenter', () => this.isHovered = true);
	// this.tableBody.addEventListener('mouseleave', () => this.isHovered = false);

	//that = this; 
  ngOnInit() {

	//this.table = document.getElementById("table") as HTMLTableElement;
	// this.tableBody = document.getElementById("tableBody") as HTMLTableElement;
	//let tableBody: any;
	let tableBody: HTMLTableElement = document.getElementById("tableBody") as HTMLTableElement;
/*	
	tableBody.addEventListener('scroll', function() {
		// Check if the user is at the bottom of the scroll
		console.log("*******************************");
		if (tableBody.offsetHeight + tableBody.scrollTop >= tableBody.scrollHeight) {
			// Run your handler function when the end is reached
		console.log("Reached the end of the table!");
		}
	});
*/
	// this.tableBody.addEventListener('mouseenter', () => this.isHovered = true);
	// this.tableBody.addEventListener('mouseleave', () => this.isHovered = false);
	
	// this.table = document.getElementsByTagName("table")[0] as HTMLTableElement;
	// this.table.addEventListener('mouseenter', () => this.clickMe());
	// this.table.addEventListener('mouseleave', () => this.clickMe());

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
	  stompClient.subscribe("/topic/boutique", (message: any) => {
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

//debugger;
			if (jsonObject == "") {
				//that.clickMe();
				that.start = false;
				return;
			}
				
			let newRow = tableBody.insertRow();
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
//debugger;	
			if (jsonObject.fileContent !== "") {
				let img = document.createElement("img");
				img.src = "data:image/png;base64," + jsonObject.fileContent;
				img.alt = "Thumbnail";
				//img.height =84;
				//img.width = 56;
				cell3.appendChild(img);
				//console.log("*******************************");
			}
			//startAutoScroll(that.tableBody);
			//triggerScroll(newRow, isHovered);
			tableBody.scrollIntoView({ behavior: 'smooth', block: 'end' });

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
		}, 20);
	}
	
	function triggerScroll(element: HTMLTableRowElement, isHovered: boolean) {
	  if (!isHovered) {
		element.scrollIntoView({ behavior: 'smooth' });
	  }
	}

	stompClient.onStompError = function (frame) {
	  console.log('Broker reported error: ' + frame.headers['message']);
	  console.log('Additional details: ' + frame.body);
	};

	stompClient.activate();  
  }



}
